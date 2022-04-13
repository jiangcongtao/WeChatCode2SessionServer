# Compile Stage
FROM maven:3.6.3-adoptopenjdk-11 as compile
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
WORKDIR /opt/demo
COPY pom.xml .
ADD m2-mirror-settings.xml /root/.m2/settings.xml
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean install -Dmaven.test.skip=true

# Package Stage
FROM adoptopenjdk/openjdk11:jre-11.0.9_11-alpine
WORKDIR /opt/demo
COPY --from=compile /opt/demo/target/WeChatCode2SessionServer-0.0.1-SNAPSHOT.jar /opt/demo
CMD ["java", "-jar", "./WeChatCode2SessionServer-0.0.1-SNAPSHOT.jar"]

EXPOSE 3000