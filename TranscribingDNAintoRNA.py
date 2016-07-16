# Should have use python string replacement method here, but wasn't
# very aware of them at this time.
with open("data/TranscribingDNAintoRNA.txt") as f:
    dnastr = f.readline()
    
lst = list(dnastr)

# Taking advantage of list mutability
for i, x in enumerate(lst):
    if x == 'T':
        lst[i] = 'U'
      
rnastr = ''.join(lst)

print(rnastr)
