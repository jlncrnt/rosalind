import scala.annotation.tailrec

object FindingASplicedMotif extends App {

  val seq = "AGGCTGACTACAGTGGGTCGGCGATCTTCGGG"
  val subseq = "GGTAGGCCAG"

  @tailrec
  def indices(seq: List[Char])(subseq: List[Char])(idcs: List[Int])(i: Int): List[Int] = subseq match {
    case x :: _ if x == seq.head => indices(seq.tail)(subseq.tail)(i :: idcs)(i + 1)
    case _ :: _ => indices(seq.tail)(subseq)(idcs)(i + 1)
    case _ => idcs.reverse
  }

  println(indices(seq.toList)(subseq.toList)(List[Int]())(1))

}