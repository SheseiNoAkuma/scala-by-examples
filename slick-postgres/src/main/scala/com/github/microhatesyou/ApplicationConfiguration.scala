package com.github.microhatesyou

import com.typesafe.config._

trait ApplicationConfiguration {
  lazy val conf: Config = ConfigFactory.load()
  lazy val port: Int    = conf.getInt("application.port")
}
