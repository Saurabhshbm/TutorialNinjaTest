FROM maven:3.9.15-eclipse-temurin-21
 
WORKDIR /app
 
COPY . .
 
RUN mvn clean install -DskipTests
 
CMD ["mvn","-Dtest=TestRunner","test"]