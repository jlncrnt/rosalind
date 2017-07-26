import org.scalatest.FlatSpec

class ExamplesTests extends FlatSpec {

  "Expected offspring" should "equal 3.5 in Rosalind example" in {
    val exampleData = "1 0 0 1 0 1"
    val r = challenges.CalculatingExpectedOffspring.result(data = exampleData)
    assert(r == "3.5")
  }

  "Counting DNA Nucleotide" should "equal 20 12 17 21 in Rosalind example" in {
    val exampleData = "AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"
    val r = challenges.CountingDNANucleotides.result(data = exampleData)
    assert(r == "20 12 17 21")
  }

  "Matching random motifs" should "equal 0.689 in Rosalind example" in {
    val exampleData = "90000 0.6\nATAGCCGA"
    val r = challenges.MatchingRandomMotifs.result(exampleData)
    assert(r == "0.689")
  }

  "Perfect matchin on RNA secondariy structire" should "equal 12 in Rosalind example" in {
    val exampleData = ">Rosalind_23\nAGCUAGUCAU"
    val r = challenges.PerfectMatchingsandRNASecondaryStructures.result(exampleData)
    assert(r == "12")
  }

}
