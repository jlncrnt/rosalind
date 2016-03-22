from itertools import permutations
from math import factorial

n = 6

numbers = list(range(1,n+1))

perms = permutations(numbers)

print(factorial(n))

for e in perms:
    for i in e:
        print(i,end=' ')
    print()
