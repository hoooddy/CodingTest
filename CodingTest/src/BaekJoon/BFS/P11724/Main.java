package BaekJoon.BFS.P11724;

import java.util.*;
import java.io.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[][] graph;

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];
        int u, v;
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        int connectedComponent = 0;
        boolean[] visited = new boolean[N+1];
        for(int n = 1; n < N + 1; n++) {
            if(visited[n] == true){
                continue;
            }
            connectedComponent++;
            Queue<Integer> queue = new LinkedList<>();

            visited[n] = true;
            queue.add(n);


            while(!queue.isEmpty()){
                // 1. 큐에서 꺼내온다.
                int node = queue.poll();

                // 2. 목적지인가.
                // 3. 연결된 곳 순회
                for(int i = 1; i < N + 1; i++){
                    // 4. 갈 수 있는가
                    if(graph[node][i] == 1 && visited[i] == false){
                        // 5. 체크인
                        visited[i] = true;
                        // 6. 큐에 넣는다
                        queue.add(i);
                    }
                }

                // 7. 체크아웃
            }
        }

        sb.append(connectedComponent);

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();


    }

}
