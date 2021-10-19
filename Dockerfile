FROM openjdk:11

EXPOSE 8080

RUN mkdir ./app

COPY ./build/libs/alfa-test-task-0.0.1-SNAPSHOT.jar ./app

CMD java -jar ./app/alfa-test-task-0.0.1-SNAPSHOT.jar