FROM gradle:6.7.1-jdk8 as builder

WORKDIR /app

COPY . .

RUN gradle build -xtest

FROM openjdk:8-jdk-alpine

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /app/api

COPY --from=builder /app/build/libs/pollingapp*.jar pollingapp-api.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005 8080

CMD java ${ADDITIONAL_OPTS} -jar pollingapp-api.jar --spring.profiles.active=${PROFILE}
