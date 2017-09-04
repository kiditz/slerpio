FROM java:8-jre
MAINTAINER kiditz <kiditzbastara@gmail.com>

ADD ./target/slerp-io-api.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/slerp-io-api.jar"]
EXPOSE 38289
