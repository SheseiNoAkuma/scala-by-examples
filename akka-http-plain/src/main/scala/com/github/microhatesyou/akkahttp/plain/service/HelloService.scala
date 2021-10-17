package com.github.microhatesyou.akkahttp.plain.service

import com.github.microhatesyou.akkahttp.plain.model.HelloMessage

class HelloService {

  def helloString: String = """{"message": "hello"}"""

  def helloObject(name: String): HelloMessage = HelloMessage(name, "hello")

}
