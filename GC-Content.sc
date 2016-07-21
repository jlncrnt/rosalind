import helpr.FastaParser

val DNA = FastaParser.parse("data.fasta")

def GCContent(s: String): Float = {
  val G = s.count(_ == 'G')
  val C = s.count(_ == 'C')
  (G+C) / s.length.toFloat * 100
}

val contents = DNA.map(x => (x._1.tail,GCContent(x._2)))
val biggest = contents.maxBy(_._2)

println(biggest._1 + "\n" + biggest._2)
