FROM java:8

COPY target/*.jar /opt/authorization-svc.jar

EXPOSE 9096

ENTRYPOINT java -jar /opt/authorization-svc.jar