
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

credentials ++= {
  import scala.util.Properties.envOrNone

  val u = envOrNone("SONATYPE_USERNAME")
  val p = envOrNone("SONATYPE_PASSWORD")

  (u, p) match {
    case (Some(user), Some(pass)) =>
      Seq(Credentials("Sonatype Nexus Repository Manager",
            "oss.sonatype.org",
            user,
            pass))
    case _ =>
      Seq()
  }
}


credentials ++= {
  import scala.util.Properties.envOrNone

  envOrNone("MAVEN_CENTRAL_GPG_PRIVATE_KEY_ID") match {
    case Some(key) =>
      Seq(Credentials(
        "GnuPG Key ID",
        "gpg",
        key, // key identifier
        "ignored" // this field is ignored; passwords are supplied by pinentry
      ))

    case _ =>
      Seq()
  }

}
