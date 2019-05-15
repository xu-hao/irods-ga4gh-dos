docker run --rm -v ${PWD}:/Users/conwaymc/Documents/Programs/swagger-codegen-3.0.8/swagger-codegen-cli generate \
    -i https://raw.githubusercontent.com/ga4gh/data-repository-service-schemas/master/openapi/data_repository_service.swagger.yaml \
    -l spring \
    -o /Users/conwaymc/temp/gen
