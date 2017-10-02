#!/bin/sh

echo "valid"
sbt jdkPackager:packageBin
echo "tag" $TRAVIS_TAG
ghr -u divanvisagie $TRAVIS_TAG target/universal/jdkpackager/bundles
