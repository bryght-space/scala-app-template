
// To sync with Maven central, you need to supply the following information:
publishMavenStyle := true

// Open-source license of your choice
licenses := Seq("MIT" -> url("https://opensource.org/licenses/MIT"))

// Where is the source code hosted: GitHub or GitLab?
import xerial.sbt.Sonatype._
sonatypeProjectHosting := Some(GitHubHosting("marcesquerra", "scala-app-template", "user@example.com"))

developers := List(
  Developer(id="marcesquerra", name="Marc Esquerra", email="esquerra@bryghts.com", url=url("https://github.com/marcesquerra"))
)

credentials += {
  val u = scala.util.Properties.envOrNone("SONATYPE_USERNAME")
  val p = scala.util.Properties.envOrNone("SONATYPE_PASSWORD")

  (u zip p) match {
    case Some((user, pass)) =>
      Credentials("Sonatype Nexus Repository Manager",
            "oss.sonatype.org",
            user,
            pass)
    case None =>
      throw new MessageOnlyException("Sonatype credentials required")
  }
}
