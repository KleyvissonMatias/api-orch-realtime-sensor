# api-orch-realtime-sensor (Orquestrador) - Processamento em Tempo Real de Sensor IoT

É uma orquestrador desenvolvido para processamento em tempo real de dados provenientes de sensores IoT. Utiliza uma arquitetura orientada a eventos e integra tecnologias como Spring Boot 3, Maven+ e Cloud Pub/Sub para garantir escalabilidade, confiabilidade e eficiência no processamento de dados.

## Pré-requisitos

- JDK 11 ou superior instalado
- Maven 3.x instalado
- Conta válida na plataforma Cloud Pub/Sub

## Configuração

1. Clone o repositório para sua máquina local:

   ```
   git clone https://github.com/seu-usuario/orch-system.git
   ```

2. Configure as credenciais do Cloud Pub/Sub:

   - Faça login na Console do Google Cloud Platform.
   - Crie ou selecione um projeto.
   - Ative a API Cloud Pub/Sub para o projeto.
   - Crie uma chave de serviço com permissões de acesso ao Cloud Pub/Sub e faça o download do arquivo JSON com as credenciais.
   - Renomeie o arquivo JSON para `google-cloud-credentials.json` e mova-o para o diretório `src/main/resources/` do projeto.

3. Edite o arquivo `application.properties` no diretório `src/main/resources/`:

   ```properties
   # Configurações do Cloud Pub/Sub
   spring.cloud.gcp.pubsub.project-id=seu-id-do-projeto
   spring.cloud.gcp.pubsub.subscription-name=nome-da-assinatura
   spring.cloud.gcp.pubsub.credentials.location=file:src/main/resources/google-cloud-credentials.json
   ```

   Substitua `seu-id-do-projeto` pelo ID do seu projeto no Google Cloud Platform e `nome-da-assinatura` pelo nome da assinatura do Cloud Pub/Sub.

## Execução

1. Navegue até o diretório raiz do projeto:

   ```
   cd orch-system
   ```

2. Compile o projeto utilizando Maven:

   ```
   mvn clean package
   ```

3. Execute a aplicação Spring Boot:

   ```
   java -jar target/orch-system-1.0.0.jar
   ```
