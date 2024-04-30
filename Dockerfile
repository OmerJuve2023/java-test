FROM openjdk:17.0.1-jdk-slim
VOLUME /tmp
EXPOSE 3000
COPY target/java-test-1.0.0.jar java-test.jar
ENTRYPOINT ["java","-jar","java-test.jar"]