import re
from collections import Counter
 
# List of amino acids from codons file
with open("data/RNACodonsTable.txt") as codons:
    prots = re.compile("\w{3} (\w+)").findall(codons.read())

# Count ocurrence in dict
ocurrences = Counter(prots)

# Build integer generator over number of codon possibilities for each amino acid
with open("data/InferringmRNAfromProteinData.txt") as data:
    intlist = (ocurrences[aa] for aa in data.readline().rstrip())

# Starting with stop codons possibilities
total = 3

# Calculate the total possibilities modulo 1M
for i in intlist:
    total = (total * i) % 1000000

print(total)
