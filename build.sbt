import ReleaseTransformations._

val domain = "com.bryghts"
val projectName = "apptemplate"
val group = s"$domain.$projectName"

organization in ThisBuild := group

lazy val root: Project =
  project
    .in(file("."))
    .enablePlugins(NativeImagePlugin)
    .settings(

       scalaVersion := "3.0.0-M3"

     , name := "scala-app-template"


     , Compile / mainClass := Some(s"$group.Main")

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
         ReleasePlugin.autoImport.releaseStepTask(genDocs),
         ReleasePlugin.autoImport.releaseStepCommand("git add ."),
         commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
         tagRelease,                             // : ReleaseStep
         setNextVersion,                         // : ReleaseStep
         commitNextVersion,                      // : ReleaseStep
         pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
       )

     , publishTo := sonatypePublishToBundle.value
    )

import java.nio.file.Path
def files(root: Path): Iterator[Path] = {
  import java.nio.file.Files
  import scala.collection.JavaConverters._
  Files.walk(root).iterator().asScala.filter(Files.isRegularFile(_))
}

val genDocs = taskKey[Unit]("Generates docs, including '*._no_ext_' ones")

genDocs := {
  val _ = (mdoc.in(docs)).toTask("").value
  val docsRoot = file("docs").toPath
  val extension = "._no_ext_"
  files(docsRoot)
    .filter(_.toString.endsWith(extension))
    .map(docsRoot.relativize)
    .foreach{source =>
      val targetName = source.toString.reverse.drop(extension.length).reverse
      val target = file(targetName)
      target.delete()
      source.toFile.renameTo(target)
    }
}


lazy val docs =
  project
    .in(file("documentation"))
    // .dependsOn(root)
    .settings(
       skip in publish := true,
       mdocOut := (ThisBuild / baseDirectory).value,
       mdocExtraArguments ++= Seq(
         "--markdown-extensions", "md",
         "--markdown-extensions", "_no_ext_"
       ),
       mdocVariables := Map(
         "VERSION" -> version.value,
         "NAME" -> (name.in(root)).value,
         "GROUP" -> (organization.in(root)).value,
         "YEAR" -> {
           val initialYear = "2020"
           val currentYear = java.time.Year.now.getValue().toString

           if (initialYear == currentYear) initialYear
           else s"$initialYear-$currentYear"
         },
         "COPYRIGHT_HOLDER" -> "Marc Esquerra <esquerra@bryghts.com>"
       )
    )
    .enablePlugins(MdocPlugin)
