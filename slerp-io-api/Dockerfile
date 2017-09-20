FROM openjdk:8-jdk-alpine
MAINTAINER kiditz <kiditzbastara@gmail.com>

ADD ./target/slerp-io-api.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/slerp-io-api.jar"]
EXPOSE 8020
