# api-orch-realtime-sensor (Orquestrador) - Processamento em Tempo Real Sensor IoT ESP8266 

É um orquestrador desenvolvido para processamento em tempo real de dados provenientes de sensores IoT. Utiliza uma arquitetura orientada a eventos e integra tecnologias como Spring Boot 3, Maven+ e Cloud Pub/Sub para garantir escalabilidade, confiabilidade e eficiência no processamento de dados.

## Funcionalidades

- Recebe mensagens de sensores IoT via Cloud Pub/Sub.
- Processa os dados recebidos em tempo real.
- Implementa um sistema de Retry para garantir a tolerância a falhas, utilizando cache (Redis) ou Cloud Memorystore.
- Integração fácil com outros sistemas por meio de uma arquitetura orientada a eventos.
- Configurações flexíveis para dimensionamento horizontal e vertical.

## Pré-requisitos

- JDK 11 ou superior instalado
- Maven 3.x instalado
- Conta ativa no Google Cloud Platform (GCP) com acesso ao Cloud Pub/Sub, Redis ou Cloud Memorystore, e outras APIs necessárias.

## Configuração

1. Clone o repositório para sua máquina local:

   ```
   git clone https://github.com/seu-usuario/aplicacao-orch.git
   ```

2. Configure as credenciais do Google Cloud Platform:

   Faça o download das credenciais JSON do GCP e defina a variável de ambiente `GOOGLE_APPLICATION_CREDENTIALS` apontando para o arquivo JSON.

3. Configure as propriedades do Redis ou Cloud Memorystore:

   Edite o arquivo `application.properties` no diretório `src/main/resources/`:

   ```properties
   # Configurações do Redis
   spring.redis.host=host-do-redis
   spring.redis.port=6379

   # Ou configurações do Cloud Memorystore
   spring.redis.host=host-do-memorystore
   spring.redis.port=6379
   spring.redis.password=sua-senha
   ```

   Substitua `host-do-redis` ou `host-do-memorystore` pelo endereço do seu Redis ou Cloud Memorystore e `sua-senha` pela senha, se aplicável.

4. Configure as propriedades do Cloud Pub/Sub:

   Edite o arquivo `application.properties` no diretório `src/main/resources/`:

   ```properties
   # Configurações do Cloud Pub/Sub
   spring.cloud.gcp.pubsub.subscription-name=nome-da-assinatura
   spring.cloud.gcp.pubsub.topic-name=nome-do-topico
   ```

   Substitua `nome-da-assinatura` pelo nome da sua assinatura e `nome-do-topico` pelo nome do seu tópico no Cloud Pub/Sub.

## Execução

1. Navegue até o diretório raiz do projeto:

   ```
   cd aplicacao-orch
   ```

2. Compile o projeto utilizando Maven:

   ```
   mvn clean package
   ```

3. Execute a aplicação Spring Boot:

   ```
   java -jar target/aplicacao-orch-1.0.0.jar
   ```

---

Este é um modelo básico de `README.md` para a sua aplicação Orch. Certifique-se de personalizá-lo conforme as necessidades específicas do seu projeto, incluindo informações adicionais sobre a integração com o Cloud Pub/Sub, detalhes sobre o sistema de Retry utilizando Redis ou Cloud Memorystore, guias de contribuição, etc.
