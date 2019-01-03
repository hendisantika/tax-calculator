FROM java:8
LABEL maintainer="hendisantika@gmail.com"
VOLUME /tmp
EXPOSE 8080
ADD target/tax-calculator-0.0.1-SNAPSHOT.jar tax-calculator-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","tax-calculator-0.0.1-SNAPSHOT.jar"]