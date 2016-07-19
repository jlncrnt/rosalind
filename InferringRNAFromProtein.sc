import scala.io.Source

val RNAtable = Tables.RNA

val prot = Source.fromFile("data.txt").getLines.next()

// RNA Table Helper
val counts = RNAtable.toList
                     .map(_.swap)
                     .map(_._1)
                     .filterNot(_ == "Stop")
                     .groupBy(identity)
                     .map { case (a,b) => (a.toArray.head, b.length) }

// 3 for stop codons
prot.map(counts).foldLeft(3)(_ * _ % 1000000)