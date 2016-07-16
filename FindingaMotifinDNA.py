# Use special regex module, allowing overlapping matches
import regex

# store string and pattern 
with open("data/FindingMotifinDNA.txt") as data:
    s = data.readline().rstrip()
    p = data.readline().rstrip()

# Compile provided pattern 
p = regex.compile(p)

# Build list from regex iterator, allowing overlapping matches
m = list(p.finditer(s, overlapped=True))

# Output format as required
for e in m:
    print(e.start()+1, end=' ')
print("")
