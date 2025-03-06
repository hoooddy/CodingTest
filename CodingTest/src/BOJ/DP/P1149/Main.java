package BOJ.DP.P1149;

import java.util.*;
import java.io.*;

// DP
// N개의 집
// 거리는 선분, 1번집부터 N번집까지 순서대로
// 집은 빨, 초, 파 중 하나로 색칠
// 규칙
// 1. 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
// 2. N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
// 3. i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.

// 이웃하는 집과 색이 같지 않아야 한다
// 위 규칙을 만족하며 모든 집을 칠하는 비용의 최솟값은?

public class Main {

    static int N;
    static int[][] RGB;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        // RGB[n][i]
        // i: 0 -> Red
        // i: 1 -> Green
        // i: 2 -> Blue
        RGB = new int[N+1][3];

        for(int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            RGB[n][0] = Integer.parseInt(st.nextToken());
            RGB[n][1] = Integer.parseInt(st.nextToken());
            RGB[n][2] = Integer.parseInt(st.nextToken());
        }

        // 시간 초과
//        DFS(-1, 0, 0);
        for (int i = 1; i <= N; i++) {
            RGB[i][0] += Math.min(RGB[i - 1][1], RGB[i - 1][2]);
            RGB[i][1] += Math.min(RGB[i - 1][0], RGB[i - 1][2]);
            RGB[i][2] += Math.min(RGB[i - 1][0], RGB[i - 1][1]);
        }

        System.out.println(Math.min(Math.min(RGB[N][0], RGB[N][1]), RGB[N][2]));
    }





// 시간 초과
//    static void DFS(int prevColor, int money, int depth) {
//        if(answer < money) {
//            return;
//        }
//        if(depth == N) {
//            answer = Math.min(answer, money);
//
//            return;
//        }
//
//        for(int i = 0; i < 3; i++) {
//            if(i == prevColor){
//                continue;
//            }
//
//            DFS(i, money+RGB[depth][i], depth + 1);
//        }
//    }
}
