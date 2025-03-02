package Tmp.Yet.P4161;

import java.util.*;
import java.io.*;
// 아직
// 메모리 초과
public class Main {

    static StringBuilder sb = new StringBuilder();
    static int ty, tx;
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    static Set<String> visited;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());

            String tmp = st.nextToken();
            if(tmp.contains("END")) {
                break;
            }
            tx = Integer.parseInt(tmp);
            ty = Integer.parseInt(st.nextToken());
            visited = new HashSet<>();
            BFS();

            sb.append(answer + "\n");

        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void BFS() {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0, 0});
        visited.add("0,0");

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
//            int cx = current[0];
//            int cy = current[1];
//            int move = current[2];


            for(int i  = 0; i < 8; i++) {
                int mx = current[0] + dx[i];
                int my = current[1] + dy[i];
                int move = current[2] + 1;

                if(visited.contains(mx+","+my)) {
                    continue;
                }

                if(mx == tx && my == ty) {
                    answer = move;
                    return;
                }

                queue.add(new int[]{mx, my, move});
                visited.add(mx+","+my);
            }
        }
    }
}
