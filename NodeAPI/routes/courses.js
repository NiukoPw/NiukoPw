var express = require('express');
var router = express.Router();
var db = require('../db');
var bodyParser = require("body-parser");

// visualizza tutti i corsi in json
router.get('/all_courses', function(req, res, next) {
    var courses = []
    db.each("SELECT * FROM corso", {}, function(err, row) {
        if (err) {
            return console.error(err);
        }
        courses.push({
            id: row.ID,
            titolo: row.titolo,
            sede: row.sede,
            durata: row.durata,
            descrizione: row.descrizione,
            stato: row.stato,
            postiLiberi: row.postiLiberi
        });
    }, function(err, rows) {
        res.json({
            records: courses
        });
    });
});


router.post('/join', function(req, res, next){
  data = {
    $idUtente: req.body.idUtente,
    $idCorso: req.body.idCorso
  };
  db.run("INSERT INTO UTENTECORSO_REL VALUES ($idUtente,$idCorso)", data, function(err, rows) {
    if (err) {
      return console.error(err);
      res.json({
        iscrizione : false
      });
    };
  });

  res.json({
    iscrizione : true
  });
});

module.exports = router;
