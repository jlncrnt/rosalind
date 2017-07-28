package challenges

import helpers.FastaParser

object GenomeAssemblyasShortestSuperstring extends Challenge("GenomeAssemblyasShortestSuperstring.txt") {

  val chunks = FastaParser.parseString(data).map(_._2)

  private def overlaps(tu: (String,String)): Option[String] = {
    val (a,b) = tu
    lazy val o = a.tails.filter(_.length >= a.length/2).find(b.startsWith)
    lazy val p = b.tails.filter(_.length >= b.length/2).find(a.startsWith)
    if      (o.isDefined) Some(a.dropRight(o.get.length) + b)
    else if (p.isDefined) Some(b.dropRight(p.get.length) + a)
    else    None
  }

  @scala.annotation.tailrec
  def loop(chunks: List[String]): String = {
    if(chunks.length < 2) chunks.head else {
      println("Getting combs")
      val combs = chunks.combinations(2) map { case List(a, b) => (a, b) }
      println("Loop")
      loop(combs.toList.par.flatMap(overlaps).toList)
    }
  }

  def result = loop(chunks)
}

/*
    val circle = ("N" * (s1.length / 2)) + s2 + ("N" * (s1.length / 2))
    val rotationsS2 = for (i <- 0 to circle.length/2) yield {
      val h = circle.take(i); circle.drop(i) ++ h
    }

    def matc(t: String): Boolean = {
      (s1 zip t).forall(t => (t._1 == 'N' || t._2 == 'N') || t._1 == t._2)
    }

    val r = rotationsS2.find(matc)

    if (r.isEmpty) None else {
      val rt = r.get
      if(rt.endsWith("N")) {

      }
    }

 */