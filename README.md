## GA4GH Data Object Service for iRODS

This is an implementation of the GA4GH data object service for the iRODS data grid, based on the iRODS Jargon API.

(work in progress)

00fea60a-e3b3-409f-9973-65b8a79db98b

ef2e03e1-bfff-4fc4-84e8-c9ba6540ac86

For an initial PoC the assumption is that a DataBundle maps to an iRODS collection,and the members of that bundle are the direct
children of that collection. Each Collection and DataObject is marked with a GUID that serves as the id.

helpful dev links


http://blog.jamesdbloom.com/UsingPropertySourceAndEnvironment.html

swagger dos api - https://github.com/ga4gh/data-object-service-schemas/blob/master/openapi/data_object_service.swagger.yaml

https://medium.com/@nonphatic/making-swagger-play-nice-with-protobufs-fd6437d63ae

free form swagger objects: https://swagger.io/docs/specification/data-models/data-types/

java example : https://github.com/overture-stack/score

codegen: https://github.com/swagger-api/swagger-codegen#os-x-users

DOCS: https://data-object-service.readthedocs.io/en/latest/

springfox: https://springfox.github.io/springfox/docs/current/

message FMDI {
 required string id = 1;
 required string version = 2;
 required string schema = 3;
 required string persistence = 4;
 required string contact = 5;
 required repeated message Keywords {
   required string ontology = 1;
   required repeated term = 2;
 }
 optional repeated message Derived_from_ids {
   required string id = 1;
   required string added_date = 2;
 }
 optional repeated message Cited_by_ids {
   required string id = 1;
   required string added_date = 2;
 }
 enum License {
    OTHER = 0;
    CC0 = 1;
    BSD=2;
   GPL=3;
   APACHE = 4
  ..
}
required repeated message Licensing_policies {
   required License license =1 [default = OTHER];
   required string description = 2;
   required string permissions = 3;
 }
}


------------

"version":"prototype",
  "derived_from":"1111",
  "object_schema":"some schema",
  "contact":"Jane Doe",
  "persistence":"/persistence.html",
  "keywords":"thyroid",
  "license":"cc0 Attribution v4.0 International",
  "cited_by":"1011,1213"
  
  
  -----------
  system metadata
  
  {
    "contact_name":"Jane Does",
    "repo_license_name":"cc0 Attribution v4.0 International",
    "contact_form":"",
    "manifest":"/Manifest.prototype.tsv",
    "terms_of_service":"/tos.html",
    "landing_page":"/about.html",
    "supported_scoring_engines":"prototype",
    "repo_license_URL":"https://creativecommons.org/licenses/by/4.0/legalcode",
    "contact_email":"jdoe@email.unc.edu"
}
  
  
 


