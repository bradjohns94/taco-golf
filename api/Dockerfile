FROM hseeberger/scala-sbt

WORKDIR /app/
COPY src/ /app/

RUN sbt clean stage

ENV JAVA_OPTS -Dplay.http.secret.key=abcdefghijk -Dpidfile.path=/dev/null
CMD /app/target/universal/stage/bin/taco-golf-api ${JAVA_OPTS}
