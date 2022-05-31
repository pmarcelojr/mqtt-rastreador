@echo off
cd backend
mvn clean install

cd ../frontend
ng build
