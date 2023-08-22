FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar surat.jar
ENTRYPOINT ["java","-jar","/surat.jar"]
EXPOSE 8080