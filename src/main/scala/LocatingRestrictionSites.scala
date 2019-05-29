import helpers.{Challenge, FastaParser}

object LocatingRestrictionSites extends App {

  Challenge("LocatingRestrictionSites.fasta") provides { data =>

    // This helper build a raw string of DNA based on fasta file
    val strand = FastaParser.getRawString(data)

    val compl = Map('A' -> 'T', 'G' -> 'C', 'C' -> 'G', 'T' -> 'A')

    def browse(str: String)(lst: List[(Int, Int)])(idx: Int): List[(Int, Int)] = {

      // private mutable list
      var list = lst

      // Method to find palindrome on a 12 nt long string
      // (!) Side effects on browse() scope
      def palindromes(substr: String, it: Int = 0) {
        if (substr.length > 2) {
          if (substr == substr.map(compl).reverse)
            list = (idx + it, substr.length) :: list
          palindromes(substr.tail.init, it + 1)
        }
      }

      // Add palindromes to browse()'s field "list"
      // taking 12 nt right from actual index
      palindromes(str take 12)

      // While string not empty, go on
      if (str.isEmpty) lst else browse(str.tail)(list)(idx + 1)
    }

    // Plot in reverse building-order
    val result = browse(strand)(Nil)(1).reverse

    result foreach println

  }

}