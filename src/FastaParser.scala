/**
  * Created by julien on 16.07.16.
  */

package helpr

object FastaParser {
  import scala.annotation.tailrec

  def parseDNA(filename: String) = {
    import scala.io.Source
    parserInner(Source.fromFile(filename).getLines())("")(List[(String,String)]())("")("[ATGC]+")
  }

  def parseUniprot(str: String) = {
    import scala.io.Source
    parserInner(str.split("\n").iterator)("")(List[(String,String)]())("")("^[FLSYCWLPHQRIMTNKVADEG]+$")
  }

  def parseString(str: String) = {
    parserInner(str.split("\n").iterator)("")(List[(String,String)]())("")("[ATGC]+")
  }

  def getRawString(filename: String): String = {

    def buildRaw(fasta: List[(String,String)])(str: String): String = fasta match {
      case x::xs => buildRaw(xs)(str ++ x._2)
      case Nil => str
    }

    buildRaw(parseDNA(filename))("")
  }

  @tailrec
  private def parserInner(lines: Iterator[String])
                         (acc: String)
                         (lst: List[(String,String)])
                         (curr: String)
                         (matcher: String): List[(String,String)] = {
    if (lines.hasNext) {
      lines.next match {
        case s if s.matches(matcher) => parserInner(lines)(acc + s)(lst)(curr)(matcher)
        case s if acc.isEmpty => parserInner(lines)(acc)(lst)(s)(matcher)
        case s if acc.nonEmpty => parserInner(lines)("")((curr,acc) :: lst)(s)(matcher)
      }
    }
    else ((curr,acc) :: lst).reverse
  }
}
