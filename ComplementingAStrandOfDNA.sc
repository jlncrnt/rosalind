import scala.io.Source

val strand = Source.fromFile("data.txt").mkString

val complement = strand.map(Map('A'->'T', 'G'->'C', 'C'->'G', 'T'->'A'))
