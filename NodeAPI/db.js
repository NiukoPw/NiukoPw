const sqlite3 = require('sqlite3').verbose();

var db = new sqlite3.Database('Niuko.db', (err) => {
  if (err) {
    return console.error(err.message);
  }
  console.log('Connected to the SQlite database.');
});


/*
db.close((err) => {
  if (err) {
    console.error(err.message);
  }
  console.log('Close the database connection.');
});
*/

module.exports = db;
