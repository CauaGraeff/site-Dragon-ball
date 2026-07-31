# 📦 Guia de Instalação - Dragon Ball API

## ✅ Pré-requisitos

### Java 17+
O Java já está instalado no seu sistema:
```
java version "21.0.7"
```

### Maven (⚠️ Precisa instalar)
Maven não foi detectado. Siga os passos abaixo:

---

## 🔧 Instalação do Maven

### Passo 1: Download
1. Acesse: https://maven.apache.org/download.cgi
2. Baixe a versão **Binary zip archive** (exemplo: `apache-maven-3.9.5-bin.zip`)

### Passo 2: Extração
1. Extraia o arquivo em um local permanente, como:
   - `C:\Program Files\apache-maven-3.9.5`
   - ou `C:\DevTools\maven`

### Passo 3: Adicionar ao PATH (Windows)

#### Método 1: GUI (Recomendado)
1. Abra **Variáveis de Ambiente**:
   - Pressione `Win + X` → Selecione "Sistema"
   - Clique em "Configurações avançadas do sistema"
   - Clique no botão "Variáveis de Ambiente"

2. Em **Variáveis do sistema**, clique em **Novo**:
   - Nome da variável: `MAVEN_HOME`
   - Valor: `C:\Program Files\apache-maven-3.9.5` (ajuste o caminho)

3. Edite a variável **Path**:
   - Clique em "Editar"
   - Clique em "Novo"
   - Adicione: `%MAVEN_HOME%\bin`

4. Clique em OK em todas as janelas

5. **Reinicie o computador** ou termine todas as abas do PowerShell/CMD

#### Método 2: PowerShell (Avançado)
```powershell
# Execute como Administrador
$mavenPath = "C:\Program Files\apache-maven-3.9.5\bin"
$currentPath = [Environment]::GetEnvironmentVariable("Path", [EnvironmentVariableTarget]::Machine)
$newPath = "$currentPath;$mavenPath"
[Environment]::SetEnvironmentVariable("Path", $newPath, [EnvironmentVariableTarget]::Machine)
```

### Passo 4: Verificar Instalação
```bash
mvn --version
```

Você deve ver:
```
Apache Maven 3.9.5
Java version: 21.0.7
```

---

## 🚀 Executar a Aplicação

### Opção 1: Script Automático (Recomendado)
No Windows, clique duas vezes em:
- `run.bat` (Prompt de Comando)
- ou `run.ps1` (PowerShell)

### Opção 2: Linha de Comando

Abra **PowerShell** ou **Prompt de Comando** na pasta do projeto:

```bash
# Compilar e instalar dependências
mvn clean install

# Executar a aplicação
mvn spring-boot:run
```

### Opção 3: Executável JAR
```bash
# Compilar JAR
mvn clean package

# Executar
java -jar target/api-dragonball-1.0.jar
```

---

## 🌐 Acessar a Aplicação

Após a aplicação iniciar, abra o navegador e acesse:
```
http://localhost:8080
```

Você verá:
1. Dropdown com personagens de Dragon Ball
2. Campo de informações (Nome, Ki, Max Ki, Raça)
3. Imagem do personagem selecionado

---

## 🐛 Solução de Problemas

### Erro: "mvn: O termo não é reconhecido"
- Maven não está no PATH
- **Solução**: Reinicie o PowerShell/CMD após adicionar ao PATH

### Erro: "JAVA_HOME não está definido"
- Variável de ambiente do Java não está configurada
- **Solução**: Adicione `JAVA_HOME=C:\Program Files\Java\jdk-21`

### Porta 8080 já em uso
- Outra aplicação está usando a porta
- **Solução**: Altere em `src/main/resources/application.properties`:
  ```properties
  server.port=8081
  ```

### Erro de conexão com API
- Verifique sua conexão de internet
- A API externa `dragonball-api.com` pode estar indisponível
- **Aguarde alguns minutos** e tente novamente

---

## 📁 Estrutura Final

```
apidragonball/
├── pom.xml
├── run.bat
├── run.ps1
├── README_COMPLETO.md
├── GUIA_INSTALACAO.md (este arquivo)
└── src/
    ├── Main.java
    ├── Controllers/
    │   └── CharacterController.java
    ├── Services/
    │   └── CharacterService.java
    ├── Modelos/
    │   ├── Titulo.java
    │   └── TituloRecord.java
    └── main/
        └── resources/
            ├── application.properties
            └── static/
                ├── index.html
                ├── style.css
```

---

## ✨ Pronto!

A aplicação deve estar rodando perfeitamente! 🎉

Selecione um personagem no dropdown e veja seus dados aparecerem automaticamente.

---

**Dúvidas?** Verifique o `README_COMPLETO.md` para mais detalhes sobre o código.
