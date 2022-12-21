FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV SPRING_PROFILES_ACTIVE develop
ENV PORT 8080
EXPOSE $PORT
CMD [  "java","-jar","app.jar","-Dserver.port=${PORT}" , "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}" ]