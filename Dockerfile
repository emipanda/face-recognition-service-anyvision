FROM openjdk:11
ADD target/rest-api-anyvision.jar rest-api-anyvision.jar
EXPOSE 8085
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar", "rest-api-anyvision.jar"]