const Pool = require("pg").Pool;

const pool = new Pool({
    user: "jumpstonik",
    password: "holamundo13579",
    database: "quanticadb",
    host: "localhost",
	port: 5432
});

module.exports = pool;