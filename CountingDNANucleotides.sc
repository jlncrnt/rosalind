import scala.collection.immutable.ListMap
import scala.io.Source

val dna = Source.fromFile("/home/julien/Desktop/RosalindChallenges/data/CountingDNANucleotides.txt").mkString

// Build a listMap from grouped nucleotides
ListMap(dna.groupBy(identity)
           // Find each group length
           .mapValues(_.length)
           // Cast to Seq to be able to sort it
           .toSeq
           // Sort values based on keys
           .sortWith(_._1 < _._1):_*)
           // Keep values
           .values
           // Prettify result
           .mkString(" ")

