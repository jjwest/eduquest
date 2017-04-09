// This is a minimalistic provider intended
// for testing purposes

const express = require("express")
const bodyParser = require("body-parser")
const app = express()

// This is a mock database.
// In a real case scenario this would be stored and handled by a proper database.
const data = {
    "Ellära": [
	{
	    "question": "Spänning mäts i",
	    "answer": "Volt"
	}
    ],
    "Test": [],
    "Programmering": [
	{
	    "question": "Nämn tre systemspråk",
	    "answer": "C, C++, Rust"
	},
	{
	    "question": "Vad är en pekare",
	    "answer": "En variabel som lagrar minnesadresser"
	},
	{
	    "question": "Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. Det här är en väldigt lång fråga. ",
	    "answer": "Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. Det är är ett väldigt långt svar. "
	}
    ]
}

app.use(bodyParser.urlencoded({
    extended: true
}))

app.use(bodyParser.json())

app.post("/questions", (request, response) => {
    const categories = request.body
    const questions = categories
	  .map(c => data[c])
	  .reduce((sum, c) => sum.concat(c), [])

    response.send(questions)
})

app.get("/categories", (request, response) => {
    const categories = Object.keys(data)
    response.send(categories)
})

app.get("/eduquestprovider", (request, response) => {
    response.send("isProvider")
})

app.get("/", (request, response) => {
    response.sendStatus(200)
})

app.listen(3000, () => {
    console.log("Running server on port 3000")
})
