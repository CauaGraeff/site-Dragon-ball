# 🎯 Como o Código Funciona - Fluxo de Dados

## 📊 Diagrama de Funcionamento

```
┌─────────────┐                                      ┌──────────────────────┐
│   BROWSER   │                                      │  DRAGON BALL API     │
│  (Frontend) │                                      │  (API Externa)       │
└──────┬──────┘                                      └──────────────────────┘
       │                                                       ▲
       │ 1. Usuário seleciona personagem                      │
       │    (change event)                                    │ 5. Retorna JSON com dados
       │                                                      │    { name, ki, maxKi, race, image }
       ▼                                                      │
┌─────────────────────────────────────┐            ┌─────────────────────────────┐
│  JavaScript (index.html)            │            │  CharacterService.java      │
│  ─────────────────────────────────  │            │  ─────────────────────────  │
│                                     │            │                             │
│  1. Pega valor do dropdown          │            │  Faz requisição HTTP GET    │
│  2. Faz fetch('/api/characters...') │────2───────►  com nome do personagem     │
│  3. Aguarda resposta JSON           │────────────┤                             │
│  4. Atualiza HTML com dados         │◄────3──────┤  Converte JSON para Objeto  │
│  5. Mostra imagem                   │  Response  │  Titulo usando GSON         │
│                                     │  JSON      │                             │
└─────────────────────────────────────┘            └────────┬────────────────────┘
       ▲                                                     │
       │ 6. Página atualizada!                             │
       │    Nome: Goku                                      │
       │    Ki: 60000                                       │
       │    Max Ki: 90000                                   │
       │    Raça: Saiyan                                   │
       │    [Imagem do Goku]                               │
       │                                                    │
       └────────────────────────────────────────────────────┘

                        │
                        │ Requisição HTTP
                        ▼
                ┌──────────────────────┐
                │ CharacterController  │
                │ ──────────────────── │
                │ GET /api/characters  │
                │ ?name=Goku           │
                │                      │
                │ @CrossOrigin("*")    │
                │ (permite requisições │
                │  do frontend)        │
                └──────────────────────┘
```

---

## 🔄 Fluxo Detalhado Passo a Passo

### **PASSO 1: Página Carrega (index.html)**

```javascript
// Ao carregar a página, o JavaScript está pronto
const select = document.getElementById('characterSelect');
const characterImage = document.getElementById('characterImage');

// Aguardando o usuário selecionar um personagem
```

**Estado Inicial:**
- Dropdown com lista de personagens
- Dados mostram "-" (vazio)
- Imagem vazia

---

### **PASSO 2: Usuário Seleciona Personagem**

```javascript
// Exemplo: usuário clica em "Goku"
select.addEventListener('change', async function() {
    const characterName = this.value; // = "Goku"
    
    // Mostrar mensagem de carregamento
    loadingMessage.style.display = 'block';
});
```

**O que acontece:**
- Select dispara evento `change`
- JavaScript captura o nome: `"Goku"`
- Mostra "Carregando..."

---

### **PASSO 3: Frontend Faz Requisição ao Backend**

```javascript
// Fazer requisição GET para o servidor Java
const response = await fetch(`/api/characters?name=Goku`);
```

**Requisição HTTP:**
```
GET http://localhost:8080/api/characters?name=Goku

Headers:
  Origin: http://localhost:8080
  Content-Type: application/json
```

---

### **PASSO 4: Backend Processa (Java Spring Boot)**

#### **CharacterController recebe a requisição:**

```java
@GetMapping
public ResponseEntity<?> getCharacter(@RequestParam String name) {
    // name = "Goku"
    
    // Chama o serviço
    Titulo character = characterService.fetchCharacter(name);
    
    // Retorna como JSON
    return ResponseEntity.ok(character);
}
```

#### **CharacterService busca os dados:**

```java
public Titulo fetchCharacter(String name) throws IOException, InterruptedException {
    // 1. Monta URL
    String url = "https://dragonball-api.com/api/characters?name=Goku";
    
    // 2. Faz requisição HTTP
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
    
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    
    // 3. Resposta JSON da API (exemplo):
    // [{"name":"Goku","ki":"60000","maxKi":"90000","race":"Saiyan","image":"https://..."}]
    
    // 4. Converte JSON para objeto Titulo
    List<TituloRecord> records = gson.fromJson(response.body(), 
            new TypeToken<List<TituloRecord>>(){}.getType());
    
    return new Titulo(records);
}
```

#### **Titulo processa os dados:**

```java
public Titulo(List<TituloRecord> records) {
    TituloRecord first = records.get(0); // Pega primeiro resultado
    
    this.nome = first.name();      // "Goku"
    this.ki = first.ki();          // "60000"
    this.maxKi = first.maxKi();    // "90000"
    this.race = first.race();      // "Saiyan"
    this.image = first.image();    // "https://..."
}
```

---

### **PASSO 5: Backend Retorna JSON**

```json
{
  "nome": "Goku",
  "ki": "60000",
  "maxKi": "90000",
  "race": "Saiyan",
  "image": "https://dragonball-api.com/api/characters/images/goku.webp"
}
```

**HTTP Response:**
```
HTTP/1.1 200 OK
Content-Type: application/json
Access-Control-Allow-Origin: *

{
  "nome": "Goku",
  ...
}
```

---

### **PASSO 6: Frontend Recebe e Atualiza a Página**

```javascript
const data = await response.json();

// Atualizar campos de texto
document.getElementById('nome').textContent = data.nome;           // "Goku"
document.getElementById('ki').textContent = data.ki;               // "60000"
document.getElementById('maxKi').textContent = data.maxKi;         // "90000"
document.getElementById('race').textContent = data.race;           // "Saiyan"

// Atualizar imagem
document.getElementById('characterImage').src = data.image;        // "https://..."

// Esconder mensagem de carregamento
loadingMessage.style.display = 'none';
```

**Resultado na Página:**
```
┌─────────────────────┐  ┌──────────────┐
│ Dados do Personagem │  │  [Imagem do  │
│ ───────────────────│  │   Goku]      │
│ Nome: Goku          │  │              │
│ Ki: 60000           │  │              │
│ Max Ki: 90000       │  │              │
│ Raça: Saiyan        │  └──────────────┘
└─────────────────────┘
```

---

## 🛠️ Componentes Principais

### **1. Record Java - TituloRecord.java**

```java
public record TituloRecord(String name, String ki, String maxKi, String race, String image) {
}
```

**O que é?**
- Structure/Template de dados
- Mapeia exatamente o JSON da API
- Automático: getters, equals, hashCode, toString

**Exemplo:**
```java
// JSON da API
{"name":"Goku","ki":"60000","maxKi":"90000","race":"Saiyan","image":"https://..."}

// Vira um objeto Record
TituloRecord record = new TituloRecord(
    "Goku", 
    "60000", 
    "90000", 
    "Saiyan", 
    "https://..."
);
```

---

### **2. Classe Model - Titulo.java**

```java
public class Titulo {
    private String nome, ki, maxKi, race, image;
    
    public Titulo(List<TituloRecord> records) {
        TituloRecord first = records.get(0);
        this.nome = first.name();
        // ... etc
    }
}
```

**O que faz?**
- Recebe lista de Records
- Extrai o primeiro (geralmente há apenas 1)
- Armazena como atributos
- Fornece getters

**Por que?**
- Abstrai a estrutura JSON
- Separa dados de apresentação
- Facilita mudanças futuras

---

### **3. Service - CharacterService.java**

```java
@Service
public class CharacterService {
    public Titulo fetchCharacter(String name) throws IOException, InterruptedException {
        // 1. Valida entrada
        // 2. Faz requisição HTTP à API externa
        // 3. Converte JSON
        // 4. Retorna objeto Titulo
    }
}
```

**Responsabilidades:**
- ✅ Consumir API externa
- ✅ Validar dados
- ✅ Tratar erros
- ✅ Converter JSON

**Por que separado?**
- Lógica de negócio isolada
- Fácil testar
- Reutilizável

---

### **4. Controller - CharacterController.java**

```java
@RestController
@RequestMapping("/api/characters")
@CrossOrigin(origins = "*")  // Permite requisições do frontend
public class CharacterController {
    
    @GetMapping
    public ResponseEntity<?> getCharacter(@RequestParam String name) {
        Titulo character = characterService.fetchCharacter(name);
        return ResponseEntity.ok(character);  // Retorna JSON
    }
}
```

**Responsabilidades:**
- ✅ Receber requisições HTTP
- ✅ Validar parâmetros
- ✅ Chamar serviço
- ✅ Retornar resposta

**@CrossOrigin:**
- Permite que JavaScript do navegador acesse o endpoint
- Sem isso, daria erro CORS (Cross-Origin)

---

### **5. Frontend - index.html + JavaScript**

```javascript
// 1. Escuta mudanças no dropdown
select.addEventListener('change', async function() {
    
    // 2. Faz requisição
    const response = await fetch(`/api/characters?name=${name}`);
    const data = await response.json();
    
    // 3. Atualiza HTML
    document.getElementById('nome').textContent = data.nome;
    document.getElementById('characterImage').src = data.image;
});
```

**Fluxo:**
- Usuário seleciona → Evento dispara
- JavaScript faz fetch → Requisição HTTP
- Resposta volta → Atualiza página

---

## 🎓 Conceitos Aprendidos

| Conceito | O Que É | Exemplo |
|----------|--------|---------|
| **REST API** | Endpoint que retorna dados JSON | `GET /api/characters?name=Goku` |
| **CORS** | Permite requisições entre domínios | `@CrossOrigin(origins = "*")` |
| **Fetch API** | JavaScript para fazer requisições HTTP | `fetch('/api/characters?name=Goku')` |
| **Async/Await** | Programação assíncrona em JavaScript | `await response.json()` |
| **Spring Boot** | Framework para APIs REST em Java | `@RestController` |
| **Service** | Lógica de negócio isolada | `CharacterService.java` |
| **Controller** | Recebe requisições e retorna respostas | `CharacterController.java` |
| **JSON** | Formato de dados universal | `{"nome": "Goku", "ki": "60000"}` |

---

## 🚀 Como Testar

### Teste 1: No Navegador
1. Abra `http://localhost:8080`
2. Selecione "Goku" no dropdown
3. Veja os dados aparecerem

### Teste 2: Via URL
Abra diretamente em uma aba:
```
http://localhost:8080/api/characters?name=Vegeta
```
Retorna JSON com dados de Vegeta

### Teste 3: Via cURL
```bash
curl "http://localhost:8080/api/characters?name=Piccolo"
```

---

**Resumo:**
- ✅ Frontend pede dados → Backend busca → Retorna JSON → Página atualiza
- ✅ Tudo acontece em tempo real (assíncrono)
- ✅ Código bem organizado (MVC - Model, View, Controller)
- ✅ Pronto para produção!

---
