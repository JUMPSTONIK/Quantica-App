const express = require('express')
const graphqlHTTP = require('express-graphql').graphqlHTTP;
const {buildSchema} = require('graphql')
const crypto = require('crypto');
const pool = require("./db")

const app = express();
app.use(express.json());
app.use(express.urlencoded({
	extended: true
}));

//Arbitrary ID manager since we don't use a database
var index = 4;

// Initializing Users Array.. It will behave like a dummy database 
var users = [{
    "id_user": 1,
    "nickname": "gato",
    "email": "gato@gmail.com",
    "password" : "123456789",
    "country" : "Costa Rica"
}, {
    "id_user": 2,
    "nickname": "fresa",
    "email": "fresa@gmail.com",
    "password" : "987654321",
    "country" : "USA"
}, {
    "id_user": 3,
    "nickname": "yei",
    "email": "yei@gmail.com",
    "password" : "qwertyuiop",
    "country" : "Costa Rica"
}, {
    "id_user": 4,
    "nickname": "draco",
    "email": "draco@gmail.com",
    "password" : "asdfghjkl",
    "country" : "Costa Rica"
}
]


/*
    var count = req.query.count != undefined ? req.query.count : req.query.count = 100;
    if(req.query.country){
        var countrySpots = users.filter(function(users) {
            return users.country == req.query.country
        });
        res.end(JSON.stringify(countrySpots.slice(0, count)));
    }
    else{
    	res.end(JSON.stringify(users.slice(0, count)));
    }
    */

// A promo message to user 
var message = "Black Friday! Get 50% cachback on saving your first spot.";

app.get('/messages', function (req, res) {
    res.end(JSON.stringify(message));
})

// Get the User for log in
app.get('/Users', async function (req, res) {
    list = []
    const email = req.query.email
    const password = crypto.createHash('sha256', req.body.password).update(email).digest('hex')

    const userWithEmail = await pool.query("SELECT * FROM users WHERE email = $1",
        [email]
    );

    list[0] = user
    console.log(userWithEmail.rows)
    console.log(userWithEmail.rows[0].password)
    if (userWithEmail.rows[0].password == password){
        console.log("we found the user")
        var user = {
            "id_user": 0,
            "email": userWithEmail.rows[0].email,
            "password" : userWithEmail.rows[0].password,
            "nickname": null
        }
        res.status(200).end(JSON.stringify(list))   
    }else{
        console.log("we couldn't find the user")
        res.status(404).end(JSON.stringify(list))
    }

})

// Get one particular Users using email
app.get('/Users/:email', async function (req, res) {
    const email = [req.params.email];
    
    const userWithEmail = await pool.query("SELECT * FROM users WHERE email = $1",
        [email]
    );


    console.log(userWithEmail.rows)
    res.status(200).end(JSON.stringify(userWithEmail))
})

// Create a new Users and add it to existing Users list 
app.post('/user', async function (req, res) {
	console.log(req.body)
    const passHASH = crypto.createHash('sha256', req.body.password).update(req.body.email).digest('hex')
    try {
        
        const userInfo = await pool.query("SELECT * FROM users WHERE (email = $1) OR (nickname = $2)",
        [req.body.email, req.body.nickname]
        );
        
        //console.log(userWithnick)
        if (userInfo.rowCount == 0 ) {
            const userParams = [req.body.nickname, req.body.email, passHASH]
            const aUser = await pool.query(
                "INSERT INTO users(nickname, email, password) VALUES ($1, $2, $3)",
                userParams
            );

            var newUser = {
                "id_user": 0,
                "email": req.body.email,
                "password" : passHASH,
                "nickname": req.body.nickname
            }

            res.status(201).end(JSON.stringify(newUser));
            console.log("201")
        }else{
            ErrorMessage = "User with that nickname or email, already exist"
            console.log(ErrorMessage)
            res.status(409).end();
        }
        
    } catch (error) {
        console.error(error.message);
        console.log("409")
        res.status(409).end(JSON.stringify(error.message));
    }

    
})

// Update a Users 
app.put('/user/:id', function (req, res) {
    var user;
    for (var i = 0; i < users.length; i++) {
        if(users[i].id_user == req.params.id){
            users[i].nickname = req.body.nickname;
            users[i].password = req.body.password;
            users[i].email = req.body.email;
            user = users[i];
        }
    }

    res.end(JSON.stringify(user));
})

// Delete a Users 
app.delete('/user/:id', function (req, res) {
    for (var i = 0; i < users.length; i++) {
        if(users[i].id_user == req.params.id){
            users.splice(i, 1);
            res.status(204).end(JSON.stringify(users[i]));
        }
    }
});



app.get('/', (req,res) => res.send('Welcome! you are all set to go'))

const server = app.listen(4000,'127.0.0.1', function(req,res){
	var host = server.address().address
	var port = server.address().port

	console.log(`Server running at http://${host}:${port}/`)
});

/*
const schema = buildSchema(`
	type Query {
		user(id: Int!): User
		users(nickname: String): [User]
	}

	type Mutation{
		updateUserName(id: Int!, nickname: String!): User
	}

	type User {
		id_user: Int
		nickname: String
		last_nickname: String
		nicknickname: String
		email: String
		password: String
		time_zone: String
		password: String
		cellphone: String
		date_of_birth: String
		sex: String
		profile_pic: String
	}

`)

let getUser = (args) =>{
	let id = args.id;
	return user.filter(user =>{
		return user.id == id;
	})[0]
}

let getUsers = (args) =>{
	if (args.nickname){
		let nickname = args.nickname;
		return users.filter( user => user.nickname === nickname);
	}else{
		return users
	}
}

let updateUserName = ({id, nickname}) => {
	users.map(user => {
		if(user.id === id){
			user.nickname = nickname;
			return user; 
		}
	})
	return users.filter( user => user.id === id)[0];
}

const root = {
	user: getUser,
	users: getUsers,
	updateUserName: updateUserName
}

app.use('/api', graphqlHTTP({
  schema: schema,
  rootValue: root,
  graphiql: true,
}));
*/
/*   
const QueryRoot = new graphql.GraphQLObjectType({
  nickname: 'Query',
  fields: () => ({
    hello: {
      type: graphql.GraphQLString,
      resolve: () => "Hello world!"
    }
  })
})
*/  
//const schema = new graphql.GraphQLSchema({ query: QueryRoot });