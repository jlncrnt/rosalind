import org.apache.commons.math3.distribution.BinomialDistribution

val (k, n) = (7, 34)
val p = 0.75
val trials = Math.pow(2,k).toInt

val pdf = new BinomialDistribution(trials, p)
val cdf = pdf.cumulativeProbability(trials - n)

