# SiengeApp
Software de gestão para indústria da construção


### Tecnologias Utilizadas

1. Java 8
2. Spring Boot 2
3. AngularJS
4. Bootstrap 4

### Estrutura de pastas do Projeto

```
sienge
├── src
|   ├── main
|   |   ├── resources
|   |   |		├── templates
|   |   |		└── static
|   |   └── java
├── README.md
└── pom.xml
```
### Ambiente necessário para execução 

1. [JDK 8]
2. [Maven 3]

### Comando para executar

1. `cd {sienge}`
2. `mvn spring-boot:run`
3. Acessar `http://localhost:8080`

### Gerar o pacate para publicação

1. `cd {sienge}`
2. `mvn package`
3. `cd ./target`
4. `java -jar ${artifactId}.jar`

### Acessar a aplicação

A aplicação será disponibilizada na porta padrão 8080 [http://{host}:8080/](http://localhost:8080/)