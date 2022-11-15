

FROM maven:3-openjdk-17 AS build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:17-jdk

COPY --from=build /home/app/target/micro1*.jar /usr/local/lib/micro1.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/micro1.jar"]
