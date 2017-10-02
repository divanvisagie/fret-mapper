#!/bin/sh

echo "valid"
sbt jdkPackager:packageBin
if [[ "$TRAVIS_OS_NAME" = "osx" ]]; then
    brew tap tcnksm/ghr;
    brew install ghr;
else
    curl -sL -o ~/bin/gimme https://raw.githubusercontent.com/travis-ci/gimme/master/gimme;
    chmod +x ~/bin/gimme;
    go get -u github.com/tcnksm/ghr;
fi
echo "tag" $TRAVIS_TAG
#ghr -u divanvisagie $TRAVIS_TAG target/universal/jdkpackager/bundles
