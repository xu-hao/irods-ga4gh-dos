FROM openjdk:8-jdk-alpine
LABEL organization="NIEHS"
LABEL maintainer="mike.conway@nih.gov"
LABEL description="GA4GH iRODS DRS Console"
ADD runit.sh /
ADD drscon.sh /

ADD target/ga4gh-console-0.0.1-SNAPSHOT.jar /ga4gh-console-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["/runit.sh"]
#CMD ["/runit.sh"]



# build: docker build -t michael-conway/ga4gh-console:0.0.1 .

# run:  docker run -it  michael-conway/ga4gh-console:0.0.1
