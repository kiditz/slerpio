FROM java:8-jre
MAINTAINER kiditz <kiditzbastara@gmail.com>

ADD ./target/config-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config-server.jar"]

HEALTHCHECK --interval=20s --timeout=10s CMD curl -f http://localhost:8888/health || exit 1oc

EXPOSE 8888
