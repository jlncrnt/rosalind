import scala.io.Source

val strand = Source.fromFile("data.txt").mkString

// Simply map each nucleotide with its complement
val complement = strand.map(Map('A'->'T', 'G'->'C', 'C'->'G', 'T'->'A'))
