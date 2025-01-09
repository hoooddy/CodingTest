package SamsungA.P17070;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] house;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        house = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                house[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        dfs(0, 1, 1);


        br.close();
//        System.out.println(answer);
        bw.write(answer+"\n");
        bw.flush();
        bw.close();


    }

    static void dfs(int r, int c, int direction) {

        // 1. 체크인

        // 2. 목적지 인가
        if (r == N - 1 && c == N - 1) {
            answer++;
            return;
        }

        // 3. 연결된 곳 을 순회
        // direction 1 -> 가로, 2 -> 세로, 3 -> 대각선
        // 3-1. 가로일 경우
        if (direction == 1) {
            // 4. 대각선 검사 및 이동
            if (checkD(r, c)) { // 대각선 검사
                // 5. 간다
                dfs(r + 1, c + 1, 3);
            }
            // 4. 가로 검사 및 이동
            if (checkH(r, c)) { // 오른쪽 검사
                // 5. 간다
                dfs(r, c + 1, 1);
            }
        }
        // 3-2. 세로일 경우
        else if (direction == 2) {
            // 4. 대각선 검사 및 이동
            if (checkD(r, c)) { // 대각선 검사
                // 5. 간다
                dfs(r + 1, c + 1, 3); // 대각선 이동
            }
            // 4. 세로 검사 및 이동
            if (checkV(r, c)) { // 아래 검사
                // 5. 간다
                dfs(r+1, c, 2); // 아래 이동
            }

        }
        // 3-3. 대각선일 경우
        else if (direction == 3) {
            // 4. 대각선 검사 및 이동
            if (checkD(r, c)) { // 대각선 검사
                // 5. 간다
                dfs(r + 1, c + 1, 3); // 대각선 이동
            }

            // 4. 가로 검사 및 이동
            if (checkH(r, c)) { // 오른쪽 검사
                // 5. 간다
                dfs(r, c + 1, 1); // 오른쪽 이동
            }

            // 4. 세로 검사 및 이동
            if (checkV(r, c)) { // 아래 검사
                // 5. 간다
                dfs(r+1, c, 2); // 아래 이동
            }
        }

        // 6. 체크아웃


    }

    static boolean checkH(int r, int c) {
        if (c + 1 <= N - 1 && house[r][c + 1] == 0){
            return true;
        }
        return false;
    }

    static boolean checkV(int r, int c){
        if (r + 1 <= N - 1 && house[r+1][c] == 0) {
            return true;
        }
        return false;
    }

    static boolean checkD(int r, int c) {
        if ((c + 1 <= N - 1 && house[r][c + 1] == 0) // 오른쪽 검사
                && (r + 1 <= N - 1 && house[r + 1][c] == 0) // 아래 검사
                && ((c + 1 <= N - 1 && r + 1 <= N - 1) && house[r + 1][c + 1] == 0)) { // 대각선 검사
            return true;
        }
        return false;
    }

}
