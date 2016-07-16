import helpr.{FastaParser, RNAToProtein}

val dir = System.getProperty("user.dir") + "/linkdir/"
val data_fn = dir + "data/RNASplicing.fasta"

val l = FastaParser.parse(data_fn)

val DNA = l(0)._2
val EXONS = l.drop(1)