package com.github.microhatesyou.akkahttp.plain

import com.github.microhatesyou.akkahttp.plain.route.HelloRoute
import com.github.microhatesyou.akkahttp.plain.service.HelloService

trait ApplicationModule {
  self: ApplicationConfiguration =>

  val accountingClient: HelloService = new HelloService
  val accountingRoute: HelloRoute    = new HelloRoute(accountingClient)
}
