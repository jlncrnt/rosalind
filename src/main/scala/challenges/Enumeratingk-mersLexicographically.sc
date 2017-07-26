import scala.io.Source
import scalaz._
import Scalaz._


val dir = System.getProperty("user.dir") + "/linkdir/"
val data_fn = dir + "data/Enumeratingk-mersLexicographically.txt"

val lines = Source.fromFile(data_fn).getLines()

val alphabet = lines.next().split(' ').toList.map(_.toCharArray.head)
val alphlen   = lines.next().split(' ').map(_.toInt).head

// Actually found an incredible solution in Haskell on solutions
// forum. Here is the translation in Scala using Haskell :

alphabet replicateM alphlen foreach (x => println(x.mkString(" ")))

// Here is mine... sigh!

val order = alphabet.zipWithIndex.toMap

def sorter(s1: String, s2: String): Boolean = (s1, s2) match {
  case (a,b) if a.head == b.head => sorter(s1.tail, s2.tail)
  case (a,b) if order(a.head) > order(b.head) => false
  case (a,b) if order(a.head) < order(b.head) => true
  case _ => false
}

val words = for {
  combs <- List.fill(alphabet.length)(alphabet).flatten.combinations(alphlen)
  perms <- combs.permutations
} yield perms

// The Rosalind answer has to be sorted ALPHABETICALLY ! (seriously...)
// val ws = words.map(_.mkString).toList.sortWith(sorter)
words.map(_.mkString).toList.sorted.foreach(x => println(x.mkString(" ")))
