# Use re to find DNA sequences in file
import re

# Sequence pattern
seqp = re.compile(r'[ATGC]+')

# Read info pattern
labp = re.compile(r'>(\w+_\d+)')

with open("data/GC-content.fasta") as f:
    rawlst = f.read().split('\n')

# Key : read info line
# Value : Sequence
lst = {}

# Build dict from single line read title
# and multiline read sequence
for i in rawlst:
    if labp.match(i) :
        curr = i.strip('>')
        lst[curr] = ""
    if seqp.match(i):
        lst[curr] += i
        
# Dict with GC-content
# Key = label
# Value = GC-Content
perc = {}

def GCperc(seq):
    """ Calculate GC-Content for a given seq.
    Takes a sequence as string and return ratio of G + C
    """
    n = sum([1 for n in seq if n == ('G') or n == ('C')])
    return n/len(seq) 

# Could have used a map function here...
for label, seq in lst.items():
    perc[label] = GCperc(seq)

# Retrieve label where value is max
m = max(perc, key=perc.get)

# Print formatted as required by challenge
print("{}\n{}".format(m, perc[m]*100))

