package BOJ.BFS.P5567;

import java.util.*;
import java.io.*;

// 결혼식에 둥기 중 친구, 친구의 친구를 초대
// 친구는 N명, 학번은 1 ~ N
// 상근이는 1
// 상근이 기준 +1, +2까지만 초대

class Friend {
    int n;
    int connect;
    Friend(int n, int connect) {
        this.n = n;
        this.connect = connect;
    }
}

public class Main {

    static int N, M;
    static List<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];

        for(int n = 1; n <= N; n++) {
            graph[n] = new ArrayList<>();
        }

        int u, v;
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        Queue<Friend> queue = new LinkedList<>();

        queue.add(new Friend(1, 0));

        boolean[] visited = new boolean[N+1];
        visited[1] = true;

        int answer = 0;
        Friend node;
        while(!queue.isEmpty()) {
            node = queue.poll();

            for(Integer friend : graph[node.n]){
                if(visited[friend]) {
                   continue;
                }
                visited[friend] = true;
                if(node.connect <= 1) {
                    queue.add(new Friend(friend, node.connect + 1));
                    answer += 1;
                }
            }
        }
        System.out.println(answer);
    }
}
