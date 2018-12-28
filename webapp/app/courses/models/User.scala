package courses.models

import sangria.macros.derive.deriveObjectType
import sangria.schema.ObjectType

case class User(id: Long, name: String)

object User {
  implicit val UserType: ObjectType[Unit, User] = deriveObjectType[Unit, User]()
}
