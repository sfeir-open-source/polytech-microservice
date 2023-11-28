

FROM maven:3-openjdk-17 AS build

ARG REVISION=0.0.1-SNAPSHOT
COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml -Drevision=$REVISION clean package


FROM openjdk:17-jdk

ARG APP

ENV application=$APP

COPY --from=build /home/app/target/$APP*.jar /usr/local/lib/$APP.jar
EXPOSE 8088
ENTRYPOINT java -jar /usr/local/lib/$application.jar
