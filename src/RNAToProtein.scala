/**
  * Created by julien on 16.07.16.
  */

package helpr

object RNAToProtein {

  import scala.annotation.tailrec
  import scala.util.matching.Regex

  def translate(str: String): String = {
    translateInner(str.toList)("")
  }

  private val table = """ UUU F      CUU L      AUU I      GUU V
                          UUC F      CUC L      AUC I      GUC V
                          UUA L      CUA L      AUA I      GUA V
                          UUG L      CUG L      AUG M      GUG V
                          UCU S      CCU P      ACU T      GCU A
                          UCC S      CCC P      ACC T      GCC A
                          UCA S      CCA P      ACA T      GCA A
                          UCG S      CCG P      ACG T      GCG A
                          UAU Y      CAU H      AAU N      GAU D
                          UAC Y      CAC H      AAC N      GAC D
                          UAA Stop   CAA Q      AAA K      GAA E
                          UAG Stop   CAG Q      AAG K      GAG E
                          UGU C      CGU R      AGU S      GGU G
                          UGC C      CGC R      AGC S      GGC G
                          UGA Stop   CGA R      AGA R      GGA G
                          UGG W      CGG R      AGG R      GGG G"""

  private val cmap: Map[String,String] = {
    val pattern = new Regex("([AUGC]{3}) ([a-zA-Z]+)")
    val allMatches = pattern.findAllMatchIn(table)
    (for (i <- allMatches) yield (i.group(1), i.group(2))).toMap
  }

  @tailrec
  def translateInner(s: List[Char])(acc: String): String = s match {
    case a::b::c::xs => translateInner(xs)(acc + cmap(List(a,b,c).mkString))
    case _ => acc
  }
}
