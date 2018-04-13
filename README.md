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
 


