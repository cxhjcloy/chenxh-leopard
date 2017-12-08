FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./application/target/chenxh-app-1.0.0.jar  leopard.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=prod","/leopard.jar"]
