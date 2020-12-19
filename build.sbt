scalaVersion := "3.0.0-M3"

name := "kak-idris"

enablePlugins(NativeImagePlugin)

Compile / mainClass := Some("kak.idris.Main")

// libraryDependencies += "com.lihaoyi" %%% "fastparse" % "2.2.4"


scalacOptions ++= Seq(
  "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",                // Specify character encoding used by source files.
  "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
  "-Xfatal-warnings",                  // Fail the compilation if there are any warnings.
  "-Yexplicit-nulls",
)

