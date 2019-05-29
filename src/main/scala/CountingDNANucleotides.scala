import helpers.Challenge

object CountingDNANucleotides extends App {

  Challenge("CountingDNANucleotides.txt") provides { data =>

    import scala.collection.immutable.ListMap

    // Build a listMap from grouped nucleotides
    val result = ListMap(data.mkString.groupBy(identity)
      // Find each group length
      .mapValues(_.length)
      // Cast to Seq to be able to sort it
      .toSeq
      // Sort values based on keys
      .sortWith(_._1 < _._1): _*)
      // Keep values
      .values
      // Prettify result
      .mkString(" ")

    println(result)

  }

}