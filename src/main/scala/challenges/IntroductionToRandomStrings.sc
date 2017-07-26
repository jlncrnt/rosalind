import scala.io.Source

val dir = System.getProperty("user.dir") + "/linkdir/"
val data_fn = dir + "data/IntroductionToRandomStrings.txt"

val lines = Source.fromFile(data_fn).getLines()

val strand = lines.next()
val GCContents = lines.next().split(' ').map(_.toFloat)

def mapOfProbFromGCContent(GC: Float): Map[Char, Float] = {
  val A,T = (1 - GC) / 2.toFloat
  val G,C =      GC  / 2.toFloat
  Map('A'->A, 'T'->T, 'G'->G, 'C'->C)
}

for (gc <- GCContents) {
  val currMap = mapOfProbFromGCContent(gc)
  val prob = strand.map(currMap).map(x=>math.log10(x)).sum
  print(f"""$prob%1.3f """)
}