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

}
