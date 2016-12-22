var express = require("express");
var app = express();

app.use(express.static("public"));

app.get('/', function(request, response) {
    response.status(200).send("Hello");
});

app.listen(3000, function() {
    console.log("Running server on port 3000");
});
