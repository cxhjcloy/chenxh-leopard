FROM maven
WORKDIR /app
ADD ["app.tar","."]
RUN ["mvn","package"]

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=0 /app/application/target/chenxh-app-1.0.0.jar .
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-jar","chenxh-app-1.0.0.jar"]