package challenges

object CompletingaTree extends Challenge("CompletingaTree.txt") {

  val numbers = data.split("\\n|\\s").toList
  val n = numbers.head.toInt
  val edges = numbers
    .tail
    .sliding(2,2)
    .map{
      case List(a,b) => (a.toInt,b.toInt)
    }.toMap

  @scala.annotation.tailrec
  def loop(node: Int)(map: Map[Int,Int])(conn: Int): Int = {
    if(map.isEmpty) {
      println("Is now empty")
      conn + 1
    }
    else if(map.contains(node)) {
      println("In group : " + node)
      println(map)
      loop(map(node))(map - node)(conn)
    }
    else if(map.values.exists(_ == node)) {
      print("A value exists for " + node)
      val (k,v) = map.filter{ case (a,b) => b == node }.head
      println(" and its key is : " + k)
      println(map)
      loop(map(k))(map - k)(conn)
    }
    else {
      println("End of group : " + node)
      println(map)
      loop(map.head._1)(map)(conn + 1)
    }
  }

  def result = println(loop(edges.head._1)(edges)(0))
}
