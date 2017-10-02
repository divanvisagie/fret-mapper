#!/bin/sh

if [[ $TRAVIS_BRANCH == "master" && -n $TRAVIS_TAG ]]; then
    echo "valid";
    sbt jdkPackager:packageBin;
    if [[ "$TRAVIS_OS_NAME" = "osx" ]]; then
        brew tap tcnksm/ghr;
        brew install ghr;
    else
        curl -sL -o ~/bin/gimme https://raw.githubusercontent.com/travis-ci/gimme/master/gimme;
        chmod +x ~/bin/gimme;
        go get -u github.com/tcnksm/ghr;
    fi
    ghr -u divanvisagie $TRAVIS_TAG target/universal/jdkpackager/bundles
else
    echo "TRAVIS_BRANCH" $TRAVIS_BRANCH;
    echo "TRAVIS_TAG" $TRAVIS;
fi