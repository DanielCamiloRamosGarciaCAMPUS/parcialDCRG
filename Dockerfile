FROM openjdk:17
COPY "./target/SaberPro-1.jar" "app.jar"
EXPOSE 8015
ENTRYPOINT [ "java","-jar","app.jar" ]