FROM openjdk:8-jre
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/aplicacao-ionic-java.jar
WORKDIR /app
ENTRYPOINT java -jar aplicacao-ionic-java.jar