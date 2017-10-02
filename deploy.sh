#!/bin/sh

if git describe --exact-match --tags HEAD
then
    echo "Found tag"
      tag=$(git describe --exact-match --tags HEAD)
    echo "Found tag $tag"
    sbt jdkPackager:packageBin
    ghr -u divanvisagie $tag target/universal/jdkpackager/bundles
else
    echo "Tag not found"
fi
