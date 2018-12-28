package courses.schema

import courses.persistence.UserRepository

class CourseContext(val userRepository: UserRepository) {

}
