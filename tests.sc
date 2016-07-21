implicit class Crossable[X](xs: Traversable[X]) {
  def cross[Y](ys: Traversable[Y]) = for { x <- xs; y <- ys } yield (x, y)
}

val xs = Seq('A','A')
val ys = List("hello", "world", "bye")

xs cross ys