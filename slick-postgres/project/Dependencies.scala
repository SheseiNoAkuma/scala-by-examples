import sbt._

object Dependencies {

  private object AkkaDependencies {
    private lazy val akkaVersion     = "2.6.17"
    private lazy val akkaHttpVersion = "10.2.6"
    lazy val actor                   = "com.typesafe.akka" %% "akka-actor"          % akkaVersion
    lazy val stream                  = "com.typesafe.akka" %% "akka-stream"         % akkaVersion
    lazy val http                    = "com.typesafe.akka" %% "akka-http"           % akkaHttpVersion
    lazy val testKit                 = "com.typesafe.akka" %% "akka-http-testkit"   % akkaHttpVersion
    lazy val testKitStream           = "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion
  }

  private object CircleDependencies {
    private val circeVersion = "0.14.1"
    lazy val core            = "io.circe" %% "circe-core"    % circeVersion
    lazy val generics        = "io.circe" %% "circe-generic" % circeVersion
    lazy val parser          = "io.circe" %% "circe-parser"  % circeVersion
  }

  private object Clients {
    lazy val fireblocks = "com.leonteq.crypto.connector" % "fireblocks" % "0.0.1-SNAPSHOT"
    lazy val taurus     = "com.leonteq.crypto.connector" % "taurus"     % "0.0.1-SNAPSHOT"
    lazy val wireSwarm  = "com.leonteq.crypto.connector" % "wireswarm"  % "0.0.1-SNAPSHOT"
    lazy val metaco     = "com.leonteq.crypto.connector" % "metaco"     % "0.0.1-SNAPSHOT"
    lazy val metacoSdk  = "com.metaco"                   % "sdk"        % "1.0.23"
  }

  private object Slick {
    private lazy val version   = "3.3.3"
    private lazy val namespace = "com.typesafe.slick"
    lazy val slick             = namespace %% "slick"          % version
    lazy val hikaricp          = namespace %% "slick-hikaricp" % version
  }

  private object Postgres {
    private lazy val version   = "42.3.1"
    private lazy val namespace = "org.postgresql"
    lazy val postgresql        = namespace % "postgresql" % version
  }

  private object LiftJson {
    private lazy val version   = "3.5.0"
    private lazy val namespace = "net.liftweb"
    lazy val lift              = namespace %% "lift-json" % version
  }

  private object Jaxb {
    private lazy val version   = "2.3.1"
    private lazy val namespace = "javax.xml.bind"
    lazy val api              = namespace % "jaxb-api" % version
  }

  private object Cats {
    private lazy val version   = "2.6.1"
    private lazy val namespace = "org.typelevel"
    lazy val core              = namespace %% "cats-core" % version
  }

  private object ScalaLogging {
    private lazy val version   = "3.9.4"
    private lazy val namespace = "com.typesafe.scala-logging"
    lazy val core              = namespace %% "scala-logging" % version
  }

  private object ScalaTest {
    private lazy val version   = "3.2.10"
    private lazy val namespace = "org.scalatest"
    lazy val test              = namespace %% "scalatest" % version % Test
  }

  private object Wiremock {
    private lazy val version   = "2.27.2"
    private lazy val namespace = "com.github.tomakehurst"
    lazy val core              = namespace % "wiremock" % version % Test
  }

  object CryptoGatewayLib {
    lazy val libraries = Seq(
      AkkaDependencies.actor,
      AkkaDependencies.stream,
      AkkaDependencies.http,
      AkkaDependencies.testKit       % Test,
      AkkaDependencies.testKitStream % Test,
      CircleDependencies.core,
      CircleDependencies.generics,
      CircleDependencies.parser,
      Slick.slick,
      Slick.hikaricp,
      Postgres.postgresql,
      LiftJson.lift,
      Jaxb.api,
      Cats.core,
      ScalaLogging.core,
      Clients.fireblocks,
      Clients.taurus,
      Clients.wireSwarm,
      Clients.metacoSdk,
      Clients.metaco,
      ScalaTest.test,
      Wiremock.core
    )
  }
}
