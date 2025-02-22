package BOJ.BackTracking.P9663;

import java.util.*;
import java.io.*;

//N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
//N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.

// 퀸은 같은 row, 같은 col, 같은 대각선 상에 놓일 수 없다.
// 문제를 바꿔 생각해보자
// int[][] board = new int[N][N]로 놓지 말고
// int[] board = new int[N]인 board를 생각해보자
    // index: col, value: row
// 각 col에 퀸들을 배치할 수 있는 row들을 찾아낸다고 생각하자

public class Main {

    static int N, answer;
    static int[] board;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        // index: col, value: row
        board = new int[N];
        visited = new boolean[N];
        answer = 0;

        BackTracking(0);

        sb.append(answer);

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void BackTracking(int col) {
        if(col == N) {
            answer += 1;
            return;
        }

        for(int row = 0; row < N; row++) {
            if(visited[row]){
                continue;
            }

            visited[row] = true;

            if(check(col, row)) {
                board[col] = row;
                BackTracking(col + 1);
            }

            visited[row] = false;
        }
    }

    // 해당 col의 row에 퀸을 놓을 수 있는지 검사
    static boolean check(int col, int row) {

        // 같은 col에 있는지는 검사할 필요 없다
            // col을 돌면서 퀸을 놓을 것이기 때문
        // 때문에 검사는 row와 대각선만 수행하면 된다.
        for(int n = 0; n < col; n++) {
            // row 검사
            if(board[n] == row) {
                return false;
            }
            // 대각선 검사
            if(Math.abs(n - col) == Math.abs(board[n] - row)){
                return false;
            }
        }

        return true;
    }
}
