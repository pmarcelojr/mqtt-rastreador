const express = require('express');
const cors = require('cors');

const app = express();
const port = process.env.WEB_PORT || 4200;
const www = __dirname + '/dist/frontend';

app.use(cors());

app.use(express.static(www));

app.all('/*', (req, res, next) => {
  res.sendFile('index.html', { root: www });
});

console.log(`Servidor iniciado na porta ${port}`);
app.listen(port);
