FROM openjdk:latest
COPY ./target/set08103-Group4-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "set08103-Group4-1.0-SNAPSHOT-jar-with-dependencies.jar"]