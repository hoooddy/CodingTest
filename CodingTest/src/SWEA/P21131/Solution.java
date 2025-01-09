package SWEA.P21131;

import java.util.*;
import java.io.*;
// 아직 못 풀었음...
public class Solution {

    static int N;
    static int[][] matrix;
    static int[][] tmpMatrix;
    static int[] orderArray;
    static int answer;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T;
        T = Integer.parseInt(st.nextToken());


        for(int test_case = 0; test_case < T; test_case++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            matrix = new int[N + 1][N + 1];
            tmpMatrix = new int[N + 1][N + 1];
            for(int i = 1; i < N + 1; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j < N + 1; j++){
                    int tmp = Integer.parseInt(st.nextToken());
                    matrix[i][j] = tmp;
                    tmpMatrix[i][j] = tmp;
                }
            }


            answer = Integer.MAX_VALUE;
            if(check()){
                answer = 0;
                System.out.println(answer);

                continue;
            }

            orderArray = new int[N + 1];

            for(int i = 2; i < N + 1 ; i++){
                orderArray[i] = i;
            }

            for(int targetDepth = 1; targetDepth < N-1; targetDepth++){
                int[] transposeOrder = new int[N - 1];
                boolean[] visited = new boolean[N + 1];

                if(dfs(transposeOrder, visited, 0, targetDepth) == 1){
                    break;
                }

            }

//            sb.append(answer+"\n");
            System.out.println(answer);
//            bw.flush();

        }
//        br.close();
//        bw.write(sb.toString());
//        bw.flush();
//        bw.close();

    }

    static boolean check() {
        for(int i = 1; i < N + 1; i++){
            for(int j = i; j < N + 1; j++){
                if(tmpMatrix[i][j] != (i - 1) * N + j){
                    return false;
                }
            }
        }

        return true;
    }

    static void transpose(int x) {
        for (int i = 1; i <= x; i++) {
            for (int j = i + 1; j <= x; j++) { // 대각선 위만 전치
                int temp = tmpMatrix[i][j];
                tmpMatrix[i][j] = tmpMatrix[j][i];
                tmpMatrix[j][i] = temp;
            }
        }
    }
    static int dfs(int[] transposeOrder, boolean[] visited, int depth, int targetDepth){
        if(depth > targetDepth){
            for(int i = 0; i<matrix.length; i++){
                tmpMatrix[i] = matrix[i].clone();
            }

            for(int i = 0; i < targetDepth + 1; i++){
                transpose(transposeOrder[i]);
            }

            if(check()){
                if(targetDepth + 1 < answer){
                    answer = targetDepth + 1;
                }
                return 1;
            }

            return 0;
        }

        for(int i = 2; i < orderArray.length; i++) {

            if(!visited[i]) {
                transposeOrder[depth] = orderArray[i];

                if(depth > 0){
                    visited[i-1] = false;
                }
                visited[i] = true;

                if(dfs(transposeOrder, visited, depth + 1, targetDepth) == 1){
                    return 1;
                }
                visited[i] = false;
            }
        }
        return 0;
    }

}
