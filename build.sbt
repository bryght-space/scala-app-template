import ReleaseTransformations._


lazy val root =
  project
    .in(file("."))
    .enablePlugins(NativeImagePlugin)
    .settings(

       scalaVersion := "3.0.0-M3"

     , name := "kak-idris"


     , Compile / mainClass := Some("kak.idris.Main")

     // , libraryDependencies += "com.lihaoyi" %%% "fastparse" % "2.2.4"


     , scalacOptions ++= Seq(
         "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
         "-encoding", "utf-8",                // Specify character encoding used by source files.
         "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
         "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
         "-Xfatal-warnings",                  // Fail the compilation if there are any warnings.
         "-Yexplicit-nulls",
       )

     , releaseProcess := Seq[ReleaseStep](
         checkSnapshotDependencies,              // : ReleaseStep
         inquireVersions,                        // : ReleaseStep
         runClean,                               // : ReleaseStep
         runTest,                                // : ReleaseStep
         setReleaseVersion,                      // : ReleaseStep
         ReleasePlugin.autoImport.releaseStepInputTask(MdocPlugin.autoImport.mdoc in docs),
         ReleasePlugin.autoImport.releaseStepCommand("git add ."),
         commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
         tagRelease,                             // : ReleaseStep
         setNextVersion,                         // : ReleaseStep
         commitNextVersion,                      // : ReleaseStep
         pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
       )

    )

lazy val docs =
  project
    .in(file("documentation"))
    // .dependsOn(root)
    .settings(
       skip in publish := true,
       mdocOut := (ThisBuild / baseDirectory).value,
       mdocVariables := Map(
         "VERSION" -> version.value
       )
    )
    .enablePlugins(MdocPlugin)
