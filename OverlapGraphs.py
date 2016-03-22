from Bio import SeqIO

# Use biopython to parse fasta files
seqs = SeqIO.parse(open("OverlapGraphsData.txt"),'fasta')

# Build a dict of labels with each end of the given seq
# stored in a tuple as value.
ends = {e.id:(str(e.seq)[  :3],\
              str(e.seq)[-3: ]) for e in seqs}

# Take every dict entry and print matching start-ends.
# As every edge can be viewed as start-end, we are sure
# that every edge is present.
for mlabel,(mstart,mend) in ends.items():
    [print(mlabel,label) for label,(start,end) in ends.items()\
     if (mend==start) and (label!=mlabel)]
    
