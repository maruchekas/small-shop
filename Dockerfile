FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/small-shop-0.0.1.jar
WORKDIR /opt/app
COPY ${JAR_FILE} small-shop-server.jar
ENTRYPOINT ["java","-jar","small-shop-server.jar"]