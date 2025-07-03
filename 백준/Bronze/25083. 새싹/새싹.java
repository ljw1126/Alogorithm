import java.util.*;
import java.io.*;

public class Main {
     private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        pro();
        output();
    }

    private static void pro() throws IOException {
        sb.append("         ,r'\"7").append("\n");
        sb.append("r`-_   ,'  ,/").append("\n");
        sb.append(" \\. \". L_r'").append("\n");
        sb.append("   `~\\/").append("\n");
        sb.append("      |").append("\n");
        sb.append("      |").append("\n");
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    
}