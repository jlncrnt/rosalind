case class Gene[C](g: C)

case class Allele[A](first: Gene[A], second: Gene[A])

case class Individual[A](A1: Allele[A], A2: Allele[A]) extends Traversable[A] {
  override def foreach[U](f: (A) => U) = {
    val these = this
    val yieldList = List(these.A1.first.g,
                         these.A1.second.g,
                         these.A2.first.g,
                         these.A2.second.g)
    yieldList.foreach(v => f(v))
  }
}

object Individual

// Class to find all genotypes when crossing alleles
implicit class Crossable[X](xs: Traversable[X]) {
  def breed[Y](ys: Traversable[Y]) = for { x <- xs; y <- ys } yield (x, y)
}



val ind1 = new Individual('A','a','B','b')
val ind2 = new Individual('A','A','B','B')

ind1 breed ind2