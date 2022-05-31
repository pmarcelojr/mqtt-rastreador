@echo off
echo PAUSANDO SISTEMA CASO ESTEJA RODANDO
docker-compose -f docker-compose.yml down

echo INICIALIZANDO SISTEMA
docker-compose -f docker-compose.yml up
