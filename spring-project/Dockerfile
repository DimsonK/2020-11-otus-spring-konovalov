#
# Build stage
#
FROM maven:3.8.1-adoptopenjdk-11 AS build

ENV PROJECT_DIR=/opt/project

RUN mkdir -p $PROJECT_DIR

ADD ./src $PROJECT_DIR/src
ADD ./pom.xml $PROJECT_DIR
RUN mvn -f $PROJECT_DIR clean package -Pdocker

#
# Package stage
#
FROM openjdk:11-jdk
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
COPY --from=build $PROJECT_DIR/target/spring-project-0.0.1.jar $PROJECT_DIR/
RUN mkdir $PROJECT_DIR/logs; touch $PROJECT_DIR/logs/spring-project.log; ln -sf /dev/stdout $PROJECT_DIR/logs/spring-project.log
EXPOSE 8080
CMD ["java", "-jar", "/opt/project/spring-project-0.0.1.jar"]
