import scala.io.Source

val dir = System.getProperty("user.dir") + "/linkdir/"
val data_fn = dir + "data/FindingMotifinDNA.txt"

val lines = Source.fromFile(data_fn).getLines()

val DNA = lines.next()
val rawSEQ = lines.next()

// Need to use a lookahead as it doesn't consume a whole string
// and permits overlapping !
val SEQ = s"(${rawSEQ.head})(?=(${rawSEQ.tail}))"

SEQ.r.findAllMatchIn(DNA).foreach(i => print(i.start + 1 + " "))
