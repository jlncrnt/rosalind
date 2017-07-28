package challenges

import helpers.FastaParser

import scala.util.Random

object GenomeAssemblyasShortestSuperstring extends Challenge("GenomeAssemblyasShortestSuperstring.txt") {

  val chunks = FastaParser.parseString(data).map(_._2)

  println("Number of chunks : " + chunks.size)

  val min = chunks.head.size / 2

  var i = 0

  private def overlaps(tu: (String,String)): Option[List[String]] = {
    val (a,b) = tu
    lazy val o = a.tails.filter(_.length >= min).find(b.startsWith)
    lazy val p = b.tails.filter(_.length >= min).find(a.startsWith)
    if      (o.isDefined) {println("O is defined"); Some(List(a.dropRight(o.get.length) + b))}
    else if (p.isDefined) {println("P is defined"); Some(List(b.dropRight(p.get.length) + a))}
    else    Some(List(a,b))
  }

  @scala.annotation.tailrec
  def loop(chks: List[String]): String = {
    val chunks = Random.shuffle(chks)
    //println(chunks)
    if(chunks.length < 2) chunks.head else {
      //println("Getting combs")
      var combs0 = if(chunks.length % 2 == 1) chunks.last :: chunks else chunks
      println("Chunks left : " + combs0.length)
      val combs = {combs0.sliding(2,2) map { case List(a, b) => (a, b) }}.toList
      //println("Loop with combs size : " + combs.size)
      //println(combs)

      loop(combs.par.flatMap(overlaps).toList.flatten)
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