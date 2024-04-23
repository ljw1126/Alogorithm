import java.util.*;
import java.io.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int T, N, M;

    static int[] A, B;

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[N];
            B = new int[M];

            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                A[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int k = 0; k < M; k++) {
                B[k] = Integer.parseInt(st.nextToken());
            }

            pro();
        }
    }

    static int binarySearch(int[] arr, int target, int left, int right) {
        while(left < right) {
            int mid = (left + right) / 2;
            if(arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    static void pro() {
        Arrays.sort(B);

        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum += binarySearch(B, A[i], 0, B.length);
        }

        sb.append(sum).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();

        System.out.println(sb.toString());
    }
}
