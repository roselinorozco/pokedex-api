# Stage 1: Build the application using the Gradle image
FROM gradle:8.7.0-jdk17 as build
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . /home/gradle/project

# Build the application
RUN gradle build --no-daemon

# Stage 2: Set up the production environment with a JRE image
FROM amazoncorretto:17-alpine3.17-jdk
EXPOSE 8080
WORKDIR /app

# Copy only the built JAR from the build stage
COPY --from=build /home/gradle/project/build/libs/*.jar /app/

# Command to run the application
ENTRYPOINT ["java", "-jar", "userservice.jar"]