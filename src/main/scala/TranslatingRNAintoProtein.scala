import helpers.{Challenge, Tables}

import scala.annotation.tailrec

object TranslatingRNAintoProtein extends App {

  Challenge("TranslatingRNAintoProtein.txt") provides { data =>
    // Transform the RNA map (Char,Char,Char) -> Char
    val map = Tables.RNA map { case (a, b) => ((a(0), a(1), a(2)), b(0)) }

    // Take nt 3 by 3 in strand and add each map transcribe to accumulator
    // until list it empty. Return reversed string
    @tailrec
    def buildProt(s: List[Char])(acc: List[Char]): String = s match {
      case a :: b :: c :: xs => buildProt(xs)(map(a, b, c) :: acc)
      case Nil => acc.reverse.mkString
    }

    println(buildProt(data.toList)(Nil))

  }

}