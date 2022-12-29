FROM maven:3.8.6-amazoncorretto-19 AS build
COPY src /service-manual/src
COPY pom.xml /service-manual
RUN mvn -f /service-manual/pom.xml clean package

FROM amazoncorretto:19-al2-jdk
COPY --from=build /service-manual/target/service-manual-0.0.1-SNAPSHOT.jar /service-manual/service-manual.jar
ENTRYPOINT ["java","-jar","/service-manual/service-manual.jar"]