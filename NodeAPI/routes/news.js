var express = require('express');
var router = express.Router();
var db = require('../db');

router.get('/all_news', function(req, res, next) {
    var notizie = []

    db.each("SELECT * FROM notizia", {}, function(err, row) {
        if (err) {
            return console.error(err);
        }
        notizie.push({
            id: row.id,
            titolo: row.titolo,
            descrizione: row.descrizione,
            data: row.data,
            img: row.img
        });
    }, function(err, rows) {
        res.json({
            records: notizie,
            queryRecordCount: 0,
            totalRecordCount: 0
        });
    });
});

module.exports = router;
