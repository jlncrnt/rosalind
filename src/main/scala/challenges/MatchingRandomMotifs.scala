package challenges

object MatchingRandomMotifs extends Challenge("MatchingRandomMotifs.txt") {

  def result(d: String = data) = {
    val (n, gc, dna) = d.split("\\n|\\s") match {
      case Array(n, g, d) => (n.toInt, g.toDouble, d)
    }

    val m = "ATGC" zip List((1-gc)/2, (1-gc)/2, gc/2, gc/2)

    // Probability that a random string matches the given string
    val peq = dna.map(m.toMap).product
    // Probability to NOT match the given string
    val pneq = 1-peq
    // Probability to NOT FIND a matching string in N attempt
    val npneq = math.pow(pneq, n)
    // Probability to find one matching string in N attempt
    val pfind = 1-npneq

    ((pfind * 1000).round / 1000.0).toString

  }

}
