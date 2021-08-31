import foo.definitions.{ApiError, Pet}

object PetService {

  def createPet(body: Pet, requestId: String): Either[ApiError, Pet] = {
    if (requestId == "KO")
      Left(ApiError("HD01", "Invalid request id value"))
    else
      Right(body)
  }
}
