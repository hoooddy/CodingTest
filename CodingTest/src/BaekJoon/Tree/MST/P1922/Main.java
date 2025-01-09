package BaekJoon.Tree.MST.P1922;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class info implements Comparable<info> {
        int node1;
        int node2;
        int distance;

        public info(int node1, int node2, int distance) {
            this.node1 = node1;
            this.node2 = node2;
            this.distance = distance;
        }

        @Override
        public int compareTo(info o) {
            return Integer.compare(distance, o.distance);
        }
    }

    static int N, M;
    static info[] list;
    static int[] group;
    static int answer;

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new info[M + 1]; // 간선 리스트
        group = new int[N + 1]; // 서로소 집합에 사용될 배열
        answer = 0;

        int n1, n2, cost;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            list[i] = new info(n1, n2, cost);
        }

        for (int i = 1; i <= N; i++) {
            group[i] = i;
        }

        Arrays.sort(list, 1, M + 1); // 간선을 비용순으로 정렬

        int connectCount = 0; // 간선을 N-1개 연결하면 최소 신장트리 완성
        for (int i = 1; i <= M; i++) {
            // 현재 선택된 간선의 두개 정점이 연결된 상태가 아니라면 연결해준다.
            if(find(list[i].node1) != find(list[i].node2)){
                union(list[i].node1, list[i].node2);
                answer += list[i].distance;
                connectCount++;
            }

            if (connectCount == N - 1) {
                break;
            }

            br.close();
            bw.write(answer + "\n");
            bw.flush();
            bw.close();
        }
    }

    private static void union(int a, int b) {
        int aGroup = find(a);
        int bGroup = find(b);

        group[aGroup] = bGroup;
    }

    private static int find(int i) {
        if (group[i] == i) {
            return i;
        } else {
            return group[i] = find(group[i]);
        }
    }


}
