var express = require('express');
var router = express.Router();
var db = require('../db');
var bodyParser = require("body-parser");

// visualizza tutti gli utenti in json
router.get('/all_users', function(req, res, next) {
  var users = []
  db.each("SELECT * FROM utente", {}, function(err, row) {
    if (err) {
      return console.error(err);
    }
    users.push({
      id: row.ID,
      nome: row.nome,
      cognome: row.cognome,
      cf: row.CF,
      amministratore: row.amministratore
    });
  }, function(err, rows) {
    res.json({
      records: users
    });
  });
});

// visualizza tutti i corsi di un utente
router.get('/corsi_utente', function(req, res, next) {
  var corsi = []
  db.each("SELECT c.titolo,c.descrizione,c.postiTotali,c.postiLiberi,c.durata,c.sede FROM corso AS c, utentecorso_rel AS ucr, utente AS u WHERE u.ID = 1 c.ID = ucr.IdCorso and ucr.IdUtente = u.ID",
  {},
  function(err, row) {
    if (err) {
      return console.error(err);
    }
    corsi.push({
      titolo: row.titolo,
      descrizione: row.descrizione,
      postiLiberi: row.postiLiberi,
      durata: row.durata,
      sede: row.sede
    });
  }, function(err, rows) {
    res.json({
      records: corsi
    });
  });
});

// visualizza tutte le lezioni che un utente dovrà frequentare
router.get('/lezioni_utente', function(req, res, next) {
  var lezioni = []
  db.each("SELECT c.titolo,c.descrizione,c.sede,l.data,l.durata FROM corso as c, lezione as l, utentelezione_rel as ulr, utente as u WHERE u.ID = 1 and u.ID = ulr.IdUtente AND ulr.IdLezione = l.ID AND l.IdCorso = c.ID ",
  {},
  function(err, row) {
    if (err) {
      return console.error(err);
    }
    lezioni.push({
      titolo: row.titolo,
      descrizione: row.descrizione,
      data: row.data,
      durata: row.durata,
      sede: row.sede
    });
  }, function(err, rows) {
    res.json({
      records: lezioni
    });
  });
});

// visualizza le ore di lezione totali di un utente
router.get('/orelezione_utente', function(req, res, next) {
  var ore = 0
  db.each("SELECT sum(durata) as totale_ore FROM lezione as l, utentelezione_rel as ulr, utente as u WHERE u.ID = 1 and l.ID = ulr.IdLezione and ulr.IdUtente = u.ID ",
  {},
  function(err, row) {
    if (err) {
      return console.error(err);
    }
    ore = row.totale_ore;
  }, function(err, rows) {
    res.json({
      ore: ore
    });
  });
});

router.post('/login', function(req, res, next){
  data = {
    $username: req.body.username,
    $password: req.body.password
  };
  db.all("SELECT ID, nomeUtente, password FROM UTENTE WHERE nomeUtente = $username AND password = $password", data, (err, rows) => {
    if (rows.length == 0) {
      res.json({
        chiamata : false
      });
    }else{
      rows.forEach((row) => {
        res.json({
          id:row.ID,
          chiamata : true
        });
      });
    }
  });
});

router.post('/signup', function(req, res, next){
  data = {
    $nome: req.body.nome,
    $cognome: req.body.cognome,
    $username: req.body.username,
    $password: req.body.password
  };
  db.all("SELECT nome,cognome,nomeUtente,password FROM UTENTE WHERE nome = $nome AND cognome = $cognome AND nomeUtente = $username AND password = $password", data, (err, rows) => {
    if (rows.length > 0) {
      res.json({
        chiamata : false
      });
    }else{
      db.run("INSERT INTO UTENTE (nome,cognome,nomeUtente,password) VALUES ($nome,$cognome,$username,$password)", data, function(err, row) {
        if (err) {
          return console.error(err);
        };
      });
      res.json({
        registrazione : true
      });
    }
  });
});

router.post('/single_user', function(req,res,next){
  userId = {
    $userId : req.body.id
  };
  db.run("SELECT nome,cognome,CF,nomeUtente,password FROM UTENTE WHERE ID = $userId", userId, function(err, row) {
    if (err) {
      return console.error(err);
    };
  });
  res.json({
    userId : userId
  });
});

module.exports = router;
