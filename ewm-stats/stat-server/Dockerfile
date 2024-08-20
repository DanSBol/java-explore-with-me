FROM amazoncorretto:21-alpine-jdk
COPY target/*.jar stat-server.jar
ENTRYPOINT ["java", "-jar", "stat-server.jar"]