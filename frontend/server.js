const express = require('express');
const http = require('http');
const cors = require('cors');
const path = require('path');

const app = express();

const port = process.env.WEB_PORT || 8080;

app.use(cors());

// Add headers before the routes are defined
app.use(function (req, res, next) {

  // Website you wish to allow to connect
  res.setHeader('Access-Control-Allow-Origin', '*');

  // Request methods you wish to allow
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

  // Request headers you wish to allow
  res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

  // Set to true if you need the website to include cookies in the requests sent
  // to the API (e.g. in case you use sessions)
  res.setHeader('Access-Control-Allow-Credentials', true);
  console.log('cors');
  // Pass to next layer of middleware
  next();
});

app.use(express.static(__dirname + '/dist/frontend'));

app.get('/*', (req, res) => res.sendFile(path.join(__dirname)));

const server = http.createServer(app);

server.listen(port, () => console.log(`Frontend iniciado: http://localhost:${port}`));

