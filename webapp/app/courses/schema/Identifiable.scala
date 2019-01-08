package courses.schema

import sangria.execution.deferred.HasId

trait Identifiable {
  val id: Long
}

object Identifiable {
  implicit def hasId[T <: Identifiable]: HasId[T, Long] = HasId(_.id)
}
