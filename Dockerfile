# Use an official Maven image as a base for building the project.
# This image includes OpenJDK 11, which matches your project's Java version.
FROM maven:3.9.9-eclipse-temurin-17

# Set the working directory inside the container.
# All subsequent commands will be executed relative to this directory.
WORKDIR /app

# Copy the Maven project file (pom.xml) into the container.
# This step is done separately to leverage Docker's build cache.
# If only pom.xml changes, dependencies are re-downloaded; if source code changes,
# this layer remains cached, speeding up builds.
COPY pom.xml .


# Copy the rest of the application source code and the TestNG XML configuration file.
# The `src` directory contains your Java test classes.
# The `testng.xml` orchestrates which tests TestNG should run.
COPY src ./src

# Build the project and run the tests.
# `mvn clean install` cleans the target directory, compiles the code,
# runs the tests, and installs the artifacts into the local Maven repository.
# By default, Surefire plugin (which runs tests) will execute tests.
RUN mvn clean

# Define the command to run when the container starts.
# This command executes your tests and generates the Allure report.
# This makes it easy to simply run the container and get your test results.
CMD ["mvn", "test", "allure:report"]