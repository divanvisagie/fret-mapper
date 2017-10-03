# fret-mapper 
[![CircleCI](https://circleci.com/gh/divanvisagie/fret-mapper.svg?style=svg)](https://circleci.com/gh/divanvisagie/fret-mapper)
[![Build Status](https://travis-ci.org/divanvisagie/fret-mapper.svg?branch=master)](https://travis-ci.org/divanvisagie/fret-mapper)
[![Build status](https://ci.appveyor.com/api/projects/status/shxl94wa7udys7ti?svg=true)](https://ci.appveyor.com/project/divanvisagie/fret-mapper)

![Screenshot](docs/images/screenshot.png)

Map the notes of frets in different tunings

## Building 

```sh
sbt jdkPackager:packageBin
```

```sh
ghr -u divanvisagie v[version number] target/upload
```
`-prerelease` flag to be used in pre releases

[Download](https://github.com/divanvisagie/fret-mapper/releases/tag/v0.0.16)
