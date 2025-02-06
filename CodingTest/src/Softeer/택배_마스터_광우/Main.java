package Softeer.택배_마스터_광우;

import java.io.*;
import java.util.*;

// 레일은 N개, 같은 무게의 레일은 없다.
// 택배 바구니 무게 M를 넘기전까지 택배 바구니에 택배를 담아 들고 옮겨야 함
// 1. 레일 순서대로 택배를 담는다.
// 2. 바구니 무게를 초과하지 않을 만큼 담는다 -> 이동 1번
// K번 일을 하는데 최소한의 무게로 일을 할 수 있게 만들어라

public class Main {

    static int N, M, K;
    static int[] rail;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        rail = new int[N];
        for(int n = 0; n < N; n++) {
            rail[n] = Integer.parseInt(st.nextToken());
        }

        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[N];
        DFS(result, visited);

        br.close();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static void DFS(List<Integer> result, boolean[] visited) {
        if(result.size() == N) {
            getAnswer(result);
        }

        for(int n = 0; n < N; n++) {
            if(!visited[n]) {
                visited[n] = true;
                result.add(rail[n]);

                DFS(result,visited);

                visited[n] = false;
                result.remove(result.size() - 1);
            }

        }
    }

    static void getAnswer(List<Integer> result) {
        int idx = 0;
        int tmp = 0;

        for(int k = 0; k < K; k++) {
            int basket = 0;
            while(true) {
                basket += result.get(idx++);
                if(basket > M) {
                    basket -= result.get(--idx);
                    tmp += basket;
                    break;
                }
                idx %= N;

            }
        }
        answer = Math.min(answer, tmp);
    }
}
