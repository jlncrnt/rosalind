import helpers.Challenge

object MatchingRandomMotifs extends App {

  Challenge("MatchingRandomMotifs.txt") provides { data =>

    val (n, gc, dna) = data.mkString.split("\\n|\\s") match {
      case Array(a, g, d) => (a.toInt, g.toDouble, d)
    }

    val m = "ATGC" zip List((1 - gc) / 2, (1 - gc) / 2, gc / 2, gc / 2)

    // Probability that a random string matches the given string
    val peq = dna.map(m.toMap).product
    // Probability to NOT match the given string
    val pneq = 1 - peq
    // Probability to NOT FIND a matching string in N attempt
    val npneq = math.pow(pneq, n)
    // Probability to find one matching string in N attempt
    val pfind = 1 - npneq

    val result = ((pfind * 1000).round / 1000.0).toString

    println(result)

  }

}
