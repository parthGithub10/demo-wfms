#
# Build stage
#
FROM maven:3.8.1-openjdk-17-slim AS build
COPY src /home/configuration-service/src
COPY pom.xml /home/configuration-service
RUN mvn -f /home/configuration-service/pom.xml clean package

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /home/configuration-service/target/*.jar /usr/local/lib/configuration-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/configuration-service.jar"]