import scala.io.Source

val values = Source.fromFile("data.txt").mkString.split(" ").map(_.toInt)

// Class to find all genotypes when crossing alleles
implicit class Crossable[X](xs: Traversable[X]) {
  def breed[Y](ys: Traversable[Y]) = for { x <- xs; y <- ys } yield (x, y)
}

// Probability for a couple to have a dominant allele for one offspring
def prob(couple: List[String]): Float = {
  // Split alleles for each parent
  val male = couple.head toList
  val female = couple.last toList

  // Find all possible offsprings
  val childs = male breed female

  // Count offsprings with a dominant allele and divide
  // divide by total number of offspring
  childs.count(t => t._1 == 'A' || t._2 == 'A') / 4.toFloat
}

// All possible alleles for an individual
val alleles = List("AA", "Aa", "aa")

// All possible couples (a.k.a combinations with replacement)
val possible_breedings = (alleles ++ alleles).combinations(2).toList

// Find prob of dominant for all couples and multiply by 2 offspring
val probs = possible_breedings.map(prob).map(_ * 2)

// Multiply each pop by its prob and sum it all
val res = probs zip values map { case (a,b) => a * b } sum
