package BOJ.Set.P1717;

import java.io.*;
import java.util.*;

// Union-Find
// 여러 노드가 존재할 떄,
// 두 개의 노드를 선택해서
// 현재 두 노드가 서로 같은 그래프에 속하는지 판별하는 알고리즘
public class Main {

    static int N, M;
    static int[] set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        set = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            set[i] = i;
        }

        int op, a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            op = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            // 0 두 집합을 합친다.
            if (op == 0) {
                union(a, b);
            }

            // 1 이면 두 값이 하나의 집합에 포함되는지 확인한다.
            else if(op == 1) {
                if (find(a) == find(b)) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static void union(int a, int b) {
        int aIndex = find(a);
        int bIndex = find(b);

        set[aIndex] = bIndex;
    }

    private static int find(int i) {
        if (set[i] == i) {
            return i;
        } else {
            return set[i] = find(set[i]);
        }
    }
}
