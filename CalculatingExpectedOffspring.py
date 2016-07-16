from itertools import combinations_with_replacement
from itertools import product
import numpy as np

FILE = "data/CalculatingExpectedOffspring.txt"

with open(FILE) as f:
    # Format of file : <int> x6
    pops = f.readline().split()

# Possible genotypes
genotypes = ('AA', 'Aa', 'aa')

# All possible pairings
pp = combinations_with_replacement(genotypes,2)

# Make sure its sorted like the problem data file
pp = sorted(pp)


def exp_dom_off(couple, n=2):
    """ Return the expected number of dominant offsprings 
    of an allele couple as a tuple ('Xx','Xx') and number
    of offspring(s) n (default 2).
    """
    # Convert tuple in list of lists
    pool = (list(e) for e in couple)
    
    # Pass 2 iterables to product() func by unpacking the pool
    pool = [e for e in product(*pool)]

    # Count all offspring having a dominant allele
    count = sum(1 for e in pool if 'A' in e)
    
    # Dominant offspring expectation
    return (count/len(pool)) * n

# Map expectation function to possible pairings
tots = list(map(exp_dom_off, pp))

# Use numpy to provide easy vectors operations
tots = np.array(tots, dtype='float64')
pops = np.array(pops, dtype='float64')

# Matrix dot function multiply terms and add them
res = np.dot(tots, pops)

print(res)
