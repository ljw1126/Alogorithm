import java.util.*;
import java.io.*;

public class Main {
    
     private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    private static int n;
    private static List<Person> persons;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t =  Integer.parseInt(br.readLine());
        while(t > 0) {
            t -= 1;
            n = Integer.parseInt(br.readLine());

            persons = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                persons.add(new Person(a, b));
            }

            Collections.sort(persons);

            pro();
        }
    }

    private static class Person implements Comparable<Person> {
        private int a;
        private int b;

        public Person(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int compareTo(Person o) {
            if(this.a != o.a) {
                return this.a - o.a;
            }

            return this.b - o.b;
        }
    }


    private static void pro() {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        int result = 0;
        for(int i = 0; i < n; i++) {
            Person person = persons.get(i);
            int a = person.a;
            int b = person.b;

            if(a < x || b < y) {
                if(a < x) x = a;
                if(b < y) y = b;

                result += 1;
            }
        }

        sb.append(result).append("\n");
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
}