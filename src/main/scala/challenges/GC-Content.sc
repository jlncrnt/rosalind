import helpr.FastaParser

val dir = System.getProperty("user.dir") + "/linkdir/"
val fn = dir + "data/GC-content.fasta"

val DNA = FastaParser.parse(fn)

// Find GC-Content for one string
def GCContent(s: String): Float = {
  val G = s.count(_ == 'G')
  val C = s.count(_ == 'C')
  (G+C) / s.length.toFloat * 100
}

// Build map of (Read ID -> GC-Content of read)
val contents = DNA.map(x => (x._1.tail,GCContent(x._2)))

// Find max by value
val biggest = contents.maxBy(_._2)

println(biggest._1 + "\n" + biggest._2)
