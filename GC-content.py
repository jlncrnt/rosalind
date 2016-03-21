import re
from pprint import pprint

seqp = re.compile(r'[ATGC]+')
labp = re.compile(r'>(\w+_\d+)')

f = open("dataset.fasta")
rawlst = f.read().split('\n')
f.close()

lst = {}
        
for i in rawlst:
    if(labp.match(i)):
        curr = i.strip('>')
        lst[curr] = ""
    if(seqp.match(i)):
        lst[curr] = lst[curr]+i        
        
perc = {}

def GCperc(seq):
    n = sum([1 for n in seq if n==('G') or n==('C')])
    return n/len(seq) 

for label, seq in lst.items():
    perc[label] = GCperc(seq)
    
m = max(perc, key=perc.get)

print("{}\n{}".format(m,perc[m]*100))

