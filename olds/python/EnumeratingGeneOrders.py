from itertools import permutations
from math import factorial

# Max number of permutations
n = 6

# Build a list of numbers 
numbers = list(range(1, n+1))

# Possible permutations
perms = permutations(numbers)

# Total number of possible permutations
print(factorial(n))

# Print result with format required
for e in perms:
    for i in e:
        print(i, end="")
    print()
