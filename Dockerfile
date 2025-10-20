FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app/heroku
# después de establecer WORKDIR /app/heroku
RUN mkdir -p /app/heroku/data

# copiamos carpeta del proyecto
COPY heroku ./ 
RUN mvn -B -DskipTests=true clean package

FROM eclipse-temurin:21-jre
WORKDIR /app/heroku
COPY --from=build /app/heroku/target/*.jar app.jar
RUN mkdir -p /app/heroku/data
COPY --from=build /app/heroku/target/*.jar app.jar

# EXPOSE 8080
CMD ["sh","-c","mkdir -p /app/heroku/data && java -Dserver.port=${PORT:-8080} -jar /app/heroku/app.jar"]
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests

#FROM eclipse-temurin:21-jre-alpine
#WORKDIR /app
#COPY --from=build /app/target/*.jar app.jar
#EXPOSE 8080
#CMD java -Dserver.port=${PORT:-8080} -jar app.jar
