FROM maven:3.8.7-openjdk-18-slim as BUILD
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY pom.xml $APP_HOME
COPY src $APP_HOME/src
RUN mvn clean package

FROM openjdk:18-slim
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME/target/*.jar ./app.jar

EXPOSE 8080

CMD ["java","-jar", "app.jar"]