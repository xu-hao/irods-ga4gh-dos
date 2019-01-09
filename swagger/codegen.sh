docker run --rm -v ${PWD}:/local swaggerapi/swagger-codegen-cli generate \
    -i https://raw.githubusercontent.com/ga4gh/data-object-service-schemas/master/openapi/data_object_service.swagger.yaml \
    -l spring \
    -o /Users/conwaymc/temp/gen
