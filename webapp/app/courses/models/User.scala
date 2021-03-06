package courses.models

import courses.schema.{Identifiable, SchemaDefinition}
import sangria.macros.derive._
import sangria.schema.ObjectType

case class User(id: Long, name: String) extends Identifiable

object User {
  implicit val UserType: ObjectType[Unit, User] = deriveObjectType[Unit, User](
    Interfaces(SchemaDefinition.IdentifiableType)
  )
}
