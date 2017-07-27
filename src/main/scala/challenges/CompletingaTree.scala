package challenges

object CompletingaTree extends Challenge("CompletingaTree.txt") {

  val lines = data.split("\n").toList

  def result = println(lines.head.toInt - lines.tail.size - 1)
}
