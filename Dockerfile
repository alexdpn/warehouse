FROM maven:3.8.1-jdk-8-openj9 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM java:8
COPY --from=build /home/app/target/warehouse-1.0-SNAPSHOT.jar /home/app/
WORKDIR /home/app
CMD ["java", "-jar", "warehouse-1.0-SNAPSHOT.jar"]