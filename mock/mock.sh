#!/bin/bash

echo "------- creating mock -------"

curl --request GET \
     --url 'https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/2.27.2/wiremock-standalone-2.27.2.jar'\
     --output 'app.jar'

java -jar app.jar --port 8889

rm -rf app.jar