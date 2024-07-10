class Solution {
    public String solution(String polynomial) {
        String[] tokens = polynomial.split("\\+");
        
        int v1 = 0;
        int v2 = 0;
        for(int i = 0; i < tokens.length; i++) {
            String token = tokens[i].trim();
            
            if(token.matches(".*x")) {
                token = token.replace("x", "");
                v1 += "".equals(token) ? 1 : toInt(token);
                continue;
            }
            
            v2 += toInt(token);
        }
        
        return convertX(v1) + convertNumeric(v1, v2);
    }
    
    private int toInt(String v) {
        return Integer.parseInt(v);
    }
    
    private String convertX(int v) {
        if(v == 0) return "";
        
        return v == 1 ? "x" : v + "x";
    }
 
    private String convertNumeric(int v1, int v2) {
        if(v2 == 0) return "";
        
        return v1 == 0 ? v2 + "" : " + " + v2;
    }
}