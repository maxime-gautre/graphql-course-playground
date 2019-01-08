package courses.persistence

import courses.models.{Course, User}


object Database {

  val users = List(
    User(1, "Laurie Santos"), // https://www.coursera.org/learn/the-science-of-well-being
    User(2, "Walter Sinnott-Armstrong"), // https://www.coursera.org/learn/understanding-arguments
    User(3, "Ram Neta"), // https://www.coursera.org/learn/understanding-arguments
    User(4, "Brad Hokanson"), // https://www.coursera.org/learn/creative-problem-solving
    User(5, "Jody Nyboer"), // https://www.coursera.org/learn/creative-problem-solving
    User(6, "Charles Severance"), // https://www.coursera.org/specializations/python
    User(7, "Daniel M Kane"), // https://www.coursera.org/specializations/data-structures-algorithms
    User(8, "Neil Rhodes"), // https://www.coursera.org/specializations/data-structures-algorithms
    User(9, "Pavel Pevzner"), // https://www.coursera.org/specializations/data-structures-algorithms
    User(10, "Michael Levin"), // https://www.coursera.org/specializations/data-structures-algorithms
    User(11, "Alexander S. Kulikov"), // https://www.coursera.org/specializations/data-structures-algorithms
    User(12, "Arvind Narayanan"), // https://www.coursera.org/learn/cryptocurrency
    User(13, "Mia Minnes"), // https://www.coursera.org/specializations/java-object-oriented
    User(14, "Leo Porter"), // https://www.coursera.org/specializations/java-object-oriented
    User(15, "Christine Alvarado"), // https://www.coursera.org/specializations/java-object-oriented
    User(16, "Dan Boneh"), // https://www.coursera.org/learn/crypto

  )

  val courses = List(
    Course(1, "The science of Well-Being", List(1), free = true),
    Course(2, "Think Again I: How to Understand Arguments", List(2, 3), free = true),
    Course(3, "Creative Problem Solving", List(4, 5), free = false),
    Course(4, "Python for Everybody", List(6), free = false),
    Course(5, "Data Structures and Algorithms", List(7, 8, 9, 10, 11), free = true),
    Course(6, "Bitcoin and Cryptocurrency Technologies", List(12), free = true),
    Course(7, "Object Oriented Java Programming: Data Structures and Beyond", List(13, 14, 15), free = true),
    Course(8, "Cryptography I", List(16), free = true)
  )
}
