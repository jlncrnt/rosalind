import helpers.Challenge

object CompletingaTree extends App {

  Challenge("CompletingaTree.txt") provides { data =>

    val lines = data.mkString.split("\n").toList

    println(lines.head.toInt - lines.tail.size - 1)

  }

}