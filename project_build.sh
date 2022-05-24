echo "## Build Project ##"
./gradlew build

echo "## Docker Build ##"
docker build --build-arg DEPENDENCY=build/dependency -t dbstjr5517/dash-map .

echo "## Push to Docker ##"
docker push dbstjr5517/dash-map
