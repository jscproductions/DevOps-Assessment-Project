# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/springboot-crud-web-app-0.0.1-SNAPSHOT.jar /app/springboot-crud-web-app-0.0.1-SNAPSHOT.jar

# Expose the port your application will listen on (if needed)
EXPOSE 8080

# Define the command to run your Java application
CMD ["java", "-jar", "springboot-crud-web-app-0.0.1-SNAPSHOT.jar"]
