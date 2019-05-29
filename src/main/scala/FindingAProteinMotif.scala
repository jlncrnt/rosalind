import helpers.{Challenge, FastaParser}

import scala.annotation.tailrec
import scala.io.Source

object FindingAProteinMotif extends App {

  Challenge("FindingAProteinMotif.txt") provides { data =>

    val prots = data.getLines
    val endpoint = "https://www.uniprot.org/uniprot/"

    def lst(): Iterator[(String, List[Int])] = for (p <- prots) yield {
      val s = Source.fromURL(endpoint + p + ".fasta")
      val raw = try {
        val ss = s.mkString
        val str = FastaParser.parseUniprot(ss).head._2
        val l = returnlocs(str)
        (p, l)
      } finally {
        s.close()
      }
      raw
    }

    def returnlocs(prot: String): List[Int] = {
      @tailrec
      def locations(s: List[Char])(acc: List[Int])(idx: Int): List[Int] = s match {
        case a :: b :: c :: d :: xs if
        a == 'N' &&
          b != 'P' &&
          (c == 'S' || c == 'T') &&
          d != 'P' => locations(s.tail)(idx :: acc)(idx + 1)
        case a :: b :: c :: d :: xs => locations(s.tail)(acc)(idx + 1)
        case _ => acc.reverse
      }

      locations(prot.toList)(Nil)(1)

    }

    for (i <- lst()) {
      if (i._2.nonEmpty) {
        println(i._1)
        println(i._2.mkString(" "))
      }
    }

  }

}
