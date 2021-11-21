package com.github.microhatesyou.route

import com.github.microhatesyou.generated.definitions.{ApiError, Pet}
import com.github.microhatesyou.generated.pet.{PetHandler, PetResource}
import com.github.microhatesyou.service.PetService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PetRouteDispatcher(petService: PetService) extends PetHandler {
  override def createPet(respond: PetResource.CreatePetResponse.type)(body: Pet): Future[PetResource.CreatePetResponse] =
    petService
      .createPet(body)
      .map(respond.OK)
      .recover { case e =>
        respond.BadRequest(ApiError("todo", e.getMessage))
      }

  override def getPets(respond: PetResource.GetPetsResponse.type)(): Future[PetResource.GetPetsResponse] =
    petService.findAll()
      .map(list => respond.OK(list))
}
