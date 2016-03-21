import regex
from pprint import pprint

with open("FindingaMotifinDNAData.txt") as data:
    s = data.readline().rstrip()
    p = data.readline().rstrip()

p = regex.compile(p)
m = list(p.finditer(s, overlapped=True))

for e in m:
    print(e.start()+1,end=' ')

print()
