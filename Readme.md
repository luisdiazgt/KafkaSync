- [Introducción](#introducción)
- [Prerequisito(s)](#prerequisitos)
- [Docker](#docker)

# Introducción

El repositorio contiene 2 proyectos, el cliente que genera un mensaje hacia el broker y se queda esperando la respuesta sel servidor en otro topic. El servidor procesa el mensaje ingresado en el broker y le responde con otro mensaje en otro topic.

# Prerequisito(s)

- Docker
- Docker compose
- Kafka
- Zookeeper

# Docker

El archivo `docker-compose.yml` continene la configuración para levantar Zookeeper y Kafka.

```
    version: '2'
    services:
    zookeeper:
        image: confluentinc/cp-zookeeper:latest
        environment:
        ZOOKEEPER_CLIENT_PORT: 2181
        ZOOKEEPER_TICK_TIME: 2000
        ports:
        - 22181:2181
    
    kafka:
        image: confluentinc/cp-kafka:latest
        depends_on:
        - zookeeper
        ports:
        - 29092:29092
        environment:
        KAFKA_BROKER_ID: 1
        KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
        KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092
        KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
        KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
        KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
```

Colocarse en la carpeta del archivo y ejecutar el siguiente comando:

```
    docker compose up
```
