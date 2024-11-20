# Usando uma imagem Maven com Java 21
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar arquivos necessários para o build
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o restante dos arquivos e compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Criar a imagem final com o JDK 21
FROM eclipse-temurin:21-jdk-alpine

# Definir o diretório de trabalho e a porta de exposição
WORKDIR /app
EXPOSE 8080

# Copiar o JAR compilado para o contêiner final
COPY --from=build /app/target/compra-e-venda-de-veiculos-0.0.1-SNAPSHOT.jar app.jar

# Iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
