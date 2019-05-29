import helpers.{Challenge, FastaParser, RNAtoProtein}

object RNASplicing extends App {

  Challenge("RNASplicing.fasta") provides { data =>

    val l = FastaParser.parseDNA(data)

    var DNA = l(0)._2
    val exons = l.drop(1).map(x => x._2)

    // Strip strand based on exon string
    for (e <- exons) DNA = DNA.replaceAll(e, "")

    val result = RNAtoProtein.translate(DNA.replaceAll("T", "U")).replaceAll("Stop", "")

    println(result)

  }

}