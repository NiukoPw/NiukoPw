var express = require('express');
var router = express.Router();
var db = require('../db');

router.get('/all_courses', function(req, res, next) {
    var corsi = []
    db.each("SELECT * FROM corso", {}, function(err, row) {
        if (err) {
            return console.error(err);
        }
        corsi.push({
            id: row.id,
            titolo: row.titolo,
            descrizione: row.descrizione,
            stato: row.stato,
            postiLiberi: row.postiLiberi,
            durata: row.durata,
            sede: row.sede,
            img: row.img
        });
    }, function(err, rows) {
        res.json({
            records: corsi,
            queryRecordCount: 0,
            totalRecordCount: 0
        });
    });
});

router.get('/inserisci_corso', function(req, res, next) {
  db.run('INSERT INTO corso VALUES (10, "corso1", "aodsm<dècoiadpfovm", "pubblicato", 10, 32, "padova", null)');
});

module.exports = router;
