sbt doc
Remove-Item docs/api -Recurse 
Copy-Item target/scala-2.12/api docs -Recurse -Force