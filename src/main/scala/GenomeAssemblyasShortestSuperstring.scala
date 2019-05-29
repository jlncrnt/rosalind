import helpers.{Challenge, FastaParser}

import scala.util.Random

object GenomeAssemblyasShortestSuperstring extends App {

  Challenge("GenomeAssemblyasShortestSuperstring.txt") provides { data =>

    val chunks = FastaParser.parseString(data.mkString).map(_._2)

    println("Number of chunks : " + chunks.size)

    val min = chunks.head.length / 2

    def overlaps(tu: (String, String)): Option[List[String]] = {
      val (a, b) = tu
      lazy val o = a.tails.filter(_.length >= min).find(b.startsWith)
      lazy val p = b.tails.filter(_.length >= min).find(a.startsWith)
      if (o.isDefined) {
        println("O is defined")
        Some(List(a.dropRight(o.get.length) + b))
      }
      else if (p.isDefined) {
        println("P is defined")
        Some(List(b.dropRight(p.get.length) + a))
      }
      else Some(List(a, b))
    }

    @scala.annotation.tailrec
    def loop(chks: List[String]): String = {
      val chunks = Random.shuffle(chks)
      //println(chunks)
      if (chunks.length < 2) chunks.head else {
        //println("Getting combs")
        val combs0 = if (chunks.length % 2 == 1) chunks.last :: chunks else chunks
        println("Chunks left : " + combs0.length)
        val combs = {
          combs0.sliding(2, 2) map { case List(a, b) => (a, b) }
        }.toList
        //println("Loop with combs size : " + combs.size)
        //println(combs)

        loop(combs.par.flatMap(overlaps).toList.flatten)
      }
    }

    println(loop(chunks))

  }

}
