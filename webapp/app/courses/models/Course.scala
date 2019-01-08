package courses.models

import courses.schema.{Identifiable, SchemaDefinition}
import sangria.macros.derive._
import sangria.schema.{Field, ListType, ObjectType}

case class Course(id: Long, title: String, teachers: List[Long], free: Boolean) extends Identifiable

object Course {
  implicit val CourseType: ObjectType[Unit, Course] = deriveObjectType[Unit, Course](
    Interfaces(SchemaDefinition.IdentifiableType),
    ReplaceField(
      "teachers",
      Field(
        "teachers",
        ListType(User.UserType),
        resolve = c => SchemaDefinition.usersFetcher.deferSeq(c.value.teachers)
      )
    )
  )
}
