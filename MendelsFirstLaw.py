
# coding: utf-8

# # Mendel's First Law

# In[313]:

import numpy as np


# In[314]:

with open("Mendel's First Law Data.txt") as f:
    line = f.readline()
print(line)


# In[315]:

pop = np.fromstring(line, dtype=int, sep=' ')
pop


# In[316]:

tot = sum(pop)
tot
print(tot)
k = pop[0]
m = pop[1]
n = pop[2]
print(k,m,n)


# In[317]:

B1 = k/tot
B2 = m/tot
B3 = n/tot
B1+B2+B3


# In[318]:

B11 = ((k-1)/(tot-1)) * 1
B12 = (m/(tot-1)) * 1
B13 = (n/(tot-1)) * 1
print(B11)
print(B12)
print(B13)
print((B11+B12+B13))


# In[319]:

B21 = (k/(tot-1)) * 1
B22 = ((m-1)/(tot-1)) * (3/4)
B23 = (n/(tot-1)) * (2/4)
print(B21)
print(B22)
print(B23)
print(B21+B22+B23)


# In[320]:

B31 = (k/(tot-1)) * 1
B32 = (m/(tot-1)) * (2/4)
B33 = ((n-1)/(tot-1)) * 0
print(B31)
print(B32)
print(B33)
print(B31+B32+B33)


# In[321]:

B1 = (B11 + B12 + B13) * B1
B2 = (B21 + B22 + B23) * B2
B3 = (B31 + B32 + B33) * B3
print(B1+B2+B3)


# # Result

# In[322]:

print(B1+B2+B3)


# # StackOverflow Solution

# In[337]:

import scipy.misc

pop_total = 4*scipy.misc.comb(k+m+n,2)

hom = k
het = m
rec = n


# In[342]:

dom_total = 4*scipy.misc.comb(hom,2) + 4*hom*het + 4*hom*rec + 3*scipy.misc.comb(het,2) + 2*het*rec
print("Total of organisms with dominant allele : {0}".format(dom_total))


# In[343]:

phom = dom_total/pop_total
print("Probability of dominant : {0}".format(phom))


# In[346]:

print("Probability of recessive : {0}".format(1-phom))

