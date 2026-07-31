# Dragon Ball API - Aplicação Web

Uma aplicação web full-stack que conecta um backend Java (Spring Boot) com um frontend HTML/CSS/JavaScript para exibir informações de personagens de Dragon Ball.

## 📋 Estrutura do Projeto

```
apidragonball/
├── pom.xml                                    # Dependências Maven
├── src/
│   ├── Main.java                              # Aplicação Spring Boot
│   ├── Controllers/
│   │   └── CharacterController.java           # Controller REST
│   ├── Services/
│   │   └── CharacterService.java              # Lógica de consumo da API
│   ├── Modelos/
│   │   ├── Titulo.java                        # Modelo de Personagem
│   │   └── TituloRecord.java                  # Record para mapeamento JSON
│   └── main/resources/
│       ├── application.properties             # Configuração Spring Boot
│       └── static/
│           ├── index.html                     # Frontend com JavaScript
│           └── style.css                      # Estilos CSS
```

## 🔧 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.8+

### Passos

1. **Compilar o projeto:**
```bash
mvn clean install
```

2. **Executar a aplicação:**
```bash
mvn spring-boot:run
```

3. **Acessar no navegador:**
```
http://localhost:8080
```

## 📖 Explicação do Código

### Backend (Java)

#### **Main.java**
- Inicializa a aplicação Spring Boot
- Expõe a aplicação web na porta 8080

#### **CharacterController.java**
- Controller REST com endpoint `GET /api/characters`
- Recebe parâmetro `name` do personagem
- Injeta `CharacterService` para buscar dados
- Retorna JSON com informações do personagem

#### **CharacterService.java**
- Consumidor de API HTTP para `https://dragonball-api.com/api/characters`
- Realiza requisições GET com o nome do personagem
- Converte resposta JSON para objetos usando GSON
- Trata erros e validações

#### **Titulo.java** (Refatorado)
- Modelo que representa um personagem
- Agora extrai apenas o primeiro resultado da lista de personagens
- Simples e limpo, sem lógica desnecessária
- Getters para acesso aos atributos

#### **TituloRecord.java**
- Record do Java 17 que mapeia a estrutura JSON da API
- Atributos: name, ki, maxKi, race, image

### Frontend (HTML/CSS/JavaScript)

#### **index.html**
- Dropdown com lista de personagens
- Contêiner para exibir dados do personagem
- **JavaScript integrado:**
  - Listener no dropdown para detectar mudanças
  - Fetch API para fazer requisições ao backend (`/api/characters?name=...`)
  - Atualiza dados e imagem dinamicamente
  - Tratamento de erros e mensagens de carregamento

#### **style.css** (Melhorado)
- Design responsivo com Grid CSS
- Gradientes modernos
- Animações suaves (fadeIn, pulse)
- Suporte mobile com media queries
- Hover effects e transições

## 🎨 Melhorias Implementadas

1. **Conexão Frontend-Backend**
   - JavaScript com Fetch API
   - CORS habilitado no Controller
   - Requisições GET assíncronas

2. **Refatoração de Código**
   - Simplificação do construtor `Titulo`
   - Removido uso desnecessário de `ArrayList`
   - Melhor estrutura com padrão Service-Controller
   - Nomes mais claros (Titulo → Character no toString)

3. **UX Melhorada**
   - Mensagem de carregamento
   - Tratamento de erros
   - Animações suaves
   - Design responsivo
   - Seleção clara de campo/label

4. **Segurança**
   - CORS configurado
   - Validação de entrada
   - Tratamento de exceções

## 🚀 API Endpoint

**GET** `/api/characters`

Parâmetro:
- `name` (String): Nome do personagem

Resposta (JSON):
```json
{
    "nome": "Goku",
    "ki": "60000",
    "maxKi": "90000",
    "race": "Saiyan",
    "image": "https://..."
}
```

## 📦 Dependências

- **Spring Boot 3.1.5**: Framework web
- **GSON 2.10.1**: Serialização JSON
- **Java HTTP Client**: Para requisições HTTP

## 📝 Notas

- A aplicação consome dados da API pública: `https://dragonball-api.com/api/characters`
- Todos os personagens da série estão disponíveis no dropdown
- As imagens são carregadas diretamente da API externa

---

**Desenvolvido com ❤️**
