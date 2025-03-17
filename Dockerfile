FROM eclipse-temurin:21
COPY backend/target/team-project-2.jar team-project-2.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "team-project-2.jar"]