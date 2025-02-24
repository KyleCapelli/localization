FROM eclipse-temurin:21-jre-alpine
LABEL authors="kylecapelli"

EXPOSE 8080

ADD build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]