import helpers.Challenge

object ExpectedNumberofRestrictionSites extends App {

  Challenge("ExpectedNumberofRestrictionSites.txt") provides { data =>

    val (List(ns, dna), gcss) = data.mkString.split("\\n|\\s").toList.splitAt(2)
    val gcs = gcss.map(_.toDouble)
    val n = ns.toInt

    def forGC(gcv: Double): Double = {
      val m = "ATGC" zip List((1 - gcv) / 2.0, (1 - gcv) / 2.0, gcv / 2.0, gcv / 2.0)
      val expected_value = dna.map(m.toMap).product
      val r = (n - 1) * expected_value
      (r * 1000).round / 1000.0
    }

    println(gcs.map(v => forGC(v)).mkString(" "))

  }

}