const express = require("express")
const bodyParser = require("body-parser")
const app = express()

const data = {
    "Ellära": [
	{
	    "question": "Spänning mäts i",
	    "answer": "Volt"
	}
    ],
    "Programmering": [
	{
	    "question": "Nämn tre systemspråk",
	    "answer": "C, C++, Rust"
	},
	{
	    "question": "Vad är en pekare",
	    "answer": "En variabel som lagrar minnesadresser"
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

app.get("/", (request, response) => {
    response.status(200).send("Hello")
})

app.listen(3000, () => {
    console.log("Running server on port 3000")
})
