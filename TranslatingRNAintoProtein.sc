import scala.annotation.tailrec
import scala.io.Source

val map = Tables.RNA map { case (a,b) => ((a(0),a(1),a(2)), b(0)) }

val strand = Source.fromFile("data.txt").mkString

@tailrec
def buildProt(s: List[Char])(acc: List[Char]): String = s match {
  case a::b::c::xs => buildProt(xs)(map(a,b,c) :: acc)
  case Nil => acc.reverse.mkString
}

buildProt(strand.toList)(Nil)