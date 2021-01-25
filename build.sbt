import ReleaseTransformations._
import com.bryghts.r2bot.caps.bintray.R2BintrayOwner
import sbt.librarymanagement.Resolver

val domain = "com.bryghts"
val projectName = "apptemplate"
val group = s"$domain.$projectName"

organization in ThisBuild := group

val theScalaVersion = "3.0.0-M3"
// val theScalaVersion = "2.13.4"

Global / r2GDocsVariables := Map(
  // "VERSION" -> version.value,
  "NAME" -> (name.in(root)).value,
  "GROUP" -> (organization.in(root)).value,
  "YEAR" -> r2.copyrightYearRange(2020),
  "COPYRIGHT_HOLDER" -> "Marc Esquerra <esquerra@bryghts.com>"
)

val r2SonatypePublishTo =
  taskKey[Option[Resolver]]("Configures where to publish to, when publishing to sonatype/maven central")

val r2SonatypeDoRelease =
  taskKey[Unit]("Pushes the project to maven central")

r2SonatypePublishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

r2SonatypeDoRelease := {
  if (isSnapshot.value)
    ()
  else
    Command.process("sonatypeRelease", state.value)
}


addCommandAlias(
  "r2SonatypeDoPublish",
  ";set publishTo := r2SonatypePublishTo.value;publishSigned;r2SonatypeDoRelease")

lazy val root: Project =
  project
    .in(file("."))
    .r2Root
    .withCapabilities( caps.Bintray )
    .enablePlugins( NativeImagePlugin )
    .settings (
      r2BintrayOwner := R2BintrayOwner.R2BintrayOrganization("bryghts"),
      r2BintrayRepository := "bryght.space"
     )
    .settings(

       scalaVersion := theScalaVersion

     , name := "scala-app-template"


     , Compile / mainClass := Some(s"$group.Main")

     // , libraryDependencies += "com.lihaoyi" %%% "fastparse" % "2.2.4"


     , scalacOptions ++= Seq(
         "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
         "-encoding", "utf-8",                // Specify character encoding used by source files.
         "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
         "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
         "-Xfatal-warnings",                  // Fail the compilation if there are any warnings.
         // "-Yexplicit-nulls",
       )

    )
