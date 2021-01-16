
addSbtPlugin("org.scalameta" % "sbt-native-image" % "0.2.2")
addSbtPlugin("ch.epfl.lamp" % "sbt-dotty" % "0.5.1")

addSbtPlugin("com.bryghts" % "r2bot" % "0.0.6")

resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
