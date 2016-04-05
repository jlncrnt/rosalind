from pprint import pprint

labels = []
seqs = []

with open("OverlapGraphsData.txt") as data:
    l = data.readline().rstrip()
    if l.startswith('>'):
        labels.append(l)
    else:
        seqs.append(l)
                
dict = dict(zip(labels,seqs))

pprint(labels)
