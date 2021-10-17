import sbt._

object Dependencies {

  private val jaxbApiVersion = "2.3.1"
  private val catsVersion = "2.6.1"

  private lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.9"
  private lazy val jaxb = "javax.xml.bind" % "jaxb-api" % jaxbApiVersion
  private lazy val catsCore = "org.typelevel" %% "cats-core" % catsVersion

  private lazy val sl4j ="org.slf4j" % "slf4j-simple" % "1.7.32"
  private lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4"

  private lazy val sttp = "com.softwaremill.sttp.client3" %% "core" % "3.3.15"
  private lazy val sttpCircle = "com.softwaremill.sttp.client3" %% "circe" % "3.3.15"

  private object AkkaDependencies {
    private lazy val akkaVersion = "2.6.16"
    private lazy val akkaHttpVersion = "10.2.6"
    lazy val actor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
    lazy val stream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
    lazy val http = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
    lazy val spray = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
    lazy val testKit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
    lazy val testKitStream = "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion
  }

  private object CircleDependencies {
    private val circeVersion = "0.14.1"
    lazy val core = "io.circe" %% "circe-core" % circeVersion
    lazy val generics = "io.circe" %% "circe-generic" % circeVersion
    lazy val parser = "io.circe" %% "circe-parser" % circeVersion
  }


  object PlainAkkaHttp {
    lazy val libraries = Seq(
      AkkaDependencies.actor,
      AkkaDependencies.stream,
      AkkaDependencies.http,
      AkkaDependencies.spray,
      AkkaDependencies.testKit % Test,
      AkkaDependencies.testKitStream % Test,
      CircleDependencies.core,
      CircleDependencies.generics,
      CircleDependencies.parser,
      jaxb,
      catsCore,
      sl4j,
      sttp,
      sttpCircle,
      scalaLogging,
      scalaTest % Test
    )
  }
}
