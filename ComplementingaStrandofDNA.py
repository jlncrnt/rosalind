# Read data
with open('data/ComplementingaStrandofDNA.txt') as f:
    dnastr = f.readline()

# Reverse string
rtsand = dnastr[::-1]

# String to list
lst = list(rtsand)

# Naively numerate and replace in same list
for i, x in enumerate(lst):
    if   x == 'A': lst[i] = 'T'
    elif x == 'C': lst[i] = 'G'
    elif x == 'G': lst[i] = 'C'
    elif x == 'T': lst[i] = 'A'
    
# Array of char to string
compl = ''.join(lst)

print(compl)
