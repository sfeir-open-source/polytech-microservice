

FROM maven:3-openjdk-17 AS build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:17-jdk

COPY --from=build /home/app/target/micro2*.jar /usr/local/lib/micro2.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "/usr/local/lib/micro2.jar"]
