
# coding: utf-8

# # Transcribing DNA into RNA

# In[41]:

with open('Transcribing DNA into RNA Data.txt') as f:
    dnastr = f.readline()
    
print(dnastr)


# In[42]:

lst = list(dnastr)

for i,x in enumerate(lst):
    if(x=='T'): lst[i]='U'
      
rnastr = ''.join(lst)
print(rnastr)

