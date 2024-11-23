# Gerenciamento de Pedidos

Este projeto demonstra a construção de um microsserviço para gerenciamento de pedidos utilizando RabbitMQ, Spring Boot e MongoDB. O fluxo principal consiste em consumir mensagens de uma fila no RabbitMQ, processar os dados no microsserviço Spring, e persistir as informações no banco MongoDB. Além disso, disponibiliza uma API RESTful para consulta de pedidos, com funcionalidades como listagem, cálculos de valores agregados e análise por cliente.

## 🚀 Tecnologias Utilizadas
O projeto utiliza as seguintes tecnologias:

- Java 17
- Spring Boot 3
- Spring Data MongoDB
- RabbitMQ
- Docker

## 📌 Funcionalidades
**1. Consumo de Pedidos**
- Processar pedidos recebidos via fila do RabbitMQ.
- Persistir informações no banco de dados MongoDB.

**2. Disponibilização via API**
Fornecer uma API REST para:
- Listar pedidos realizados por cliente.
- Calcular o valor total de um pedido.
- Determinar a quantidade de pedidos por cliente.

## 🛠️ Como executar o projeto
#### 1. Iniciar os serviços do MongoDB e RabbitMQ com Docker Compose
Certifique-se de que o Docker está instalado e execute o comando para subir os serviços:
```bash
docker compose up -d
```
#### 2. Acessar as interfaces do MongoDB e RabbitMQ
Verifique se os serviços estão ativos e faça login nas respectivas portas:

- MongoDB
  - Host: localhost/27017
  - Banco de dados: order-management-ms
  - Usuário: mongo
  - Senha: mongo

- RabbitMQ
  - Host: localhost/15672
  - Usuário: rabbitmq
  - Senha: rabbitmq

#### 3. Executar o projeto na IDE
Abra o projeto em sua IDE favorita, certifique-se de que as dependências foram instaladas corretamente e execute a aplicação.

#### 4. Publicar uma mensagem na fila do RabbitMQ
- Acesse o RabbitMQ Management Console e vá para a aba Queues and Streams `http://localhost:15672/#/queues/%2F/order-created`.
- Publique a seguinte mensagem na fila configurada para o projeto:
```JSON
{
    "codigoPedido": 1015,
    "codigoCliente": 1,
    "itens": [
        {
            "produto": "BTLG11",
            "quantidade": 4,
            "preco": 114.73
        },
        {
            "produto": "HGLG11",
            "quantidade": 19,
            "preco": 108.88
        }
    ]
}
```
#### 5. Testar a API
Utilize uma ferramenta como Postman ou Insomnia para acessar o endpoint de listagem de pedidos. Execute o seguinte GET request:
```
GET http://localhost:8080/customers/1/orders
```
O retorno será uma lista de pedidos realizados pelo cliente com o código 1, juntamente com o somatório dos valores. Um exemplo de resposta:
```JSON
{
    "summary": {
        "totalOnOrders": 52120.00
    },
    "data": [
        {
            "orderId": 1001,
            "customerId": 1,
            "total": 120.00
        },
        {
            "orderId": 1002,
            "customerId": 1,
            "total": 52000.00
        }
    ],
    "pagination": {
        "page": 0,
        "pageSize": 10,
        "totalElements": 2,
        "totalPages": 1
    }
}
```







## 📝 Lista de Tarefas Executadas
###### **Comunicação com o Banco de Dados**
- [x] Inicializar o projeto com as dependências: Spring Web, Spring Data MongoDB, RabbitMQ, Lombok.
- [x] Configurar o RabbitMQ e o MongoDB utilizando Docker.
- [x] Estabelecer a comunicação do Spring Boot com o MongoDB.
- [x] Configurar a criação de filas no RabbitMQ.
###### **Funcionalidade de Consumo de Pedidos**
- [x] Mapear as entidades principais: Order e OrderItem.
- [x] Criar um listener para processar mensagens da fila do RabbitMQ.
- [x] Implementar a lógica para salvar pedidos no banco de dados MongoDB.
- [ ] Testar o fluxo completo: RabbitMQ → Spring Boot → MongoDB.
###### **Funcionalidade de API - Desenvolver endpoints para:**
- [x] Listar pedidos de um cliente.
- [x] Calcular o valor total de um pedido.
###### **Obter a quantidade de pedidos por cliente.**
- [x] Criar os DTOs necessários para os retornos.
- [x] Implementar serviços que processam e agregam as informações solicitadas.

