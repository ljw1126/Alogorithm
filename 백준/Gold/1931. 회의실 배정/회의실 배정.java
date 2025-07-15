import java.util.*;
import java.io.*;

public class Main {
   private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int n;
    private static List<Meeting> rooms;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        rooms = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            rooms.add(new Meeting(start, end));
        }
    }

    private static class Meeting implements Comparable<Meeting> {
        private int start;
        private int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Meeting o) {
            if(this.end != o.end) {
                return this.end - o.end;
            }

            return this.start - o.start;
        }

        public String toString() {
            return start + " " + end;
        }
    }

    private static void pro() {
        Collections.sort(rooms);

        int result = 1;
        int cur = 0;
        for(int i = 1; i < n; i++) {
            Meeting prev = rooms.get(cur);
            Meeting next = rooms.get(i);
            if(prev.end <= next.start) {
                cur = i;
                result += 1;
            }
        }

        sb.append(result);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}