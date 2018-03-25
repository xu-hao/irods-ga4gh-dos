## GA4GH Data Object Service for iRODS

This is an implementation of the GA4GH data object service for the iRODS data grid, based on the iRODS Jargon API.

(work in progress)

ef2e03e1-bfff-4fc4-84e8-c9ba6540ac86

For an initial PoC the assumption is that a DataBundle maps to an iRODS collection,and the members of that bundle are the direct
children of that collection. Each Collection and DataObject is marked with a GUID that serves as the id.

helpful dev links


http://blog.jamesdbloom.com/UsingPropertySourceAndEnvironment.html

swagger dos api - https://github.com/ga4gh/data-object-service-schemas/blob/master/openapi/data_object_service.swagger.yaml

