import re
from collections import Counter
 
# List of amino acids from codons file
with open("CodonsTable.txt") as codons:
    prots = re.compile("\w{3} (\w+)").findall(codons.read())

# Count ocurrence in dict
ocurrences = Counter(prots)

# Build generator over number of codon possibilities for each amino acid
with open("InferringmRNAfromProteinData.txt") as data:
    intlist = (ocurrences[aa] for aa in data.readline().rstrip())

# Stop codons possibilities
total = 3

# Calculate the total possibilities modulo 1M
for i in intlist:
    total = (total * i) % 1000000

# Multiply by stop codons possibilities
print(total)
