package courses.persistence

import scala.concurrent.Future

import courses.models.User

class UserRepository() {

  def users() = Future.successful(List(
    User(1, "Thierry Henry"),
    User(10, "Lionel Messi"),
  ))
}
