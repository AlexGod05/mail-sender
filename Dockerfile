FROM eclipse-temurin:17-jre

ADD build/libs/app.jar /app.jar
ADD build/agent/opentelemetry-javaagent-1.32.0.jar /opentelemetry-javaagent.jar

ENTRYPOINT java -javaagent:/opentelemetry-javaagent.jar \
           -Dotel.traces.exporter=logging \
           -Dotel.metrics.exporter=logging \
           -Dotel.logs.exporter=logging \
           -jar /app.jar