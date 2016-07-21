import scala.io.Source

val dir = System.getProperty("user.dir") + "/linkdir/"
val data_fn = dir + "data/CountingDNANucleotides.txt"

val strand = Source.fromFile(data_fn).mkString

// Simply map each nucleotide with its complement
val complement = strand.map(Map('A'->'T', 'G'->'C', 'C'->'G', 'T'->'A'))
