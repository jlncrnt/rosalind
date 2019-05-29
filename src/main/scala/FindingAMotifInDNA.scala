import helpers.Challenge

object FindingAMotifInDNA extends App {

  Challenge("FindingMotifinDNA.txt") provides { data =>

    val lines = data.getLines

    val DNA = lines.next()
    val rawSEQ = lines.next()

    /** Need to use a lookahead as it doesn't consume a whole string and permits overlapping ! */
    val SEQ = s"(${rawSEQ.head})(?=(${rawSEQ.tail}))"

    SEQ.r.findAllMatchIn(DNA).foreach(i => print(i.start + 1 + " "))

  }

}