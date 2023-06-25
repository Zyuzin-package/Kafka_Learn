FROM openjdk:17
COPY . /Consumer
WORKDIR /Consumer
RUN ./mvnw package
CMD ["java","ConsumerApplication"]
ENTRYPOINT ["java","-jar","target/Consumer-0.0.1-SNAPSHOT.jar"]
