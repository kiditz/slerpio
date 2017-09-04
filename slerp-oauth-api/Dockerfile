FROM java:8-jre
MAINTAINER kiditz <kiditzbastara@gmail.com>

ADD ./target/slerp-oauth-api.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/slerp-oauth-api.jar"]
EXPOSE 8019
