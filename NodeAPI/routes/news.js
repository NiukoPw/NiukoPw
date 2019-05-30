var express = require('express');
var router = express.Router();
var db = require('../db');

// visualizza tutte le notizie in json
router.get('/all_news', function(req, res, next) {
    var news = []
    db.each("SELECT * FROM notizia", {}, function(err, row) {
        if (err) {
            return console.error(err);
        }
        news.push({
            id: row.id,
            titolo: row.titolo,
            descrizione: row.descrizione,
            data: row.data
        });
    }, function(err, rows) {
        res.json({
            records: news
        });
    });
});

module.exports = router;
