FROM gradle:jdk17-corretto

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY src/ src/


RUN gradle build --no-daemon -x test

EXPOSE 8080

CMD ["java", "-jar", "build/libs/demoBackend-0.0.1-SNAPSHOT.jar"]

