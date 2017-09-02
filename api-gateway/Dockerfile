FROM openjdk
MAINTAINER Alexander Lukyanchikov <sqshq@sqshq.com>

ADD ./target/api-gateway-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/api-gateway-0.0.1-SNAPSHOT.jar"]

EXPOSE 4000
