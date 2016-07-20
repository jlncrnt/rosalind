import re

with open("data/RNACodonsTable.txt") as codons:
    c = codons.read()

with open("data/TranslatingRNAintoProtein.txt") as str:
    seq = str.read()

# Parse codons table
m = dict(re.compile("(\w+) (\w+)").findall(c))

# Iterate 3 by 3 through seq, replacing key with corresponding values. Strip last amino acid.
prot = [m[c] for c in [seq[i:i+3] for i in range(0, len(seq), 3)] if c != '\n'][:-1]

print(''.join(prot))
