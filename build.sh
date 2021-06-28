mvn package -DskipTests

pushd ga4gh-dos-service
docker build . -t ga4gh-dos-service:0.0.1
popd

pushd ga4gh-console
docker build . -t ga4gh-console:0.0.1
popd


