#include <stdio.h>

const static char n =35;
const static char k = 2;

long long rabbit(m,g) {
    int i=0;
    long long a=0;
    if(g>=n){
        return 1;
    }
    else if(m>0){
        a = rabbit(m+1,g+1);
        for(i=0;i<k;i++){
            a = a + rabbit(0,g+1);
        }
        return a;
    }
    else{
        return rabbit(m+1,g+1);
    }
}

int main(int argc, char ** argv) {
    long long tot=0;
    tot = rabbit(0,1);
    printf("%llu\n",tot);
}
