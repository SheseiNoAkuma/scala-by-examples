package com.github.microhatesyou

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import com.github.microhatesyou.generated.pet.PetResource
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

object Application extends App with LazyLogging with ApplicationModule with ApplicationConfiguration {

  implicit def actorSystem: ActorSystem = ActorSystem()

  val routes = concat(
    PetResource.routes(petDispatcher)
  )

  Http()
    .newServerAt("0.0.0.0", port)
    .bind(routes)
    .map(_.addToCoordinatedShutdown(hardTerminationDeadline = 10.seconds))

  logger.info(s"Application Running on port $port")
  logger.info(s"Configuration: $conf")
}
