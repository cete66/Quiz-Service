FROM maven:3.5-jdk-8-alpine
WORKDIR /Quiz-service
COPY ./quiz-reactor /Quiz-service
RUN mvn install
FROM openjdk:8-jre-alpine
EXPOSE 8080
CMD ["java -jar ./quiz-web-rs/target/quiz-web-rs-0.0.1-SNAPSHOT.war"]
