<a name="readme-top"></a>


  <br />
<div align="center">
  <img width="94" height="94" src="https://img.icons8.com/3d-fluency/94/credit-card-front.png" alt="credit-card-front"/>

<h3 align="center">Microsserviço de Pagamento com Crédito</h3>

  <p align="center">
    Projeto realizado para teste de conhecimentos em Java + Quarkus
  </p>

</div>

## Índice
  <ol>
    <li>
      <a href="#about-the-project">Sobre o projeto</a>
      <ul>
        <li><a href="#built-with">Tecnologias utilizadas</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Rodando localmente</a>
      <ul>
        <li><a href="#prerequisites">Pré-requisitos</a></li>
        <li><a href="#installation">Instalação</a></li>
      </ul>
    </li>
    <li>
      <a href="#endpoints">Endpoints</a>
    </li>
    <li><a href="#contact">Contato</a></li>
  </ol>

<a name="about-the-project"></a>
## Sobre o projeto

Deve criar um microsserviço em Java Quarkus que recebe dados de um pagamento fictício realizado com cartão de crédito
e armazena essas informações. O microsserviço deve validar se os dados recebidos estão no formato correto e, em seguida, 
persistir essas informações em um banco de dados relacional. Além disso, deve fornecer um endpoint HTTP para receber os
dados do pagamento e um mecanismo para consulta posterior desses dados armazenados.

<a name="built-with"></a>
### Tecnologias utilizadas

- Java
- Quarkus
- PostgreSQL
- Prometheus
- Docker
- JUnit

<p align="right">(<a href="#readme-top">Volte ao topo</a>)</p>

<a name="getting-started"></a>

## Rodando localmente

### Pré-requisitos

- [JDK 17](https://adoptium.net/temurin/releases/?version=17)


### Como rodar localmente

1. Clone o repositório

```bash

git clone https://github.com/josafamarengo/pagamento-credito.git

```

2. Vá para o diretório do projeto

```bash

cd pagamento-credito

```

3. Rode em modo desenvolvedor

```bash

./mvnw quarkus:dev

```

4. Acesse os endpoints pelo Swagger clicando no link abaixo:

- [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)

<p align="right">(<a href="#readme-top">Volte ao topo</a>)</p>

<a name="endpoints"></a>

## Endpoints

#### Retorna todos os pagamentos

```http
  GET /pagamentos
```

#### Retorna um pagamento específico

```http
  GET /pagamentos/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do pagamento que você quer |

#### Envia um novo pagamento

```http
  POST /pagamentos
```

```json
{
  "paymentId": 0,
  "plasticNumber": "1234123412341234",
  "personType": 1,
  "cpfOrCnpj": "123.456.789-10",
  "expirationMonth": 12,
  "expirationYear": 2025,
  "cvv": "123",
  "amount": 200.00
}
```




<a name="contact"></a>
## Contato

[![Linkedin][linkedin-shield]][linkedin-url]
[![Portfolio][site-shield]][site-url]

<!-- REPO LINK -->
[repo-url]: https://github.com/josafamarengo/pagamento-credito
[issues-url]: https://github.com/josafamarengo/pagamento-credito/issues

<!-- SOCIAL LINKS -->
[linkedin-shield]: https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white
[linkedin-url]: https://linkedin.com/in/josafamarengo

[email-shield]: https://img.shields.io/badge/Gmail-D14836?style=flat&logo=gmail&logoColor=white
[email-url]: mailto://josafabmarengo@gmail.com

[site-shield]: https://img.shields.io/badge/website-000000?style=flat&logo=Google-chrome&logoColor=white
[site-url]: https://josafa.com.br