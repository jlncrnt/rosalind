import classes.rosalind.helpers.FastaParser

val dir = System.getProperty("user.dir") + "/linkdir/"
val data_fn = dir + "data/RNASplicing.fasta"

val l = FastaParser.parse(data_fn)

