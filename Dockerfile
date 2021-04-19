# Added slim adoptopenjdk
FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app

ADD target/AggregatorService.jar AggregatorService.jar
EXPOSE 9092
ENTRYPOINT ["java", "-jar", "AggregatorService.jar"]