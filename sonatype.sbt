
// To sync with Maven central, you need to supply the following information:
publishMavenStyle := true

// Open-source license of your choice
licenses := Seq("MIT" -> url("https://opensource.org/licenses/MIT"))

// // Where is the source code hosted: GitHub or GitLab?
// import xerial.sbt.Sonatype._
// sonatypeProjectHosting := Some(GitHubHosting("username", "projectName", "user@example.com"))
// // or
// sonatypeProjectHosting := Some(GitLabHosting("username", "projectName", "user@example.com"))

// // or if you want to set these fields manually
// homepage := Some(url("https://(your project url)"))
// scmInfo := Some(
//   ScmInfo(
//     url("https://github.com/(account)/(project)"),
//     "scm:git@github.com:(account)/(project).git"
//   )
// )
// developers := List(
//   Developer(id="(your id)", name="(your name)", email="(your e-mail)", url=url("(your home page)"))
// )
