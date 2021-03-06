import helpers.Challenge

object CalculatingProteinMass extends App {

  Challenge("CalculatingProteinMass.txt") provides { data =>

    val strand = data.mkString

    // Build protein map
    // compile regex pattern and feed the
    // map raw string, then convert it to
    // a map of Char -> Float
    val proteinMap: Map[Char, Float] = {
      "(\\w)\\s+(\\d+.\\d+)".r
        .findAllMatchIn(
          """A   71.03711
            |    C   103.00919
            |    D   115.02694
            |    E   129.04259
            |    F   147.06841
            |    G   57.02146
            |    H   137.05891
            |    I   113.08406
            |    K   128.09496
            |    L   113.08406
            |    M   131.04049
            |    N   114.04293
            |    P   97.05276
            |    Q   128.05858
            |    R   156.10111
            |    S   87.03203
            |    T   101.04768
            |    V   99.06841
            |    W   186.07931
            |    Y   163.06""".stripMargin)
        .map(m => (m.group(1).toCharArray()(0),
          m.group(2).toFloat))
        .toMap
    }

    // Apply the map on the strand and sum all
    val sum = strand.map(proteinMap).sum

    println(sum)

  }

}
