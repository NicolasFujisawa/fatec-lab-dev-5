# fatec-lab-dev-5
Projeto com Spring Boot e VueJs

## Setup modo desenvolvimento

### Docker

Para fazer o setup em modo desenvolvimento com docker, suba o container dev

```bash
docker-compose up dev
```

### Máquina local

Para fazer o setup em modo desenvolvimento localmente abra uma aba e utilize o comando

```bash
make devbuild
```

Abra outra aba e utilize o comando

```bash
make run
```

*Obs*: Lembre-se de alterar o arquivo `.env` para apontar o endereço do banco de dados para `localhost`.
