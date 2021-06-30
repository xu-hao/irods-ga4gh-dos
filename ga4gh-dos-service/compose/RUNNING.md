# Running the DRS API



### Overview

This is an alpha level release of a pure iRODS implementation of the GA4GH Data Repository Service (DRS). 

This docker-compose configures both the GA4GH DRS for iRODS and an iRODS REST API early access release. The two components work together
to support the DRS functions.  

* irods-drs is a Docker image that contains an implementation of the GA4GH Swagger spec for the Data Repository Service version 1.0.0. 

* irods-rest is a Docker image that contains an early access implementation of supporting iRODS endpoints needed for DRS, specifically
the issuing of JWTs for authenticated iRODS users and an endpoint for accessing file content as a stream for downloads.

### Architecture

This DRS implementation only requires a running iRODS data grid (www.irods.org). This open source data management platform can manage
collections of data that span all manner of local and cloud storage. iRODS provides a global logical namespace and can arrange colelctions
by hierarchy as well as by metadata relationships. This implementation defines a DRS data bundle as a parent iRODS collection, all data objects
contained in the parent folder are considered DRS bundle objects. The bundle is a flattened view and can include objects in a 
subfolder.

Bundles and contained data objects are marked in-place with AVU metadata, these are metadata tuples that can be attached at the file and 
collection level. This arrangement means that a collection can be used as-is with little alteration in DRS, having a minimal impact
on other access methods.

Currently this DRS implementation does not account for managing the mutability of data in a bundle, though iRODS rule engine policies 
can easily manage immutability and versioning if this is desired.

The DRS and iRODS Rest API are thin clients run via Docker, they only need proper host and authentication information to connect and work
properly. Each of these Docker images in the compose file will look for a ga4gh.properties and rest.properties, respectively, in 
a volume mount for /etc/irods-ext. In this directory is a sample etc/irods-ext collection that can be copied and configured
for the target iRODS grid. The location of these properties files is set as an IRODS_EXT_PATH environment variable before starting
the docker images.

An initial console application (like iCommands) has been created, currently this is only suitable for testing and evaluation. This allows
you to navigate around iRODS collections and create, remove, and list existing bundles. 

#### Authentication and Security

The DRS API and the REST API are configured with a shared key used to encode and decode a JWT used as a Bearer token in DRS. The iRODS 
REST API has an authentication endpoint (see https://app.swaggerhub.com/apis/angrygoat/i-rods_rest_api/1.0.0)

To obtain a JWT to use as a Bearer token for DRS, you can call the auth endpoint with a valid iRODS user/password. You will be authenticated
to iRODS and a JWT will be created with the iRODS identity in the JWT claims. This identity is used for any operations on iRODS via 
policies or access decisions.

The JWT token is then presented to DRS as a Bearer token and validatd via the shared key. The REST API can authenticate via these 
JWT tokens as well, and can utilize an iRODS ticket for the access URL endpoints in DRS

## Configuration steps

### Copy the included compose directory to a local file location

### Edit the ga4gh.properties and rest.properties, see those files for hints on the prop settings

### Set an environment variable that points to the local etc/irods-ext directory

```
export IRODS_EXT_PATH=/foo/bar/etc/irods-ext
```

### Start iRODS

### Start the REST and DRS docker containers

```
docker-compose up
```

### Create some bundles with the console

The DRS console is a docker image that is a thin client to iRODS. This can be started as a stand-alone docker image, and the code is
built as part of this package. It can be found under the ga4gh-console. The docker container can be started with the following:

```
(base) ~/Documents/workspace-niehs-dev/ga4gh-dos/ga4gh-console @ ALMBP-02010755(conwaymc): docker run -it  michaelconway/ga4gh-console:0.0.1
No cert to import
exec drscon.sh to run drs console
/ # 

```

This puts you at an interactive prompt. In the current directory you will find an ./drscon.sh script that can start the console

```
/ # ./drscon.sh

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.9.RELEASE)

shell:>

```

Typing help will give you a list of available commands:

```
shell:>help
AVAILABLE COMMANDS

Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        script: Read and execute commands from a file.
        stacktrace: Display the full stacktrace of the last error.

Drs Bundles Command
      * icd: Change working directory in iRODS
        iinit: Initialize connection
      * ilistdrsb: List all DRS bundles
      * ils: List directory contents
      * imakedrsb: Make a DRS bundle at current directory
      * ipwd: Print working directory in iRODS
      * irmdrsb: Remove a DRS bundle by directory path or GUID
      * list-bundles: List Bundles

Commands marked with (*) are currently unavailable.
Type `help <command>` to learn more.


shell:>

```

Several familiar icommands are provided that can help navigate to a target of a bundle. In the following sequence, the console moves 
to a subdirectory and creates a bundle. This sequence includes an illustration of how to log in (yes, the password right now
is a plain text param, that's why it's alpha, I'll fix that for a real release)

```
shell:>ipwd
Command 'ipwd' exists but is not currently available because you are not connected, please do the iinit command
Details of the error have been omitted. You can use the stacktrace command to print the full stacktrace.
shell:>iinit --host server4.local --zone zone1 --user test1 --password test
Connected to zone1 as user: test1
shell:>ipwd
/zone1/home/test1
shell:>ils
/zone1/home/test1
	/zone1/home/test1/bun1	COLLECTION	
	/zone1/home/test1/bun2	COLLECTION	
	/zone1/home/test1/cacheServiceTempDir	COLLECTION	
	/zone1/home/test1/epigenomics	COLLECTION	
	/zone1/home/test1/fedread	COLLECTION	
	/zone1/home/test1/fedwrite	COLLECTION	
	/zone1/home/test1/grpEpi	COLLECTION	
	/zone1/home/test1/grpEpi2	COLLECTION	
	/zone1/home/test1/jargon-scratch	COLLECTION	
	/zone1/home/test1/MultipleFilesUnderUserHomeBug262	COLLECTION	
	/zone1/home/test1/project-indexer-scratch	COLLECTION	
	/zone1/home/test1/reg	COLLECTION	
	/zone1/home/test1/study	COLLECTION	
	/zone1/home/test1/testCreateUserHomeDirViaProxy	COLLECTION	
	iscan.xlsx	DATA_OBJECT	11855

shell:>icd study
/zone1/home/test1/study
shell:>imakedrsb
created bundle with GUID:b670ec6a-78d2-438f-a180-885f49a016b4
shell:>ilistdrsb
893e11fd-aed6-4a62-b012-8057b55c8870	/zone1/home/test1/bun1
1d5a8b9f-b351-41f6-8d86-f06bd15e2ef2	/zone1/home/test1/bun2
b670ec6a-78d2-438f-a180-885f49a016b4	/zone1/home/test1/study

shell:>
	
```

Note that the bundle GUID is provided...You can use this in your DRS requests

### Authenticate and obtain a bearer token

The iRODS REST api has a tokens endpoint, pass a valid iRODS user/password and get the token for later CURL operations. Here is a CURL example:

```
 curl -X POST   http://localhost:8888/irods-rest2/token   --user test1:test
```
returns
```
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MSIsImlzcyI6Imlyb2RzLXJlc3QyIiwiaWF0IjoxNTgxMTAyMTkxfQ._XeZqqUi4MHmsxQdTyr-XcktnRaqtvRUToCGJq2rwL0QfGO9OSlKgf2OzknQtvl4F_i10oN-FBcT_uDO5gBuFg

```

### Utilize the given token in interactions with DRS

Here is an (abridged) example of retrieving a data bundle via CURL given the Bearer token obtained via the REST API

```

curl -X GET \
  'http://localhost:8080/ga4gh/drs/v1/objects/b670ec6a-78d2-438f-a180-885f49a016b4?expand=false' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MSIsImlzcyI6Imlyb2RzLXJlc3QyIiwiaWF0IjoxNTgxMTAyMTkxfQ._XeZqqUi4MHmsxQdTyr-XcktnRaqtvRUToCGJq2rwL0QfGO9OSlKgf2OzknQtvl4F_i10oN-FBcT_uDO5gBuFg'
```
returns
```json
{
   "id":"b670ec6a-78d2-438f-a180-885f49a016b4",
   "name":"/zone1/home/test1/study",
   "self_uri":"drs://localhost/b670ec6a-78d2-438f-a180-885f49a016b4",
   "size":0,
   "created_time":"2019-09-06T15:28:03Z",
   "updated_time":"2019-09-06T15:28:03Z",
   "version":"0",
   "mime_type":"text/directory",
   "checksums":[
      {
         "checksum":"736e715a72017f1e6ce67e9e5d7d0dc4e6b6d29e6150f7dc1f011697910c3bdf",
         "type":"sha256"
      }
   ],
   "access_methods":[
      
   ],
   "contents":[
      {
         "name":"file1.json",
         "id":"39e87c10-dbb6-4d45-9e21-1257ea337104",
         "drs_uri":[
            "drs://localhost/39e87c10-dbb6-4d45-9e21-1257ea337104"
         ],
         "contents":[
            
         ]
      },
      {
         "name":"file2.pdf",
         "id":"ca8d9a9c-c3d1-417a-8663-6904d9870be1",
         "drs_uri":[
            "drs://localhost/ca8d9a9c-c3d1-417a-8663-6904d9870be1"
         ],
         "contents":[
            
         ]
      },
      {
         "name":"file3.pdf",
         "id":"1c1c9892-d22f-4eef-882b-378d2a8dc3be",
         "drs_uri":[
            "drs://localhost/1c1c9892-d22f-4eef-882b-378d2a8dc3be"
         ],
         "contents":[
            
         ]
      }
   ],
   "description":"iRODS exploded bundle collection",
   "aliases":[
      "/zone1/home/test1/study"
   ]
}

```

### Obtain file contents via an access url using the REST API endpoint

Here one of the data objects is selected to obtain an access url

```
curl -X GET \
  http://localhost:8080/ga4gh/drs/v1/objects/01c167a9-6367-4d7b-895b-02c151514e79/access/irods-rest \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MSIsImlzcyI6Imlyb2RzLXJlc3QyIiwiaWF0IjoxNTgxMTAyMTkxfQ._XeZqqUi4MHmsxQdTyr-XcktnRaqtvRUToCGJq2rwL0QfGO9OSlKgf2OzknQtvl4F_i10oN-FBcT_uDO5gBuFg'
  
```
returns

```  
  {
    "url": "http://irods-rest:8888/fileStream?path=/zone1/home/test1/study/MuscleNGS/10-17-2017 POLG Muscle NGS project proposal.docx",
    "headers": [
        "X-API-KEY iU7Gc3dmeC1ECQ3"
    ]
}

```



