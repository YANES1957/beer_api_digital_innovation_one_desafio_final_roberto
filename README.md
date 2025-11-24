🔴 Beer API - Gerenciamento de Estoque de Cervejas 🍺🚀

 *Sobre o projeto

Este projeto é uma API REST desenvolvida em Spring Boot para gerenciamento de estoques de cerveja. Criado com foco em testes unitários, TDD e QA Automation, serve como exemplo prático para portfólio e estudos avançados de testes automatizados.

Funcionalidades principais:

🍺 Criação de cervejas no estoque

📜 Listagem de cervejas

🔍 Consulta por nome

❌ Exclusão de cervejas

Organizado em camadas estruturadas, garantindo manutenção simples e escalabilidade.

🔴 Tecnologias e frameworks utilizados

🟢 Spring Boot: desenvolvimento da API

🟢 H2 Database: banco de dados em memória

🟢 JUnit: testes unitários

🟢 Mockito: simulação de dependências

🟢 Cucumber: testes BDD (Behavior Driven Development)

🟢 Homecrest: geração de dados de teste

🟢 Postman: testes manuais de endpoints

🟢 Swagger: documentação interativa da API

🔴 Testes automatizados

O projeto inclui testes unitários e BDD para validar todas as funcionalidades:

✅ JUnit + Mockito: validação da lógica de negócio

✅ Cucumber: testes de integração e aceitação, seguindo TDD

✅ Homecrest: dados de teste automatizados

Esses testes garantem que criação, listagem, consulta e exclusão de cervejas funcionem corretamente mesmo após alterações no código.

🔴 Testes finais da API

Além dos testes automatizados, os endpoints foram validados com:

🟠 Postman: simulação de requisições HTTP para verificar todas as funcionalidades

🟠 Swagger: documentação interativa e prática para explorar os endpoints

Esses testes complementam a prática de QA Automation e asseguram o funcionamento real da aplicação.

🔴 Estrutura do projeto
beer_api_digital_innovation_one
│
├─ src/main/java
│   ├─ controller    -> endpoints da API
│   ├─ service       -> lógica de negócio
│   ├─ repository    -> interação com o banco de dados
│   ├─ model         -> entidades
│   ├─ dto           -> objetos de transferência
│   ├─ mapper        -> conversão entre entidades e DTOs
│   └─ config        -> configurações do projeto
│
├─ src/test/java
│   ├─ service       -> testes unitários
│   ├─ steps         -> steps do Cucumber
│   └─ bdd           -> testes de integração BDD
│
├─ pom.xml           -> dependências do Maven
└─ README.md         -> documentação do projeto

🔴 Finalidade do projeto

Este projeto serve para:

🚀 Praticar TDD (Test Driven Development)

🧪 Criar testes unitários e automatizados

🌐 Demonstrar conhecimento em Spring Boot e endpoints REST

💻 Criar um portfólio completo, incluindo funcionalidades, testes e documentação

🔴 Autor
 Roberto Césaer Yanes
 
🔴Meu GitHub: YANES1957

