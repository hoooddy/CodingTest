package BOJ.DFS.P1260;

import java.util.*;
import java.io.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    // N: 정점의 개수, M: 간선의 개수, V: 탐색 시작 정점 번호
    static int N, M, V;
    static int[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];

        int u;
        int v;
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        boolean[] visited = new boolean[N+1];
        DFS(V, visited);

        sb.append("\n");
        BFS(V);

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void DFS(int v, boolean[] visited){
        // 1. 체크인

        visited[v] = true;
        sb.append(v + " ");

        // 2. 목적지인가
        // 3. 연결된 곳 순회
        for(int i = 1; i < N + 1; i++) {
            if(graph[v][i] == 1){
                // 4. 갈 수 있는가
                if(visited[i] == false) {
                    // 5. 간다
                    DFS(i, visited);
                }
            }

            // 6. 체크아웃
        }
    }
    static void BFS(int v){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];

        visited[v] = true;
        queue.add(v);
        sb.append(v + " ");

        int u;
        while(!queue.isEmpty()){
            // 1. 큐에서 가져온다.
            u = queue.poll();

            // 2. 목적지인가
            // 3. 연결된 곳 순회
            for(int i = 1; i < N + 1; i++) {
                if(graph[u][i] == 1) {
                    // 4. 갈 수 있는가
                    if(visited[i] == false) {
                        // 5. 체크인
                        visited[i] = true;

                        // 6. 큐에 넣는다.
                        queue.add(i);
                        sb.append(i + " ");
                    }
                }
            }
            // 7. 체크아웃
        }
    }
}


//public class Main {
//
//    // N: 정점 개수
//    // M: 간선 개수
//    // V: 탐색을 시작할 정점 번호
//    static int N, M, V;
//    static int[][] graph;
//    static StringBuilder answer = new StringBuilder();
//    static boolean[] visited;
//
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        V = Integer.parseInt(st.nextToken());
//
//
//        graph = new int[N+1][N+1];
//
//        int u, v;
//        for(int i = 0; i < M; i++) {
//            st = new StringTokenizer(br.readLine());
//            u = Integer.parseInt(st.nextToken());
//            v = Integer.parseInt(st.nextToken());
//
//            graph[u][v] = 1;
//            graph[v][u] = 1;
//        }
//
//        visited = new boolean[N + 1];
//        dfs(V);
//
//        answer.append("\n");
//
//        visited = new boolean[N + 1];
//        bfs(V);
//
//        br.close();
//        bw.write(answer.toString());
//        bw.flush();
//        bw.close();
//    }
//
//
//    static void dfs(int node) {
//        // 1. 체크인
//        visited[node] = true;
//        answer.append(node + " ");
//
//        // 2. 목적지 인가
//        // 3. 연결된 곳 순회
//        for (int v = 1; v < N + 1; v++){
//            // 4. 갈 수 있는가
//            if(graph[node][v] == 1 && !visited[v]) {
//                // 5. 간다.
//                dfs(v);
//            }
//        }
//        // 6. 체크아웃
//    }
//
//    static void bfs(int node) {
//        Queue<Integer> queue = new LinkedList<>();
//
//        queue.add(node);
//        answer.append(node + " ");
//        visited[node] = true;
//
//        while(!queue.isEmpty()) {
//            // 1. 큐에서 가져온다
//            node = queue.poll();
//
//            // 2. 목적지 인가
//            // 3. 연결된 곳 순회
//            for (int v = 1; v < N + 1; v++) {
//                // 4. 갈 수 있는가
//                if(graph[node][v] == 1 && !visited[v]) {
//                    // 5. 체크인
//                    visited[v] = true;
//                    // 6. 큐에 넣는다.
//                    queue.add(v);
//                    answer.append(v + " ");
//                }
//            }
//            // 7. 체크아웃
//        }
//    }
//}

