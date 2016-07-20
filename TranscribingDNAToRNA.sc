import scala.io.Source

Source.fromFile("data.txt").mkString.replaceAll("T","U")