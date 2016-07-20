from Bio import SeqIO

# Generator over seqs in file provided
seqs = SeqIO.parse(open("data/LocatingRestrictionSites.fasta"), 'fasta')

seq = next(seqs).seq

# Dict of nt complements
compl = {'A': 'T', 'G': 'C', 'T': 'A', 'C': 'G'}

def revcompl(s):
    """Return the reverse complement of a given string"""
    return ''.join([compl[c] for c in s[::-1]])

all = []

# For each demanded pattern length (4-12)
# (!) need 12+1 to include 12-nt patterns
# Only even numbers
for j in range(4, 12+1, 2):
    # Go through string, stopping at last
    # pos the current pattern can fit
    for i in range(len(seq)-j+1):
        # Extract testing pattern in string
        s = seq[i:i+j]
        # If reverse complement is equal to
        # testing pattern, it's a palindrome
        if s == revcompl(s):
            # Store position and length
            all.append((i+1, len(s)))

# Sort them, although it's not necessary.
# Easier to compare to real solution when testing.
all = sorted(all)

# Print with required format
for e in all:
    print("{} {}".format(e[0], e[1]))
