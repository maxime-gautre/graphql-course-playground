package courses.schema

import sangria.execution.deferred.{DeferredResolver, Fetcher}
import sangria.schema._
import courses.models.{Course, User}

object SchemaDefinition {

  val Id = Argument("id", LongType)

  val IdentifiableType = InterfaceType(
    "Identifiable",
    fields[Unit, Identifiable](
      Field("id", LongType, resolve = _.value.id)
    )
  )
  val usersFetcher = Fetcher(
    (ctx: CourseContext, ids: Seq[Long]) => ctx.userRepository.users(ids)
  )

  val Query = ObjectType(
    "Query", fields[CourseContext, Unit](
      Field("users", ListType(User.UserType), resolve = c => c.ctx.userRepository.users),
      Field(
        "user",
        OptionType(User.UserType),
        arguments = Id :: Nil,
        resolve = c => usersFetcher.deferOpt(c.arg(Id))
      ),
      Field(
        "courses",
        ListType(Course.CourseType),
        resolve = c => c.ctx.courseRepository.courses
      )
    ))

  val Resolver: DeferredResolver[CourseContext] = DeferredResolver.fetchers(usersFetcher)

  val CoursesSchema = Schema(Query)
}
