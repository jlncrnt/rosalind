import helpers.{Challenge, FastaParser}

object FindingASharedSplicedMotif extends App {

  Challenge("FindingASharedSplicedMotif.fasta") provides { data =>

    val lst = FastaParser.parseDNA(data)

    // Match on both strings
    def LCS(str1: String, str2: String): String = (str1, str2) match {
      // If one of them is empty, return empty string
      case (s1, s2) if s1.length < 1 || s2.length < 1 => ""
      // Add char to return string if chars are equals
      case (s1, s2) if s1.head == s2.head => s1.head.toString ++ LCS(s1.tail, s2.tail)
      case (s1, s2) =>
        // Return longest String of each call
        def l1 = LCS(s1, s2.tail)

        def l2 = LCS(s1.tail, s2)

        if (l1.length > l2.length) l1 else l2
    }

    println(LCS(lst(0)._2, lst(1)._2))

  }

}