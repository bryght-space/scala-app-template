
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
