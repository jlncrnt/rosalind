import scala.annotation.tailrec
import scala.io.Source
import scala.util.matching.Regex
import java.io._

val data_fn = "data.txt"
val codons_fn = "codons.txt"
val out_fn = "out.txt"
val data_fh = Source.fromFile(data_fn)
val codons_fh = Source.fromFile(codons_fn)

val pattern = new Regex("([ATGC]{3}) ([a-zA-Z]+)")
val allMatches = pattern.findAllMatchIn(codons_fh.mkString)
val codonMap = (for (i <- allMatches) yield (i.group(1), i.group(2))).toMap

val startcodon = ('A','T','G')

val glist = for(
  line <- data_fh.getLines()
  if line.matches("[ATGC]+")
) yield line

case class ProteinFinder(str: String) {

  private val compl = Map('A'->'T','G'->'C','C'->'G','T'->'A')

  private def findMet(L : List[Char]): List[String] = L match {
    case a::b::c::tail if (a,b,c) == startcodon => buildProteinFrom(L)("") match {
      case Some(i) => i :: findMet(b::c::tail)
      case None => findMet(b::c::tail)
    }
    case a::b::c::tail => {
      findMet(b::c::tail)
    }
    case _ => List[String]()
  }

  @tailrec
  private def buildProteinFrom(lst: List[Char])(prot: String): Option[String] = lst match {
    case a::b::c::tail if codonMap(List(a,b,c).mkString) == "Stop" => Some(prot)
    case a::b::c::tail => buildProteinFrom(tail)(prot + codonMap(List(a,b,c).mkString))
    case _ => None
  }

  def find() = (findMet(str.toList) ::: findMet(str.map(x => compl(x)).reverse.toList)).distinct
}

val resList = glist.map(ProteinFinder).map(x => x.find())

def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
  val p = new java.io.PrintWriter(f)
  try { op(p) } finally { p.close() }
}

printToFile(new File(out_fn)) { p =>
  for(r <- resList) r.foreach(p.println)
}
