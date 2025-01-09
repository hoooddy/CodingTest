package BaekJoon.BFS.P1260;

import java.io.*;
import java.util.*;

public class Main {

    // N: 정점 개수
    // M: 간선 개수
    // V: 탐색을 시작할 정점 번호
    static int N, M, V;
    static int[][] graph;
    static StringBuilder answer = new StringBuilder();
    static boolean[] visited;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());


        graph = new int[N+1][N+1];

        int u, v;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        visited = new boolean[N + 1];
        dfs(V);

        answer.append("\n");

        visited = new boolean[N + 1];
        bfs(V);

        br.close();
        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }


    static void dfs(int node) {
        // 1. 체크인
        visited[node] = true;
        answer.append(node + " ");

        // 2. 목적지 인가
        // 3. 연결된 곳 순회
        for (int v = 1; v < N + 1; v++){
            // 4. 갈 수 있는가
            if(graph[node][v] == 1 && !visited[v]) {
                // 5. 간다.
                dfs(v);
            }
        }
        // 6. 체크아웃
    }

    static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(node);
        answer.append(node + " ");
        visited[node] = true;

        while(!queue.isEmpty()) {
            // 1. 큐에서 가져온다
            node = queue.poll();

            // 2. 목적지 인가
            // 3. 연결된 곳 순회
            for (int v = 1; v < N + 1; v++) {
                // 4. 갈 수 있는가
                if(graph[node][v] == 1 && !visited[v]) {
                    // 5. 체크인
                    visited[v] = true;
                    // 6. 큐에 넣는다.
                    queue.add(v);
                    answer.append(v + " ");
                }
            }
            // 7. 체크아웃
        }
    }
}

//public class Main {
//
//    static int N, M, V;
//    static Map<Integer, ArrayList<Integer>> graph;
//    static boolean[] visited;
//    static StringBuilder answer;
//
//
////
////    public static void main(String[] args){
////
////    }
////
//
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
//        graph = new HashMap<>();
//
//        // 1부터 N까지의 모든 정점을 그래프에 추가
//        for (int i = 1; i <= N; i++) {
//            graph.put(i, new ArrayList<>());
//        }
//
//        int key;
//        int value;
//        for (int i = 0; i < M; i++) {
//            st = new StringTokenizer(br.readLine());
//
//            key = Integer.parseInt(st.nextToken());
//            value = Integer.parseInt(st.nextToken());
//            if(graph.get(key) == null) {
//                graph.put(key, new ArrayList<>());
//            }
//            if(graph.get(value) == null) {
//                graph.put(value, new ArrayList<>());
//            }
//
//            if(!graph.get(key).contains(value)){
//                graph.get(key).add(value);
//            }
//            if(!graph.get(value).contains(key)){
//                graph.get(value).add(key);
//            }
//        }
//
//        // 정렬
//        for (Integer graphKey : graph.keySet()) {
//            Collections.sort(graph.get(graphKey));
//        }
//
//        answer = new StringBuilder();
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
//
//    }
//
//    static void dfs(int key) {
//        // 1. 체크인
//        visited[key] = true;
//        answer.append(key+" ");
//
//        // 2. 목적지 인가
//        // 3. 연결된 곳 순회
//        for (int i = 0; i < graph.get(key).size(); i++){
//            // 4. 갈 수 있는가
//            if(!visited[graph.get(key).get(i)]){
//                // 5. 간다
//                dfs((int) graph.get(key).get(i));
//            }
//        }
//
//        // 6. 체크 아웃
//
//    }
//    static void bfs(int key) {
//        Queue<Integer> queue = new LinkedList<>();
//
//        visited[key] = true;
//        answer.append(key + " ");
//
//        queue.add(key);
//        while(!queue.isEmpty()) {
//            // 1. 큐에서 꺼내온다.
//            key = queue.poll();
//
//            // 2. 목적지 인가
//            // 3. 연결된 곳을 순회
//            for (int i = 0; i < graph.get(key).size(); i++) {
//                // 4. 갈 수 있는가
//                if(!visited[graph.get(key).get(i)]) {
//                    // 5. 체크인
//                    visited[graph.get(key).get(i)] = true;
//                    answer.append(graph.get(key).get(i) + " ");
//
//                    // 6. 큐에 넣는다
//                    queue.add(graph.get(key).get(i));
//                }
//            }
//        }
//        // 7. 체크 아웃
//    }
//}
