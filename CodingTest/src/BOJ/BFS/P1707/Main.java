package BOJ.BFS.P1707;

import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());


        for(int test_case = 0 ; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());

            int V, E;

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            List<Integer>[] graph = new ArrayList[V+1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for(int e = 0; e < E; e++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }

            int[] visited = new int[V+1];
            boolean isBipartite = true;

            for(int v = 1; v < V + 1; v++) {
                if(visited[v] == 0) {
                    if(!BFS(v, graph, visited)) {
                        isBipartite = false;
                        break;
                    }
                }
            }

            if (isBipartite){
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean BFS(int start, List<Integer>[] graph, int[] visited) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = 1;
        while(!queue.isEmpty()) {
            int node = queue.poll();

            for(int neighbor : graph[node]) {
                if(visited[neighbor] == 0){
                    visited[neighbor] = -visited[node];
                    queue.add(neighbor);
                } else if (visited[neighbor] == visited[node]){
                    return false;
                }
            }
        }
        return true;
    }
}
