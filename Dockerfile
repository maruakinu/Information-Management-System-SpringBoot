FROM openjdk:17
COPY target/mysql-spring-0.0.1-SNAPSHOT.jar mysql-spring-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/mysql-spring-0.0.1-SNAPSHOT.jar"]