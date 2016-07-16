import scala.annotation.tailrec

val n: Int = 21
val k: Int = 7

// First solution
val intArray = (1 to n).toArray.map(x => BigInt(x))
intArray.product / intArray.slice(1, n-k).product % 1000000

// Second solution
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
