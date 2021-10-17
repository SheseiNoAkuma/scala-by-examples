package com.github.microhatesyou.akkahttp.plain

import com.typesafe.config.{Config, ConfigFactory}

trait ApplicationConfiguration {
  lazy val conf: Config = ConfigFactory.load()
  lazy val port: Int    = conf.getInt("server.port")
}
