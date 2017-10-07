
organization := "com.example"
scalaVersion := "2.12.3"
version      := "0.0.19"
maintainer   := "Divan Visagie"
name := "Fret Mapper"

libraryDependencies ++= Seq(
  "org.scalafx" % "scalafx_2.12" % "8.0.102-R11",
  "org.scalatest" %% "scalatest" % "3.0.3"
)

//need this to have styles
unmanagedJars in Compile += {
val ps = new sys.SystemProperties
val jh = ps("java.home")
Attributed.blank(file(jh) / "lib/ext/jfxrt.jar")
}


enablePlugins(JDKPackagerPlugin)
jdkPackagerType := "installer"
jdkPackagerToolkit := JavaFXToolkit



