import helpr.FastaParser

val fasta = FastaParser.parse("data.fasta")

// Prettify the fasta to ( ID , ( START, END ))
val pretty = fasta map { case (a, b) => (a tail, (b take 3, b takeRight 3)) }

// Foreach combination, check is start matches end
// and names are not the same
for {
  pair1 <- pretty
  pair2 <- pretty
  if pair1._2._2 == pair2._2._1
  if pair1._1    != pair2._1
} yield (pair1._1, pair2._1)
