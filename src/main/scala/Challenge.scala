package challenges

import scala.io.Source

case class Challenge(filename: String) {
  val data = Source.fromResource(filename).mkString
}