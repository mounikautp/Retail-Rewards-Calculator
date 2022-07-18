FROM openjdk:11
ARG JAR_FILE_NAME=target/*.jar

COPY ${JAR_FILE_NAME} application.jar

CMD ["java","-jar","application.jar"]