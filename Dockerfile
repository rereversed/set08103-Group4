FROM openjdk:latest
COPY ./target/set08103-Group4-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "set08103-Group4-0.1.0.2-jar-with-dependencies.jar"]
