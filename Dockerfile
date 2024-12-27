# Basis-Image verwenden (Java 23 verwenden, falls nötig)
FROM eclipse-temurin:23-jdk-alpine

# Erstelle ein Verzeichnis für das Applikationsvolumen
VOLUME /tmp

# Kopiere die JAR-Datei aus dem Target-Verzeichnis in das Image
COPY target/diplom-projekt-backend-0.0.1-SNAPSHOT.jar app.jar

# Setze den Einstiegspunkt für das Container-Image
ENTRYPOINT ["java", "-jar", "/app.jar"]
