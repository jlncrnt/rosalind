import helpr.FastaParser

val strand = FastaParser.getRawString("data.fasta")

val compl = Map('A'->'T', 'G'->'C', 'C'->'G', 'T'->'A')

def browse(str: String)(lst: List[(Int, Int)])(idx: Int): List[(Int, Int)] = {

  var list = lst

  // (!) Side effects on browse scope
  def palindromes(substr: String, it: Int = 0) {
    if (substr.length > 2) {
      if (substr == substr.map(compl).reverse)
        list = (idx + it, substr.length) :: list
      palindromes(substr.tail.init, it + 1)
    }
  }

  // Add palindromes to browse's var "list"
  palindromes(str take 12)

  if (str.isEmpty) lst else browse(str.tail)(list)(idx + 1)
}

browse(strand)(Nil)(1)