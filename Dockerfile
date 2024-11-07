FROM openjdk:22
WORKDIR /target
COPY . /target
EXPOSE 8080
CMD ["java", "platform-ticket-0.0.1-SNAPSHOT.jar"]