class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int son = (numer1 * denom2) + (numer2 * denom1);
        int mother = denom1 * denom2;
        
        for(int i = mother; i > 1; i--) {
            if(mother % i == 0 && son % i == 0) {
                son /= i;
                mother /= i;
            }
        }
     
        return new int[] {son, mother};
    }
    
    private int[] solve(int numer1, int denom1, int numer2, int denom2 ) {
         int value = gcd(denom1, denom2); // 최대 공약수
        int denom = (denom1 * denom2) / value; // 최소 공배수
        
        int n1 = numer1 * (denom / denom1);
        int n2 = numer2 * (denom / denom2);
        int numer = n1 + n2;
        
        int resultGcd = gcd(numer, denom); // 최대 공약수
        
        return new int[] {numer / resultGcd, denom / resultGcd};
    }
    
    private int gcd(int a, int b) {
        if(b == 0) return a;
        
        return gcd(b, a % b);
    }
}