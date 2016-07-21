import scala.annotation.tailrec
import scala.io.Source
import scala.util.matching.Regex

val dir = System.getProperty("user.dir") + "/linkdir/"
val data_fn = dir + "data/OpenReadingFrames.fasta"
val codons_fn = dir + "data/DNACodonsTable.txt"
val data_fh = Source.fromFile(data_fn)
val codons_fh = Source.fromFile(codons_fn)

// Pattern match codon + corresponding letter or "stop"
val pattern = new Regex("([ATGC]{3}) ([a-zA-Z]+)")

// Find all matches of these two groups
val allMatches = pattern.findAllMatchIn(codons_fh.mkString)

// Build map of Codon -> (Amino Acid | Stop)
val codonMap = (for (i <- allMatches) yield (i.group(1), i.group(2))).toMap

val startcodon = ('A','T','G')

// Generator over DNA string in file
val glist = for(
  line <- data_fh.getLines()
  if line.matches("[ATGC]+")
) yield line

// This class instantiated with a string provides a method find
// that return a list of all protein in it
case class ProteinFinder(str: String) {

  private val compl = Map('A'->'T','G'->'C','C'->'G','T'->'A')

  // First find all Methionine. Need String as a list of char
  private def findMet(L : List[Char]): List[String] = L match {
    // If >4 nt left and thoses match a start codon, call protein builder from here
    case a::b::c::tail if (a,b,c) == startcodon => buildProteinFrom(L)("") match {
      // If protein successfully build
      case Some(i) => i :: findMet(b::c::tail)
      // Else slide window of 1 nt
      case None => findMet(b::c::tail)
    }
    // If no met found here, slide window of 1 nt
    case a::b::c::tail => {
      findMet(b::c::tail)
    }
    // Return empty list of string to concatenate
    case _ => List[String]()
  }

  // Tail recursively build protein. Take DNA char list from where it's called in findMet
  // and accumulate protein string in prot argument.
  @tailrec
  private def buildProteinFrom(lst: List[Char])(prot: String): Option[String] = lst match {
    // If codon is stop, return prot
    case a::b::c::tail if codonMap(List(a,b,c).mkString) == "Stop" => Some(prot)
    // If not stop, call recursively on next 3 codons and concatenate corresponding Amino Acid.
    case a::b::c::tail => buildProteinFrom(tail)(prot + codonMap(List(a,b,c).mkString))
    // Return None as return type is Option
    case _ => None
  }

  // Concatenate list of prot in 5' and 3' directions and remove doublons
  def find() = (findMet(str.toList) ::: findMet(str.map(x => compl(x)).reverse.toList)).distinct
}

// Instanciate a protein finder for each DNA string and call find() method on each of them
val resList = glist.map(ProteinFinder).map(x => x.find())

resList.flatten.foreach(println)