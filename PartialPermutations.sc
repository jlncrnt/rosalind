import scala.annotation.tailrec

val n: BigInt = 21
val k: BigInt = 7

def fact(n: BigInt): BigInt = {

  val zero = BigInt(0)

  @tailrec
  def factIter(x: BigInt)(acc: BigInt): BigInt = x match {
    case `zero` => acc
    case i => factIter(x - 1)(x * acc)
  }

  factIter(n)(1)

}

fact(n)/fact(n-k) % 1000000
