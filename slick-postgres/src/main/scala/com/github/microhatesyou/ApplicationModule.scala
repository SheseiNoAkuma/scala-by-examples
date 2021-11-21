package com.github.microhatesyou

import com.github.microhatesyou.persistence.PetDao
import com.github.microhatesyou.route.PetRouteDispatcher
import com.github.microhatesyou.service.PetService
import slick.jdbc.JdbcBackend.Database

/** dependency injection module
  */
trait ApplicationModule { self: ApplicationConfiguration =>

  lazy val db = Database.forConfig("postgres")

  lazy val petDao: PetDao                    = new PetDao(db)
  lazy val petService: PetService            = new PetService(petDao)
  lazy val petDispatcher: PetRouteDispatcher = new PetRouteDispatcher(petService)
}
