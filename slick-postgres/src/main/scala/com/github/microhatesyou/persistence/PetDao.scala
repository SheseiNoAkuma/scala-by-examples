package com.github.microhatesyou.persistence

import com.github.microhatesyou.generated.definitions.Pet
import net.liftweb.json._
import slick.jdbc.PostgresProfile.api._

import java.util.UUID
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class PetEntity(id: UUID, name: String, tag: Option[String] = None)

class PetDao(val db: Database) {

  val table = TableQuery[PetTable]
  db.run(table.schema.createIfNotExists)

  class PetTable(tag: Tag) extends Table[PetEntity](tag, None, "pet") {
    def id   = column[UUID]("id", O.PrimaryKey)
    def name = column[String]("name")
    def tags = column[Option[String]]("tag")
    def *    = (id, name, tags).shaped.<>(PetEntity.tupled, PetEntity.unapply)
  }

  implicit val formats: DefaultFormats.type = DefaultFormats

  def persist(pet: Pet): Future[String] = {
    val entity = PetEntity(
      id = UUID.randomUUID(),
      name = pet.name,
      tag = pet.status
    )

    val insertOrder = table += entity
    db.run(insertOrder).map(_ => entity.id.toString)
  }

  def findAll(): Future[Vector[PetEntity]] = {
    val orders = TableQuery[PetTable]
    val q      = orders

    db.run(q.result)
      .map(_.toVector)
  }
}
