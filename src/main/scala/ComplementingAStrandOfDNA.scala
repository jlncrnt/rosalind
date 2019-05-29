import helpers.Challenge

object ComplementingAStrandOfDNA extends App {

  Challenge("CountingDNANucleotides.txt") provides { data =>

    val complement = data.map(Map('A' -> 'T', 'G' -> 'C', 'C' -> 'G', 'T' -> 'A')).mkString

    println(complement)

  }

}
