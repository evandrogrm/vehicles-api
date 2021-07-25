FROM java:8
EXPOSE 8080
EXPOSE 8081
ADD /target/vehicles-0.0.1-SNAPSHOT.jar vehicles-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8081","-jar","vehicles-0.0.1-SNAPSHOT.jar"]