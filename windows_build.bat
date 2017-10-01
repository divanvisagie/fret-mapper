rmdir /S /Q target\universal\jdkpackager\bundles
rmdir /S / Q target\upload
sbt jdkPackager:packageBin
pause
xcopy target\universal\jdkpackager\bundles\*.exe target\upload\ /sy