import helpr.FastaParser
import scala.annotation.tailrec

val dir = System.getProperty("user.dir") + "/linkdir/"
val fn = dir + "data/TansitionsAndTransversions.fasta"

val lst = FastaParser.parseDNA(fn).map(_._2)

val transition    = Map('A'->'G', 'C'->'T', 'G'->'A', 'T'->'C')
val transversion1 = Map('A'->'C', 'G'->'T', 'C'->'A', 'T'->'G')
val transversion2 = Map('A'->'T', 'G'->'C', 'C'->'G', 'T'->'A')

@tailrec
def ratio(lst: List[(Char, Char)])(transi: Int, transv: Int): Float = lst match {
  case x::xs =>
    if      (x._1 == transition  (x._2))  ratio(xs)(transi+1  , transv  )
    else if (x._1 == transversion1(x._2) ||
             x._1 == transversion2(x._2)) ratio(xs)(transi    , transv+1)
    else    ratio(xs)(transi, transv)

  case Nil => transi.toFloat / transv.toFloat
}

val res = ratio(lst.head zip lst.last toList)(0,0)
