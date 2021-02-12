FROM openjdk:8-jdk-alpine

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /app/api

ADD /build/libs/pollingapp*.jar pollingapp-api.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005 80

CMD java ${ADDITIONAL_OPTS} -jar pollingapp-api.jar --spring.profiles.active=${PROFILE}
