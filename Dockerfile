FROM docker.binarios.intranet.bb.com.br/bb/dev/dev-java:17.0.8

COPY --chown=185 target/quarkus-app/*.jar /deployments/
COPY --chown=185 target/quarkus-app/lib /deployments/lib/
COPY --chown=185 target/quarkus-app/app /deployments/app/
COPY --chown=185 target/quarkus-app/quarkus /deployments/quarkus/

#descomente a linha abaixo APENAS para debug
#ENV JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=127.0.0.1:8000

ENTRYPOINT ["java", "-jar", "/deployments/quarkus-run.jar"]