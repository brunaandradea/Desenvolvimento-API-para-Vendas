# Desafio de API de Vendas - Processo Seletivo

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x.x-green)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red)

API REST desenvolvida como desafio técnico para um processo seletivo de Desenvolvedor(a) Backend. A aplicação gerencia o cadastro de vendas e expõe um endpoint que calcula a performance dos vendedores (total e média diária de vendas) por período.

## Índice

- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Documentação da API](#documentação-da-api)
- [Como Rodar os Testes](#como-rodar-os-testes)
- [Decisões de Projeto](#decisões-de-projeto)

## Funcionalidades

- ✅ Cadastro de novas vendas.
- ✅ Consulta consolidada da performance de vendedores, retornando nome, total de vendas e média diária de vendas em um período específico.

## Tecnologias Utilizadas

- **Java 17**: Linguagem principal do projeto.
- **Spring Boot 3.x**: Framework para criação da API REST.
- **Spring Data JPA**: Para persistência de dados e abstração de consultas ao banco.
- **Maven**: Gerenciador de dependências e build do projeto.
- **H2 Database**: Banco de dados relacional em memória para simplicidade e portabilidade.
- **Lombok**: Para reduzir código boilerplate (getters, setters, construtores).
- **JUnit 5 & Mockito**: Para a implementação de testes unitários automatizados.

## Como Executar o Projeto

Siga os passos abaixo para executar a aplicação localmente.

### Pré-requisitos

- Java (JDK) 17 ou superior.
- Apache Maven 3.8 ou superior.

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/brunaandradea/Desenvolvimento-API-para-Vendas.git](https://github.com/brunaandradea/Desenvolvimento-API-para-Vendas.git)
    ```

2.  **Navegue até o diretório do projeto:**
    ```bash
    cd vendas-api
    ```

3.  **Execute a aplicação com o Maven Wrapper:**
    * No Linux/Mac:
        ```bash
        ./mvnw spring-boot:run
        ```
    * No Windows:
        ```bash
        mvnw.cmd spring-boot:run
        ```

A API estará disponível em `http://localhost:8080`.

## Documentação da API

A seguir estão os detalhes dos endpoints disponíveis.

### 1. Criar uma Nova Venda

-   **Método:** `POST`
-   **URL:** `/api/vendas`
-   **Descrição:** Registra uma nova venda no sistema. A data da venda é definida automaticamente como a data atual do servidor.

-   **Corpo da Requisição (`Body`):**
    ```json
    {
      "valor": 250.75,
      "vendedorId": 1,
      "vendedorNome": "Ana"
    }
    ```

-   **Exemplo de Uso (`curl`):**
    ```bash
    curl -X POST http://localhost:8080/api/vendas \
    -H "Content-Type: application/json" \
    -d '{
          "valor": 250.75,
          "vendedorId": 1,
          "vendedorNome": "Ana"
        }'
    ```

-   **Resposta de Sucesso (`201 Created`):**
    ```json
    {
      "id": 1,
      "dataVenda": "2025-09-16",
      "valor": 250.75,
      "vendedorId": 1,
      "vendedorNome": "Ana"
    }
    ```

---

### 2. Listar Performance dos Vendedores

-   **Método:** `GET`
-   **URL:** `/api/vendedores`
-   **Descrição:** Retorna uma lista com o nome de cada vendedor, o total de vendas realizadas e a média diária de vendas, com base em um período informado.

-   **Parâmetros de URL (`Query Params`):**
    -   `dataInicio` (obrigatório): Data de início do período no formato `YYYY-MM-DD`.
    -   `dataFim` (obrigatório): Data de fim do período no formato `YYYY-MM-DD`.

-   **Exemplo de Uso (`curl`):**
    ```bash
    curl "http://localhost:8080/api/vendedores?dataInicio=2025-09-01&dataFim=2025-09-30"
    ```

-   **Resposta de Sucesso (`200 OK`):**
    ```json
    [
      {
        "nomeVendedor": "Ana",
        "totalVendas": 5,
        "mediaVendasDiaria": 350.50
      },
      {
        "nomeVendedor": "Carlos",
        "totalVendas": 3,
        "mediaVendasDiaria": 180.00
      }
    ]
    ```

## Como Rodar os Testes

Para garantir a qualidade do código e a corretude da lógica de negócio, foram implementados testes unitários. Para executá-los, utilize o seguinte comando na raiz do projeto:

```bash
./mvnw test
 ```

## Decisões de Projeto

-   **Simplicidade do Modelo:** Optei por não criar uma entidade `Vendedor` separada, incluindo os campos `vendedorId` e `vendedorNome` diretamente na entidade `Venda`. Esta abordagem simplifica o modelo, atendendo diretamente aos requisitos do desafio sem adicionar complexidade desnecessária.

-   **Banco de Dados em Memória:** O uso do H2 foi escolhido para simplificar a configuração e execução do projeto, eliminando a necessidade de um ambiente de banco de dados externo para a avaliação.

-   **DTOs (Data Transfer Objects):** A utilização de DTOs (`VendaRequestDTO` e `VendedorPerformanceDTO`) foi adotada para desacoplar a representação da API da entidade do banco de dados, o que é uma boa prática para manter a flexibilidade e a segurança da aplicação.

---

**Autor**

Bruna Andrade Alves
