package courses.schema

import sangria.execution.deferred.DeferredResolver
import sangria.schema._

import courses.models.User

object SchemaDefinition {

  val Query = ObjectType(
    "Query", fields[CourseContext, Unit](
      Field("users", ListType(User.UserType), resolve = c => c.ctx.userRepository.users())
    ))

  val Resolver = DeferredResolver.fetchers()

  val CoursesSchema = Schema(Query)
}
