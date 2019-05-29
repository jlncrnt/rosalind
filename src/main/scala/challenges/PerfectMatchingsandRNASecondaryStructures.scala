package challenges

object PerfectMatchingsandRNASecondaryStructures extends Challenge("PerfectMatchingsandRNASecondaryStructures.txt"){

  @scala.annotation.tailrec
  def fact(n:BigInt,acc: BigInt = 1): BigInt = if(n==0) acc else fact(n-1,n*acc)

  def result: BigInt = {
    val Array(_,dna) = data.split('\n')
    val A = dna.count(_ == 'A')
    val G = dna.count(_ == 'G')
    fact(A) * fact(G)
  }

}
