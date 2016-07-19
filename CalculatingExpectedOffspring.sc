import scala.io.Source

val numbers = Source.fromFile("/home/julien/Desktop/RosalindChallenges/data/CalculatingExpectedOffspring.txt").mkString

val values = numbers.split(" ").toList.map(_.toInt)

val l = List("AA", "Aa", "aa")

val comb = (l ::: l).combinations(2)

comb.map(c => List(c)).toList