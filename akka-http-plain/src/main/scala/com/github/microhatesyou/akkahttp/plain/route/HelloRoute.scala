package com.github.microhatesyou.akkahttp.plain.route

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.{Directives, Route}
import com.github.microhatesyou.akkahttp.plain.model.HelloMessage
import com.github.microhatesyou.akkahttp.plain.service.HelloService
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

class HelloRoute(helloService: HelloService) extends Directives with JsonSupport {

  lazy val routes: Vector[Route] = Vector(accounts, transactions)

  protected lazy val accounts: Route =
    path("v1" / "hello") {
      complete {
        HttpResponse(entity = HttpEntity(ContentTypes.`application/json`, helloService.helloString))
      }
    }

  protected lazy val transactions: Route =
    path("v1" / "helloObject") {
      parameters("name") { (name: String) =>
        complete {
          helloService.helloObject(name = name)
        }
      }
    }
}
// collect your json format instances into a support trait:
trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val itemFormat: RootJsonFormat[HelloMessage] = jsonFormat2(HelloMessage)
}
