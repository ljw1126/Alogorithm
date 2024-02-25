import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();

    static int [] LIMIT = new int[3];
    static boolean[][][] VISIT = new boolean[201][201][201];

    static List<Integer> RESULT = new ArrayList<>();
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < 3; i++) {
            LIMIT[i] = Integer.parseInt(st.nextToken());
        }
    }

    static class State {
        public int[] liquid;

        public State(int[] _liquid) {
            liquid = new int[3];
            for(int i = 0; i < 3; i++) {
                liquid[i] = _liquid[i];
            }
        }

        public State moveTo(int from, int to, int[] _limit) {
            int[] _liquid = new int[]{liquid[0], liquid[1], liquid[2]};

            if(_liquid[from] + _liquid[to] >= _limit[to]) {
                _liquid[from] -= (_limit[to] - _liquid[to]);
                _liquid[to] = _limit[to];
            } else {
                _liquid[to] += _liquid[from];
                _liquid[from] = 0;
            }

            return new State(_liquid);
        }
    }

    static void dfs(State state) {
        VISIT[state.liquid[0]][state.liquid[1]][state.liquid[2]] = true;
        if(state.liquid[0] == 0) RESULT.add(state.liquid[2]);

        for(int from = 0; from < 3; from++) {
            for(int to = 0; to < 3; to++) {
                if(from == to) continue;

                State next = state.moveTo(from, to, LIMIT);
                if(VISIT[next.liquid[0]][next.liquid[1]][next.liquid[2]]) continue;

                dfs(next);
            }
        }
    }

    static void pro() {
        dfs(new State(new int[]{0, 0, LIMIT[2]}));

        Collections.sort(RESULT);
        for(int n : RESULT) sb.append(n).append(" ");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }


}
