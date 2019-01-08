package courses.schema

import courses.persistence.{CourseRepository, UserRepository}

class CourseContext(val userRepository: UserRepository, val courseRepository: CourseRepository) {

}
