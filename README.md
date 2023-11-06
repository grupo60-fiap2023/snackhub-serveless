<center>
  <h1 align="center">Serveless</h1>
  <br align="center">
    Este projeto tem a finalidade educacional, como parte do Tech Challenge do Curso de Arquitetura de Software.
</center>

# Vídeo para avaliação dos mentores
https://youtu.be/eoKUjgunudo

Observação: Conforme sugerido e um dos critérios de avaliação, este repositório tem o ramo principal (main) bloqueado para commits diretos,
a partir desse momento, todos os commits para a main necessitará de um Pull Request com pelo menos 1 revisor.
![github.png](readmeFolder%2Fgithub.png)

Foi configurado o CI/CD onde é realizado:
* Build do código Java
* Gerado o Zip da Lambda
* Configuração das credenciais Amazon
* Criação/Atualização da Lambda

https://github.com/grupo60-fiap2023/snackhub-serveless/blob/main/.github/workflows/gradle.yml

![action.png](readmeFolder%2Faction.png)
![action2.png](readmeFolder%2Faction2.png)

Obs2: Para fins de redução de custo na AWS foi retirado as credenciais pessoais AWS do secrets para evitar criação de lambdas e cobranças.

# Conceito

## O que é Serveless?

Uma "função serverless" geralmente se refere a uma função de computação que é executada em um ambiente de computação serverless, como AWS Lambda, Google Cloud Functions ou Azure Functions. Vou explicar os principais conceitos envolvidos:

### Função Serverless:

Uma função serverless é um pequeno pedaço de código ou uma função que executa uma tarefa específica. Pode ser escrito em várias linguagens de programação, como Python, Node.js, Java, C#, etc.
A principal característica de uma função serverless é que ela é executada sob demanda, em resposta a eventos específicos, e não requer um servidor dedicado. Isso significa que você não precisa provisionar ou gerenciar servidores explicitamente.

### Computação Serverless:

A computação serverless é um modelo de computação em nuvem no qual a infraestrutura subjacente é gerenciada pelo provedor de nuvem, e os desenvolvedores podem se concentrar apenas na escrita do código da função.
Em vez de ter um servidor em execução continuamente, as funções serverless são ativadas apenas quando ocorre um evento específico, como uma solicitação HTTP, a chegada de uma mensagem em uma fila, uma atualização em um banco de dados, etc.
Os recursos computacionais são alocados dinamicamente com base na carga de trabalho, o que pode resultar em um custo menor em comparação com a execução contínua de servidores.

### Exemplos de Serviços Serverless:

AWS Lambda: Um serviço da Amazon Web Services que permite executar funções serverless em resposta a eventos.
Google Cloud Functions: Um serviço do Google Cloud que permite criar funções serverless em várias linguagens.
Azure Functions: Um serviço da Microsoft Azure que oferece suporte a funções serverless em várias linguagens.
As funções serverless são frequentemente usadas para criar aplicativos escaláveis e reativas, especialmente em cenários em que a carga de trabalho pode variar significativamente ao longo do tempo. Elas são uma maneira eficiente de executar código em resposta a eventos, sem a necessidade de gerenciar servidores tradicionais.

## Desafio
Implementar um API Gateway e um function serverless para autenticar o cliente com base no CPF

## Decisão Arquitetural do Desafio

Optamos pelas seguintes tecnologias:
* AWS API Gateway
* Base de dados Relacional Mysql disponível do Amazon RDS
* AWS Lambda com Java 17

![Lambda.drawio.png](readmeFolder%2FLambda.drawio.png)

Lembrando que para essa parte do desafio não foi obrigatório o uso de terraform

#### Lambda criada para realizar a Autenticação
![aws.png](readmeFolder%2Faws.png)

#### API Gateway e API
![apigateway.png](readmeFolder%2Fapigateway.png)
![api.png](readmeFolder%2Fapi.png)