# ScreamMatch

ScreamMatch é uma aplicação feita em Java que permite buscar informações sobre séries, temporadas e episódios utilizando a API OMDb
O projeto conta com versões com e sem camada Web, oferecendo tanto uma interface em linha de comando quanto integração com banco de dados através de repositórios Spring Data
A aplicação permite consultar séries, armazená-las, buscar episódios, filtrar informações e analisar dados como avaliações, categorias e datas de lançamento

## Funcionalidades

- Buscar séries diretamente da API OMDb
- Listar séries já armazenadas no banco
- Buscar temporadas e episódios de uma série específica
- Filtrar séries por título, ator, avaliação e gênero
- Listar as cinco séries mais bem avaliadas
- Consultar episódios por trecho do nome
- Listar episódios lançados após um determinado ano
- Exibir os episódios mais bem avaliados de cada série
- Armazenar séries e episódios utilizando repositórios Spring

## Tecnologias utilizadas

- Java
- Spring Data JPA
- API OMDb
- Streams e Collections
- Maven
- Banco de dados relacional (dependendo da versão utilizada)

## Estrutura geral

- model/ – Entidades principais (Série, Episódio, categorias etc.)
- service/ – Serviços de consumo da API e conversão de dados
- repository/ – Interfaces JPA para persistência
- principal/ – Lógica de interação com o usuário (CLI)

## Como executar

1. Configure seu banco de dados e variáveis necessárias no application.properties (para a versão web).
2. Execute o projeto via IDE ou linha de comando: mvn spring-boot:run
3. ou, para a versão sem web: java -jar scream-match.jar
4. Utilize o menu exibido no terminal para navegar entre as opções de consulta.

## Sobre o projeto

Este projeto foi desenvolvido como parte dos estudos de Java, com foco em consumo de API, manipulação de dados, interação com banco, uso de Streams e boas práticas de organização de código


