object EnumeratingGeneOrders extends App {

  val maxPerm = 3
  val range = 1 to maxPerm

  // Factorial
  range.product

  // Print permutations
  range.permutations.foreach(v => println(v.mkString(" ")))

}