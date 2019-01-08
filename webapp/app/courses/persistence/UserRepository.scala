package courses.persistence

import scala.concurrent.Future

import courses.models.User

class UserRepository() {

  def users: Future[List[User]] = Future.successful(Database.users)

  def users(ids: Seq[Long]): Future[List[User]] = Future.successful(Database.users.filter(u => ids.contains(u.id)))

  def user(id: Long): Future[Option[User]] = Future.successful(Database.users.find(_.id == id))
}
