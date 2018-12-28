name := "graphql-course-playground"

version := "0.1"

scalaVersion := "2.12.8"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, BuildInfoPlugin, SbtWeb)
  .settings(Settings.commonPlay: _*)
  .settings(
    libraryDependencies ++= Seq(
      jdbc,
      evolutions,
      "org.sangria-graphql" %% "sangria" % "1.4.2",
      "org.sangria-graphql" %% "sangria-play-json" % "1.0.4",
    )
  )
