# Simulador de Servidor Web - Escalonamento de Tarefas

Este projeto é uma simulação de um servidor web que utiliza um algoritmo de escalonamento para atender múltiplas requisições. As requisições possuem diferentes tempos de chegada e carga de trabalho, e o objetivo do sistema é processá-las de forma eficiente, minimizando o tempo médio de espera e evitando a ocorrência de starvation.

## Descrição do Problema

Um servidor web recebe várias requisições simultâneas que precisam ser processadas. As requisições podem chegar a qualquer momento e cada uma tem uma carga de trabalho e, portanto, um tempo de processamento diferente. O servidor web poderá receber uma ou várias requisições em um dado instante de tempo e com variações também entre os tempos de chegada delas. O objetivo é desenvolver uma solução para atender as requisições de forma a minimizar o tempo médio de espera, escalonando-as utilizando um algoritmo apropriado que evite starvation.

## Funcionalidades

- Processamento de múltiplas requisições de clientes simultâneos.
- Escalonamento de tarefas com tempo de processamento variável.
- Visualização gráfica em tempo real do desempenho do servidor, como:
  - Tempo médio de espera.
  - Uso de CPU.
- Interface gráfica para acompanhar as requisições processadas pelo servidor.
- Simulação de múltiplos clientes fazendo requisições ao servidor.

## Execução do Programa

### Passo 1: Vincular Bibliotecas .jar

Antes de rodar o projeto, você deve vincular as bibliotecas necessárias para gerar a interface gráfica e os gráficos em tempo real. As bibliotecas **JFreeChart** e **JCommon** estão incluídas no repositório e você deve adicioná-las ao seu projeto Java:

1. Adicione o arquivo `jfreechart-1.0.19.jar` ao seu projeto.
2. Adicione o arquivo `jcommon-1.0.23.jar` ao seu projeto.

Esses arquivos estarão disponíveis no repositório, na raíz do repositório na pasta `jar`.

### Passo 2: Rodar o Servidor

1. A primeira etapa é rodar o servidor, que fica responsável por processar as requisições. Para isso, rode a classe `ChatServer.java`. O servidor vai inicializar e ficar pronto para aceitar conexões dos clientes.

### Passo 3: Rodar os Clientes

2. Em seguida, para simular requisições de clientes, você deve rodar a classe `ChatClientTester.java`. Essa classe vai instanciar múltiplos clientes que farão requisições ao servidor de forma simultânea. Isso permitirá simular um ambiente real de servidor com múltiplos clientes.
   
3. É necessário que o **servidor** (`ChatServer.java`) e os **clientes** (`ChatClientTester.java`) rodem **simultaneamente** para que o programa funcione corretamente.

### Observação

Você pode ajustar o número de clientes que farão requisições ao servidor, alterando o valor na linha:

```java
int numberOfClients = 5; // Define o número de clientes
## Arquivo in.txt - Requisições dos Clientes

O projeto contém um arquivo chamado `in.txt` que simula diferentes tipos de requisições feitas ao servidor. O arquivo inclui exemplos de requisições HTTP como `GET`, `POST`, `PUT`, e `DELETE`. Essas requisições são enviadas ao servidor de forma sequencial e separadas por um delimitador `---` que indica o fim de uma requisição e o início de outra.

As requisições são lidas e processadas pelo servidor de acordo com o tempo de chegada, e podem incluir ações como obter dados, enviar formulários, atualizar recursos ou excluir informações. O conteúdo de cada requisição inclui informações como o método HTTP, cabeçalhos e o corpo da requisição.

### Exemplo de Conteúdo do Arquivo `in.txt`:

GET / HTTP/1.1
Host: localhost
User-Agent: TestClient/1.0
Accept: text/html
Connection: keep-alive
---
POST /login HTTP/1.1
Host: localhost
User-Agent: TestClient/1.0
Content-Type: application/x-www-form-urlencoded
Content-Length: 29
username=user&password=pass123
---
GET /dashboard HTTP/1.1
Host: localhost
User-Agent: TestClient/1.0
Accept: text/html
Connection: keep-alive
---
PUT /api/users/123 HTTP/1.1
Host: localhost
User-Agent: TestClient/1.0
Content-Type: application/json
Content-Length: 54
{ "username": "newuser", "email": "newuser@example.com"}
---
DELETE /api/users/123 HTTP/1.1
Host: localhost
User-Agent: TestClient/1.0
Accept: application/json
Connection: keep-alive
---
GET /logout HTTP/1.1
Host: localhost
User-Agent: TestClient/1.0
Accept: text/html
Connection: close
---
POST /upload HTTP/1.1
Host: localhost
User-Agent: TestClient/1.0
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW
Content-Length: 138
---
WebKitFormBoundary7MA4YWxkTrZu0gW
Content-Disposition: form-data; name="file"; filename="test.txt"
Content-Type: text/plain
```

## Exemplo de Interface Gráfica

Aqui está um exemplo da interface gráfica gerada pelo programa durante a execução:

<div align="center">
  <img src="https://github.com/user-attachments/assets/ee5ed725-5e03-4142-b96a-76d6a52e9fe1" alt="grafico">
</div>

A interface exibe as requisições processadas pelo servidor e um gráfico em tempo real do desempenho (tempo de espera e uso de CPU).

## Estrutura do Projeto

O projeto está organizado da seguinte maneira:

- **entities**: Contém as classes principais que implementam o servidor, clientes e o sistema de requisições.
  - `ChatServer.java`: Classe responsável por inicializar e rodar o servidor. O servidor aceita conexões de múltiplos clientes e processa suas requisições.
  - `ChatClient.java`: Classe que simula um cliente individual, capaz de enviar requisições ao servidor.
  - `ChatClientTester.java`: Classe para simular múltiplos clientes simultâneos enviando requisições ao servidor.
  - `ClientSocket.java`: Classe que gerencia a comunicação por socket entre o servidor e os clientes.
  - `ServerMetricsGUI.java`: Interface gráfica que exibe informações sobre as requisições e gráficos em tempo real.
- **interfaces**: Contém a interface `Request.java`, que define a estrutura das requisições feitas ao servidor.

## Tecnologias Utilizadas

- **Java**: Linguagem principal utilizada no projeto.
- **JFreeChart**: Biblioteca utilizada para criar gráficos em tempo real.
- **JCommon**: Biblioteca auxiliar para suporte ao JFreeChart.
- **Sockets**: Para comunicação entre o servidor e os clientes.

## Como Vincular as Bibliotecas

1. **IntelliJ IDEA**:
   - Vá até `File` > `Project Structure`.
   - Na aba `Modules`, clique em `Dependencies`.
   - Clique no ícone `+` e adicione os arquivos `.jar` do **JFreeChart** e **JCommon** disponíveis no repositório.
   
2. **Eclipse**:
   - Clique com o botão direito no projeto.
   - Selecione `Build Path` > `Configure Build Path`.
   - Na aba `Libraries`, clique em `Add External JARs` e selecione os arquivos `.jar` das bibliotecas.

## Solução Técnica

### Algoritmo de Escalonamento

O servidor implementa um algoritmo de escalonamento simples, onde as requisições são processadas em intervalos de tempo com o objetivo de reduzir o tempo médio de espera. A cada requisição, o tempo estimado é reduzido, e as requisições que ainda não foram completadas são reinseridas na fila, até serem totalmente processadas.

### Fluxo de Execução

1. O servidor (`ChatServer.java`) aguarda conexões de clientes.
2. Quando um cliente se conecta, ele envia uma ou mais requisições.
3. O servidor processa essas requisições por intervalos de tempo (quantum) definidos, e exibe as informações na interface gráfica em tempo real.
4. O gráfico de tempo de espera e uso de CPU é atualizado conforme as requisições são processadas.
5. O processo continua até que todas as requisições sejam concluídas.

## Sobre o Projeto

Este projeto foi desenvolvido para as disciplinas de **Programação Orientada a Objetos** e **Sistemas Operacionais 1**, do curso de **Engenharia de Computação** no 5º período.
