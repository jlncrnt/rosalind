import helpers.Challenge

object ConsensusAndProfile extends App {

  import helpers.FastaParser
  import scalaz.Scalaz._

  Challenge("ConsensusAndProfile.fasta") provides { data =>

    val reads = FastaParser.parseDNA(data).map(_._2.toList).transpose

    val map = Map('A' -> 0, 'C' -> 0, 'G' -> 0, 'T' -> 0)

    def counts(col: List[Char]): Map[Char, Int] = {
      val m = col.mkString.groupBy(identity).mapValues(_.length)
      map |+| m
    }

    // Dont trust transpose when using non sortable items... (!!!)
    val profile = reads.map(counts) map { _.toList.sortBy(_._1) }

    println(profile.map(x => x.maxBy(_._2)._1).mkString)

    for (line <- profile.transpose) {
      print(line.head._1 + ": ")
      line.foreach(x => print(x._2 + " "))
      println
    }

  }

}
