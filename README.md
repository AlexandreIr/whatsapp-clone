# 💬 WhatsApp Clone  

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![Angular](https://img.shields.io/badge/Angular-19-red?logo=angular)
![Keycloak](https://img.shields.io/badge/Keycloak-Auth-blue?logo=keycloak)
![PrimeNG](https://img.shields.io/badge/PrimeNG-UI-purple?logo=primefaces)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker)
![License](https://img.shields.io/badge/License-Apache%202.0-lightgrey)

> Clone da aplicação **WhatsApp**, com autenticação Keycloak, back-end em Spring Boot 3 e front-end em Angular 19.  
> Projeto criado para fins de aprendizado e demonstração de arquitetura moderna full stack.

---

## 🧭 Índice
- [Visão Geral](#-visão-geral)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação & Execução](#-instalação--execução)
- [Configuração](#-configuração)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Roadmap / Melhorias Futuras](#-roadmap--melhorias-futuras)
- [Licença](#-licença)

---

## 🌍 Visão Geral
Este projeto é uma implementação de **chat em tempo real** inspirada no WhatsApp.  
A aplicação permite autenticação, listagem de usuários, troca de mensagens e uma interface moderna e responsiva.

💡 **Objetivo:** demonstrar uma arquitetura completa e moderna com autenticação segura e comunicação reativa.

---

## ⚙️ Funcionalidades

✅ Login e autenticação via **Keycloak**  
✅ Registro e gerenciamento de usuários  
✅ Listagem de contatos e status online  
✅ Envio e recebimento de mensagens em “tempo real”  
✅ Interface moderna e responsiva com **PrimeNG**  
✅ Arquitetura separada (frontend + backend)  
✅ Dockerização completa com `docker-compose.yml`

---

## 🧩 Tecnologias Utilizadas

| Camada | Tecnologias |
|--------|--------------|
| **Back-end** | Java 17 +, Spring Boot 3, Maven, JPA/Hibernate |
| **Front-end** | Angular 19, TypeScript, SCSS, PrimeNG |
| **Autenticação** | Keycloak (OpenID Connect) |
| **Banco de Dados** | PostgreSQL *(ou conforme definido)* |
| **Infraestrutura** | Docker, Docker Compose |
| **Ferramentas** | VS Code / IntelliJ, Postman, Node.js |

---

## 🧰 Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:
- [Java 17+](https://adoptium.net/)
- [Node.js + npm](https://nodejs.org/)
- [Docker & Docker Compose](https://www.docker.com/)
- [Git](https://git-scm.com/)

---

## 🚀 Instalação & Execução

### 1️⃣ Clonar o repositório
```bash
git clone https://github.com/AlexandreIr/whatsapp-clone.git
cd whatsapp-clone
```

### 2️⃣ Rodar com Docker
```bash
docker-compose up --build
```
Isso inicializará:
- Back-end (Spring Boot)
- Front-end (Angular)
- Keycloak
- Banco de dados (caso configurado)

### 3️⃣ Rodar manualmente (sem Docker)

#### Back-end
```bash
cd whatsappclone
./mvnw spring-boot:run
```

#### Front-end
```bash
cd whatsapp-clone-ui
npm install
npm start
```

Acesse no navegador: 👉 [http://localhost:4200](http://localhost:4200)

---

## ⚙️ Configuração

### 🔐 Keycloak
- Configure o *realm*, *clients* e *roles* de acordo com o arquivo de inicialização.  
- Atualize as URLs de redirecionamento conforme ambiente local ou produção.

### 🗂️ Back-end (`application.yml`)
- Defina a URL do Keycloak, banco de dados e CORS do front-end.

### 💻 Front-end (`environment.ts`)
- Atualize as variáveis de ambiente:
```ts
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api',
  keycloakUrl: 'http://localhost:8080/auth'
};
```

---

## 🧱 Estrutura do Projeto

```
whatsapp-clone/
├── docker-compose.yml
├── whatsappclone/              # Back-end (Spring Boot)
│   ├── src/
│   ├── pom.xml
│   └── ...
├── whatsapp-clone-ui/          # Front-end (Angular)
│   ├── src/
│   ├── package.json
│   └── ...
└── LICENSE
```

---

## 🛠️ Roadmap / Melhorias Futuras

- 📸 Envio de imagens, áudios e vídeos  
- 🔒 Criptografia ponta-a-ponta  
- 📱 Aplicativo mobile (Ionic / React Native)  
- 📊 Dashboard administrativo  
- 🔔 Notificações push  
- 💬 Suporte a grupos e mensagens em massa  
- 🧩 Microsserviços e mensageria (Kafka / RabbitMQ)  
- 📈 Monitoramento com Prometheus + Grafana  

---

## 📜 Licença

Distribuído sob a licença **Apache 2.0**.  
Consulte o arquivo [LICENSE](./LICENSE) para mais informações.

---

## 🌟 Autor

**[Alexandre Fernandes](https://github.com/AlexandreIr)**  
💼 Desenvolvedor Full Stack | Java • Angular • Docker • Keycloak  

---

### 🧷 Clone, explore e contribua!
```bash
git clone https://github.com/AlexandreIr/whatsapp-clone.git
```

> ⭐ Se este projeto te ajudou, não esqueça de deixar uma **star** no repositório!
