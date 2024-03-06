FROM maven:3.9.6-eclipse-temurin-17-focal as build
COPY /src /app/src
COPY /pom.xml /app
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

FROM amazoncorretto:17-alpine3.16
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT [ "java", "-jar","/app.jar" ]

