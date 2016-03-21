import re

with open("codons.txt") as codons:
    c = codons.read()

with open("RNAds.txt") as str:
    seq = str.read()

m = dict(re.compile("(\w+) (\w+)").findall(c))

prot = [m[c] for c in [seq[i:i+3] for i in range(0, len(seq), 3)] if c != '\n'][:-1]

print(''.join(prot))
