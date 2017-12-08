FROM ubuntu
WORKDIR /app
COPY  ./* /app/
RUN apt update && apt install -y openjdk-8-jdk maven && mvn clean package
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=prod","/app/application/target/chenxh-app-1.0.0.jar"]