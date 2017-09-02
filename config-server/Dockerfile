FROM openjdk
MAINTAINER kiditz <kiditzbastara@gmail.com>

ADD ./target/config-server-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config-server-0.0.1-SNAPSHOT.jar"]


EXPOSE 8888
