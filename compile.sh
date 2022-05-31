#!/usr/bin/env bash

# compilar
source ~/.profile

printf 'Starting Backend'
cd backend
mvn clean install

printf 'Starting Frontend'
cd ../frontend
ng build
