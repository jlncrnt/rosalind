from Bio import SeqIO

# Generator over seqs in file provided
seqs = SeqIO.parse(open("data/OverlapGraphs.fasta"), 'fasta')

# Dict of (seqID,(seqSTART,seqEND)) foreach seq
ends = {e.id:(str(e.seq)[  :3],
              str(e.seq)[-3: ]) for e in seqs}

# For all prepared seqs, check all other prepared seq.
# if end of outer loop label match start of inner loop label,
# and those are not from the same seq, print the label tuple.
for mlabel, (mstart, mend) in ends.items():
    [print(mlabel, label) for label, (start, end) in ends.items()
        if (mend == start) and (label != mlabel)]
