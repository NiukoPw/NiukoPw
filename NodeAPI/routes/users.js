var express = require('express');
var router = express.Router();
var db = require('../db');

router.get('/all_users', function(req, res, next) {
    var users = []
    db.each("SELECT * FROM utente", {}, function(err, row) {
        if (err) {
            return console.error(err);
        }
        users.push({
            id: row.id,
            nome: row.nome,
            cognome: row.cognome,
            cf: row.CF,
            amministratore: row.amministratore
        });
    }, function(err, rows) {
        res.json({
            records: users,
            queryRecordCount: 0,
            totalRecordCount: 0
        });
    });
});

router.get('/inserisci_utente', function(req, res, next) {
  db.run('INSERT INTO utente VALUES ("andrea", "rossi", "easdfrcvvfdswwef", 0, 10, null)');
});

module.exports = router;
