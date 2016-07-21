val maxPerm = 3
val range = 1 to maxPerm

range.product
range.permutations.foreach(v => println(v.mkString(" ")))

