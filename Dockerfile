FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENV JAVA_OPTS="-Xmx512m"
ENV PORT=8080
EXPOSE ${PORT}
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"] 