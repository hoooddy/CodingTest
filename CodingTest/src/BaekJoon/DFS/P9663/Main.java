//package DFS.P9663;
//
//import java.util.*;
//import java.io.*;
//
//public class Main {
//
//    static int N;
//    static int[] board;
//    static int answer;
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        N = Integer.parseInt(br.readLine());
//
//        board = new int[N];
//
//        answer = 0;
//        dfs(N, 0);
//
//        System.out.println(answer);
//
//    }
//    static void dfs(int N, int row) {
//
//        // 1. 체크인
//        // 2. 목적지 인가
//        if(row == N){
//            answer++;
//        }
//
//        // 3. 연결된 곳 순회
//        // 열을 돈다.
//        for(int col = 0; col < N; col++) {
//            // 4. 갈 수 있는가
//            if(check(row, col, N)){
//                board[row] = col;
//
//                // 5. 간다
//                // 행을 돈다.
//                dfs(N, row + 1);
//                // 어차피 모든 경우의 수를 다 따질 것이므로 초기화는 필요 없다.
////                board[row] = 0;
//            }
//        }
//        // 6. 체크아웃
//    }
//
//    static boolean check(int row, int col, int N){
//        // 퀸을 놓을 수 있는지 없는지 검사한다
//        for (int i = 0; i < row; i++){
//            // 1. 같은 열에는 놓을 수 없다.
//            if(board[i] == col
//                    // 2. 대각선에는 놓을 수 없다.
//                    // 2-1. 두 퀸의 행과 열의 차이가 같을 때 같은 대각선에 있다.
//                    // 그래프 기울기가 같다 -> 같은 대각선에 있다.
//                || Math.abs(board[i] - col) == Math.abs(i - row)){
//                return false;
//            }
//        }
//
//        return true;
//    }
//}





























