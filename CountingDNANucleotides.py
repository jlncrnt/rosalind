# Open and read file
with open('data/CountingDNANucleotides.txt') as f:
    dnastr = f.readline()

A = 0
T = 0
G = 0
C = 0

# Naive string elements counting
for symb in dnastr:
    if   symb == 'A': A += 1
    elif symb == 'T': T += 1
    elif symb == 'G': G += 1
    elif symb == 'C': C += 1

print(A, C, G, T)
