import helpr.FastaParser

import scala.annotation.tailrec
import scala.io.Source

val dir = System.getProperty("user.dir") + "/linkdir/"
val data_fn = dir + "data/FindingAProteinMotif.txt"

val prots = Source.fromFile(data_fn).getLines()

val APIAccess = "http://www.uniprot.org/uniprot/"

def lst(): Iterator[(String,List[Int])] = for (p <- prots) yield {
  val raw = Source.fromURL(APIAccess + p + ".fasta").mkString
  val str = FastaParser.parseUniprot(raw)(0)._2
  val l   = returnlocs(str)
  (p, l)
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

for (i <- lst) {
  if(i._2.nonEmpty) {
    println(i._1)
    println(i._2.mkString(" "))
  }
}
