import java.util.*;
import java.io.*;

public class Main {
    
    static String input;
    static int TEXT_LENGTH;

    static int[] alphabet, DP;
    static boolean[][] PALINDROME;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine();
        TEXT_LENGTH = input.length();

        alphabet = new int[TEXT_LENGTH + 1];
        for(int i = 1; i <= TEXT_LENGTH; i++) {
            alphabet[i] = input.charAt(i - 1) - 'A';
        }
    }

    static void fulfillPalindrome() {
        PALINDROME = new boolean[TEXT_LENGTH + 1][TEXT_LENGTH + 1];

        for(int i = 1; i <= TEXT_LENGTH; i++) {
            PALINDROME[i][i] = true;
        }

        for(int i = 1; i < TEXT_LENGTH; i++) {
            if(alphabet[i] == alphabet[i + 1]) PALINDROME[i][i + 1] = true;
        }

        for(int len = 3; len <= TEXT_LENGTH; len++) {
            for(int i = 1; i <= (TEXT_LENGTH - len + 1); i++) {
                int j = i - 1 + len;
                if(alphabet[i] == alphabet[j] && PALINDROME[i + 1][j - 1]) {
                    PALINDROME[i][j] = true;
                }
            }
        }
    }

    static void pro() {
        DP = new int[TEXT_LENGTH + 1];

        Arrays.fill(DP, Integer.MAX_VALUE);
        DP[0] = 0;

        for(int i = 1; i <= TEXT_LENGTH; i++) {
            for(int j = 1; j <= i; j++) {
                if(PALINDROME[j][i]) {
                    DP[i] = Math.min(DP[i], DP[j - 1] + 1);
                }
            }
        }

        System.out.println(DP[TEXT_LENGTH]);
    }

    public static void main(String[] args) throws Exception {
        input();

        fulfillPalindrome();

        pro();
    }
    
}