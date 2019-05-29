import helpers.Challenge

object OrderingStringsofVaryingLengthLexicographically extends App {

  Challenge("OrderingStringsofVaryingLengthLexicographically.txt") provides { data =>

    val data = "D N A\n3".split("\\n|\\s").toList
    val (alphabet, n) = (data.init, data.last.toInt)

    def loop(p: String)(d: Int): Unit = if (d >= 0) {
      println(p)
      alphabet.foreach(s => loop(p ++ s)(d - 1))
    }

    println(loop("")(n))

  }

}