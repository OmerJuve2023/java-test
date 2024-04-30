#FROM maven:3.8.5-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY . .
EXPOSE 3000
ADD target/java-test-1.0.0.jar java-test.jar
ENTRYPOINT ["java","-jar","java-test.jar"]