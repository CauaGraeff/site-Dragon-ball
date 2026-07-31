# Script PowerShell para compilar e executar a aplicação Dragon Ball API

Write-Host "=================================" -ForegroundColor Cyan
Write-Host "Dragon Ball API - Setup" -ForegroundColor Cyan
Write-Host "=================================" -ForegroundColor Cyan
Write-Host ""

# Verificar se Maven está instalado
$mavenCheck = mvn --version 2>$null
if (-not $?) {
    Write-Host "[!] Maven não está instalado." -ForegroundColor Red
    Write-Host ""
    Write-Host "Para instalar Maven:" -ForegroundColor Yellow
    Write-Host "1. Faça download em: https://maven.apache.org/download.cgi" -ForegroundColor White
    Write-Host "2. Extraia o arquivo ZIP" -ForegroundColor White
    Write-Host "3. Adicione ao PATH do Windows a pasta bin do Maven" -ForegroundColor White
    Write-Host ""
    Write-Host "Após instalar Maven, execute novamente este script." -ForegroundColor Yellow
    Write-Host ""
    Read-Host "Pressione Enter para sair"
    exit 1
}

Write-Host "[OK] Maven encontrado" -ForegroundColor Green
Write-Host ""

# Compilar o projeto
Write-Host "Compilando projeto..." -ForegroundColor Cyan
mvn clean install -DskipTests

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "[ERRO] Falha na compilação" -ForegroundColor Red
    Read-Host "Pressione Enter para sair"
    exit 1
}

Write-Host ""
Write-Host "[OK] Projeto compilado com sucesso!" -ForegroundColor Green
Write-Host ""
Write-Host "Iniciando aplicação na porta 8080..." -ForegroundColor Cyan
Write-Host "Abra o navegador em: http://localhost:8080" -ForegroundColor Yellow
Write-Host ""

# Executar a aplicação
mvn spring-boot:run

Read-Host "Pressione Enter para sair"
