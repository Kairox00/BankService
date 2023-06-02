FROM openjdk:17
EXPOSE 3000
ADD target/BankService-0.0.1-SNAPSHOT.jar bank-service.jar
ENTRYPOINT ["java","-jar","bank-service.jar"]