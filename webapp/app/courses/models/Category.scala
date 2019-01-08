package courses.models

import courses.schema.{Identifiable, SchemaDefinition}
import sangria.macros.derive._
import sangria.schema.ObjectType

case class Category(id: Long, name: String) extends Identifiable

object Category {
  implicit val CategoryType: ObjectType[Unit, Category] = deriveObjectType[Unit, Category](
    Interfaces(SchemaDefinition.IdentifiableType)
  )
}
