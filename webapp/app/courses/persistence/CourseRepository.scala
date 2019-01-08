package courses.persistence

import courses.models.Course

import scala.concurrent.Future

class CourseRepository {
  def courses: Future[List[Course]] = Future.successful {
    Database.courses
  }
}
