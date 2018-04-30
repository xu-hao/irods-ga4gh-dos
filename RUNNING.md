# Running the DOS API in FAIR test mode

#### set up etc

create a directory containing an /etc/irods-ext/ga4gh.properties to mirror the example file contained in this repo. This will be mounted as a volume in docker. Customize the ga4gh.properties file to point to your irods server

#### pull the repo

Docker pull diceunc/ga4gh-dos:0.0.1 (or other version number)

#### run the container

Example:

```
docker run -i -t \
-p 8080:8080 \
-v /Users/conwaymc/Documents/docker/ga4gh/etc/irods-ext:/etc/irods-ext \
diceunc/ga4gh-dos:0.0.1

```


This example runs the docker image in the foreground ,making it easy to start and stop the container as well as to easily see log messages


#### set up optional system metadata

FAIR system metadata is provided by AVUs attached to a special collection. The following set of icommands will create a directory on iRODS and provision it with metadata that will be sent to callers in the data bundle system metadata. The name of the collection is up to the user

icd xxx
imkdir yyy
imeta add -C yyy "contact_name" "Jane Does" "irods::ga4ghdos:systemMetadata"
imeta add -C yyy "repo_license_name" "cc0 Attribution v4.0 International" "irods::ga4ghdos:systemMetadata"
imeta add -C yyy "contact_form" "" "irods::ga4ghdos:systemMetadata"
imeta add -C yyy "manifest" "/Manifest.prototype.tsv" "irods::ga4ghdos:systemMetadata"
imeta add -C yyy "terms_of_service" "/tos.html" "irods::ga4ghdos:systemMetadata"
imeta add -C yyy "landing_page" "/about.html" "irods::ga4ghdos:systemMetadata"
imeta add -C yyy "supported_scoring_engines" "prototype" "irods::ga4ghdos:systemMetadata"
imeta add -C yyy "repo_license_URL" "https://creativecommons.org/licenses/by/4.0/legalcode" "irods::ga4ghdos:systemMetadata"
imeta add -C yyy "contact_email" "jdoe@email.unc.edu" "irods::ga4ghdos:systemMetadata"


#### Add a data bundle

A data bundle is an iRODS collection marked by a GUID, the consitutent data objects in the collection are mapped to GA$GH data objects in the bundle as long as they are marked by a GUID.


Create a collection:

imkdir yyy

Generate a GUID! An easy tool for testing is: https://www.guidgenerator.com/online-guid-generator.aspx

Add that guid as metadata

imeta add -C yyy "GUID" "GENERATEDGUIDHERE" "irods:GUID"

Add some data objects by iputting some files into that data bundle collection. Mark these data objects with a GUID as well

imeta add -d dataobjectname "GUID" "GENERATEDGUIDHERE" "irods:GUID"

Now you can go to your browser and bring up the DOS API. The 'get' methods for data object and data bundle will show the data objects and collections


#### Running functional tests

The src/test/java/gov/nih/niehsods/ga4gh/dos/test/functional/FunctTest.java has an example of writing a functional test that will go against your running docker image, setting up a collection, data objects, with system and object metadata. This test can be copied and used to produce other demonstrations



