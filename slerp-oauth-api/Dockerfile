FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/slerp-oauth-api-1.0.0-SNAPSHOT.jar app.jar
ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:PermSize=64m -XX:MaxPermSize=256m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]