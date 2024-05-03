FROM bellsoft/liberica-openjdk-debian:21
LABEL maintainer="hendisantika@yahoo.co.id"
VOLUME /tmp
EXPOSE 8080
ADD target/tax-calculator-0.0.1-SNAPSHOT.jar tax-calculator-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","tax-calculator-0.0.1-SNAPSHOT.jar"]
