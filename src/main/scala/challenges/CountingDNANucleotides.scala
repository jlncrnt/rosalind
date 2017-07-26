package challenges

object CountingDNANucleotides extends Challenge("CountingDNANucleotides.txt") {

  import scala.collection.immutable.ListMap

  // Build a listMap from grouped nucleotides
  def result(data: String = data) = ListMap(data.groupBy(identity)
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

}