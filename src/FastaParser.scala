/**
  * Created by julien on 16.07.16.
  */

package helpr

object FastaParser {
  import scala.annotation.tailrec

  def parse(filename: String) = {
    import scala.io.Source
    parserInner(Source.fromFile(filename).getLines())("")(List[(String,String)]())("")
  }

  def parseString(str: String) = {
    parserInner(str.split("\n").iterator)("")(List[(String,String)]())("")
  }

  def getRawString(filename: String): String = {

    def buildRaw(fasta: List[(String,String)])(str: String): String = fasta match {
      case x::xs => buildRaw(xs)(str ++ x._2)
      case Nil => str
    }

    buildRaw(parse(filename))("")
  }

  @tailrec
  private def parserInner(lines: Iterator[String])
                         (acc: String)
                         (lst: List[(String,String)])
                         (curr: String): List[(String,String)] = {
    if (lines.hasNext) {
      lines.next match {
        case s if s.matches("[ATGC]+") => parserInner(lines)(acc + s)(lst)(curr)
        case s if acc.isEmpty => parserInner(lines)(acc)(lst)(s)
        case s if acc.nonEmpty => parserInner(lines)("")((curr,acc) :: lst)(s)
      }
    }
    else ((curr,acc) :: lst).reverse
  }
}
