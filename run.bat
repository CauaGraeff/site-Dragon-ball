@echo off
REM Script para compilar e executar a aplicação Dragon Ball API

echo =================================
echo Dragon Ball API - Setup
echo =================================
echo.

REM Verificar se Maven está instalado
mvn --version >nul 2>&1
if errorlevel 1 (
    echo.
    echo [!] Maven não está instalado.
    echo.
    echo Para instalar Maven:
    echo.
    echo 1. Faça download em: https://maven.apache.org/download.cgi
    echo 2. Extraia o arquivo ZIP
    echo 3. Adicione ao PATH do Windows a pasta bin do Maven
    echo.
    echo Após instalar Maven, execute novamente este script.
    echo.
    pause
    exit /b 1
)

echo [OK] Maven encontrado
echo.

REM Compilar o projeto
echo Compilando projeto...
call mvn clean install -DskipTests

if errorlevel 1 (
    echo.
    echo [ERRO] Falha na compilação
    pause
    exit /b 1
)

echo.
echo [OK] Projeto compilado com sucesso!
echo.
echo Iniciando aplicação na porta 8080...
echo Abra o navegador em: http://localhost:8080
echo.

REM Executar a aplicação
call mvn spring-boot:run

pause
