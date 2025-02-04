package Softeer.우물_안_개구리;

import java.io.*;
import java.util.*;

// 1. 회원은 1 ~ N 사이의 번호를 가진다
// 2. i번 회원이 들 수 있는 역기의 무게는 Wi
// 3. 회원 사이엔 M개의 친분 관계가 있다.
// 4. (Aj, Bj)는 Aj와 Bj가 친분이 있다는 듯
// 5. i 번 회원은 자신과 친분 관계가 있는 다른 회원들보다 본인의 역기가 가장 무거우면 짱이라고 생각함
// 6. 누구와도 친분이 없는 멤버는 본인이 짱이라 생각함
// 7. 자신이 최고라고 생각하는 회원은 몇명인가?

// 자신이 최고라고 생각하려면
// 친분이 아무도 없거나
// 친분이 있을 때 본인이 가장 크거나
public class Main {

    static int N, M;
    static int[] weight;
    static Map<Integer, List<Integer>> graph;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        weight = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++) {
            weight[n] = Integer.parseInt(st.nextToken());
        }

        int u, v;
        graph = new HashMap<>();
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            if(graph.get(u) == null) {
                graph.put(u, new ArrayList<>());
            }

            if(graph.get(v) == null) {
                graph.put(v, new ArrayList<>());
            }

            graph.get(u).add(v);
            graph.get(v).add(u);
        }


        for(int n = 1; n <= N; n++) {
            List<Integer> friendList = graph.get(n);
            if(friendList == null) {
                answer++;
                continue;
            }

            boolean lose = false;
            for(Integer friend : friendList) {
                if(weight[n] <= weight[friend]) {
                    lose = true;
                    break;
                }
            }

            if(!lose) {
                answer++;
            }
        }

        br.close();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}
