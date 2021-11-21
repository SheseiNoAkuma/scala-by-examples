val dockerRepo = "microbo"

lazy val guardrailSettings = Compile / guardrailTasks := List(
  ScalaServer(
    specPath = (Compile / resourceDirectory).value / "petstore.yaml",
    pkg = "com.github.microhatesyou.generated",
    tracing = false
  )
)

lazy val dockerSettings = Seq(
  docker / dockerfile := {
    val artifact: File = assembly.value
    val artifactTargetPath = s"/app/${artifact.name}"

    new Dockerfile {
      from("openjdk:11")
      add(artifact, artifactTargetPath)
      entryPoint("java", "-jar", artifactTargetPath, "--bind", "0.0.0.0")
      expose(8080)
    }
  },
  docker / imageNames := Seq(
    ImageName(
      namespace = Some(dockerRepo),
      repository = name.value,
      tag = Some("latest")
    ),
    ImageName(
      namespace = Some(dockerRepo),
      repository = name.value,
      tag = Some("v" + version.value)
    )
  )
)

lazy val assemblySettings = assembly / assemblyMergeStrategy := {
  case PathList("META-INF", _*) => MergeStrategy.discard
  case PathList("reference.conf") => MergeStrategy.concat
  case _ => MergeStrategy.first
}

lazy val root = (project in file("."))
  .settings(
    organization := "com.github.microhatesyou",
    scalaVersion := "2.13.3",
    version := "0.0.1-SNAPSHOT",
    name := "slick-postgres",
    scalacOptions ++= Seq(
      "-deprecation", // Emit warning and location for usages of deprecated APIs.
      "-encoding",
      "utf-8", // Specify character encoding used by source files.
      "-explaintypes", // Explain type errors in more detail.
      "-feature", // Emit warning and location for usages of features that should be imported explicitly.
      "-unchecked", // Enable additional warnings where generated code depends on assumptions.
      "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
      "-Xfatal-warnings" // Fail the compilation if there are any warnings.
    ),
    assembly / mainClass := Some("com.github.microhatesyou.Application"),
    assembly / assemblyJarName := "slick-postgres.jar",
    libraryDependencies ++= Dependencies.CryptoGatewayLib.libraries,
    resolvers += Resolver.mavenLocal,
    scalafmtOnCompile := true
  )
  .settings(
    assemblySettings,
    guardrailSettings,
    dockerSettings
  )

enablePlugins(DockerPlugin)
