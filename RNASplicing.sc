import helpr.{FastaParser, RNAToProtein}

val dir = System.getProperty("user.dir") + "/linkdir/"
val data_fn = dir + "data/RNASplicing.fasta"

val l = FastaParser.parse(data_fn)

var DNA = l(0)._2
val exons = l.drop(1).map(x => x._2)

for(e <- exons) DNA = DNA.replaceAll(e,"")

RNAToProtein.translate(DNA.replaceAll("T","U")).replaceAll("Stop","")
