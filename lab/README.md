
# Lab de construction et d'utilisation de microservices

## Pré-requis

* Docker
* docker-compose

## Lancement

```shell
docker compose up -d
```

## Supervision

http://localhost:8761


## Schéma

```mermaid
flowchart TD
    subgraph microservices
    A[Gateway :9090] --->|appelle| B(Microservice 1) &  C(Microservice 2)
    end
    A -.-> |liste| D([Register :8761])
    B & C -.->|se référence| D
```
