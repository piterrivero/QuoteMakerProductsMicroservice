FROM openjdk

VOLUME /tmp
ADD maven/QuoteMakerProductsMicroservice-0.0.1-SNAPSHOT.jar quoteMakerProducts.jar
RUN sh -c 'touch /quoteMakerProducts.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/quoteMakerProducts.jar"]
