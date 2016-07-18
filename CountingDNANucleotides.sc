import scala.collection.immutable.ListMap
import scala.io.Source

val dna = Source.fromFile("data.txt").mkString

ListMap(dna.groupBy(identity)
           .mapValues(_.length)
           .toSeq
           .sortWith(_._1 < _._1):_*)
           .values
           .mkString(" ")
