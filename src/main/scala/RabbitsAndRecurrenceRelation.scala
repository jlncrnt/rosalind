import scala.annotation.tailrec

object RabbitsAndRecurrenceRelation extends App {

  val gen = 35
  val litter = 2

  @tailrec
  def Fib(t1: BigInt)(t2: BigInt)(g: Int): BigInt = g match {
    case `gen` => t2
    case n => Fib(t2)(t1 * litter + t2)(n + 1)
  }

  // One grown pair is generation 2
  println(Fib(1)(1)(2))

}