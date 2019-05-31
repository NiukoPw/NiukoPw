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

})

module.exports = router;
