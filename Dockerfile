FROM java:8
COPY target/rest-api-acrolinx-1.0-SNAPSHOT.jar /home/app/
ADD target/lib /home/app/lib
WORKDIR /home/app
CMD ["java", "-jar", "warehouse-1.0-SNAPSHOT.jar"]