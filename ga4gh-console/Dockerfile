FROM openjdk:8-jdk-alpine
LABEL organization="NIEHS"
LABEL maintainer="mike.conway@nih.gov"
LABEL description="GA4GH iRODS DRS Console"
ADD runit.sh /
ADD drscon.sh /

ADD target/ga4gh-console-0.0.1-SNAPSHOT.jar /ga4gh-console-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["/runit.sh"]
#CMD ["/runit.sh"]



# build: docker build -t diceunc/ga4gh-dos:0.0.1 .

# run:  docker run -d --rm -p 8080:8080 -v /etc/irods-ext:/etc/irods-ext  --add-host irods420.irodslocal:172.16.250.101 diceunc/ga4gh-dos:0.0.1
