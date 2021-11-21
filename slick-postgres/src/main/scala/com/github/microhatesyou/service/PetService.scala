package com.github.microhatesyou.service

import com.github.microhatesyou.generated.definitions.Pet
import com.github.microhatesyou.persistence.PetDao

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PetService(petDao: PetDao) {

  def createPet(body: Pet): Future[Pet] =
    petDao
      .persist(body)
      .map(uuid => body.copy(id = Some(uuid)))

  def findAll(): Future[Vector[Pet]] =
    petDao
      .findAll()
      .map(
        _.map(e =>
          Pet(
            id = Some(e.id.toString),
            name = e.name,
            status = e.tag
          )
        )
      )
}
