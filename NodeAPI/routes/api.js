var express = require('express');
var fs = require('fs')
var router = express.Router();
var bodyParser = require('body-parser');
var db = require('../db');
//var csv2sql = require('db2sql-stream');



/* GET users listing. */


router.post('/registrazioni ', function(req, res, next) {
    data = {
        $name: req.body.name,
        $email: req.body.email,
        $favourite_song: req.body.favourite_song
    };
    db.run("INSERT INTO users (name,email,favourite_song) VALUES ('VVV','IDCMI@DJFDJF.VVV','ffffff')",data, function(err, row) {
        if (err) {
            return console.error("    42");
        }
        res.redirect('/users');
    });
});

/* Inserisci un utente REGISTRAZIONE */
/*router.get('/user_reg', function(req, res, next) {

}*/

module.exports = router;
