FROM openjdk:11
LABEL maintainer="ysp80s@gmail.com"
EXPOSE 8080
ARG JAR_FILE
ADD target/${JAR_FILE} carPlatePredictor.jar
ADD config.json config.json
ENTRYPOINT ["java","-jar","/carPlatePredictor.jar"]