package com.github.microhatesyou.akkahttp.plain

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.concat
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

object Application extends App with LazyLogging with ApplicationModule with ApplicationConfiguration {

  implicit def actorSystem: ActorSystem = ActorSystem()

  val routes = concat(
    accountingRoute.routes: _*
  )

  val bindingFuture = Http()
    .newServerAt("0.0.0.0", port)
    .bind(routes)
    .map(_.addToCoordinatedShutdown(hardTerminationDeadline = 10.seconds))

  logger.info(s"Gateway Running on port $port")
  logger.info(s"Configuration: $conf")
}
