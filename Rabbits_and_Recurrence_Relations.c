/*
    I had to do it in C as a use recursive function. It is
    slow in python and take a huge amount of memory...
*/

#include <stdio.h>

// Using const static to avoid passing args to 
// recursive functions and gain time in changing context

// Number of generations
const static char n =35;

// Number of offsping at each Generation
const static char k = 2;

// The number is huge
// m = grown up of not
// g is actual generation
long long rabbit(m,g) {
    int i=0;
    long long a=0;
    
    // If this rabbit is from last generation, return
    if(g>=n){
        return 1;
    }
    
    // If rabbit is adult, 
    else if(m>0){
        // ...continue with this rabbit...
        a = rabbit(m+1,g+1);
        // But also call k new baby rabbit
        // and add them child (or leaves)
        for(i=0;i<k;i++){
            a = a + rabbit(0,g+1);
        }
        // Return leaves count
        return a;
    }
    // If rabbit is not an adult, make it adult for next generation
    else{
        return rabbit(m+1,g+1);
    }
}

// Build tree
int main(int argc, char ** argv) {
    long long tot=0;
    
    // Pop first rabbit and count leaves
    tot = rabbit(0,1);
    
    printf("%llu\n",tot);
}
