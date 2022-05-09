#!/usr/bin/env bash

# compilar
cd backend
mvn clean install

cd ../frontend
ng build
