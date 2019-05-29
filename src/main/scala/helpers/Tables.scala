
/**
  * Created by julien on 19.07.16.
  */

package helpers

object Tables {

  import scala.util.matching.Regex

  def DNA: Map[String, String] = codonMap

  def RNA: Map[String, String] = codonMap map {case (a,b) => (a.replace('T','U'),b) }

  private val RAWTable = """TTT F      CTT L      ATT I      GTT V
                           |TTC F      CTC L      ATC I      GTC V
                           |TTA L      CTA L      ATA I      GTA V
                           |TTG L      CTG L      ATG M      GTG V
                           |TCT S      CCT P      ACT T      GCT A
                           |TCC S      CCC P      ACC T      GCC A
                           |TCA S      CCA P      ACA T      GCA A
                           |TCG S      CCG P      ACG T      GCG A
                           |TAT Y      CAT H      AAT N      GAT D
                           |TAC Y      CAC H      AAC N      GAC D
                           |TAA Stop   CAA Q      AAA K      GAA E
                           |TAG Stop   CAG Q      AAG K      GAG E
                           |TGT C      CGT R      AGT S      GGT G
                           |TGC C      CGC R      AGC S      GGC G
                           |TGA Stop   CGA R      AGA R      GGA G
                           |TGG W      CGG R      AGG R      GGG G""".stripMargin

  // Pattern match codon + corresponding letter or "stop"
  private val pattern = new Regex("([ATGC]{3}) ([a-zA-Z]+)")

  // Find all matches of these two groups
  private val allMatches = pattern.findAllMatchIn(RAWTable.mkString)

  // Build map of Codon -> (Amino Acid | Stop)
  private val codonMap: Map[String, String] = (for (i <- allMatches) yield (i.group(1), i.group(2))).toMap

}