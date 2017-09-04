FROM java:8-jre
MAINTAINER kiditz <kiditzbastara@gmail.com>

ADD ./target/discovery-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/discovery-service.jar"]

EXPOSE 8761