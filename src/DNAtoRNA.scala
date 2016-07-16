/**
  * Created by julien on 16.07.16.
  */
/**
  * Created by julien on 16.07.16.
  */

package helpr

object DNAtoRNA {

  import scala.annotation.tailrec

  def transcribe(str: String): String = {
    transcribeInner(str.toList)("")
  }

  @tailrec
  def transcribeInner(s: List[Char])(acc: String): String = s match {
    case x::xs if x == 'T' => transcribeInner(xs)(acc + 'U')
    case x::xs => transcribeInner(xs)(acc + x)
    case _ => acc
  }
}
