from Bio import SeqIO

seqs = SeqIO.parse(open("OverlapGraphsData.txt"),'fasta')

ends = {e.id:(str(e.seq)[  :3],\
              str(e.seq)[-3: ]) for e in seqs}

for mlabel,(mstart,mend) in ends.items():
    [print(mlabel,label) for label,(start,end) in ends.items()\
     if (mend==start) and (label!=mlabel)]
    
