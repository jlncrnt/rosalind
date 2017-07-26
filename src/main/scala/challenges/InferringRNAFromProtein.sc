import scala.io.Source
import helpr.Tables

val dir = System.getProperty("user.dir") + "/linkdir/"
val fn = dir + "data/InferringmRNAfromProteinData.txt"

// Simple RNA Codon -> Amino Acid table
val RNAtable = Tables.RNA

// protein string in file
val prot = Source.fromFile(fn).getLines.next()

// Take all Amino Acid, make a map of Name -> Counts
// Filter Stop Codons
val counts = RNAtable.toList
                     .map(_.swap)
                     .map(_._1)
                     .filterNot(_ == "Stop")
                     .groupBy(identity)
                     .map { case (a,b) => (a.toArray.head, b.length) }

// Fold left, starting with 3 stop codons, taking
// modulo at each step.
prot.map(counts).foldLeft(3)(_ * _ % 1000000)
