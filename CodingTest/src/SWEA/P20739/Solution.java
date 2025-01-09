package SWEA.P20739;

import java.util.*;
import java.io.*;

public class Solution {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[][] photo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 0; test_case < T; test_case++) {
            sb.append("#" + (test_case + 1) + " ");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            photo = new int[N][M];

            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    photo[n][m] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;

            // 가로로 연속된 1을 찾는 탐색
            for (int n = 0; n < N; n++) {
                int length = 0;
                for (int m = 0; m < M; m++) {
                    if (photo[n][m] == 1) {
                        length++;
                    } else {
                        if (length >= 2) {
                            answer = Math.max(answer, length);
                        }
                        length = 0;
                    }
                }
                if (length >= 2) {
                    answer = Math.max(answer, length);
                }
            }

            // 세로로 연속된 1을 찾는 탐색
            for (int m = 0; m < M; m++) {
                int length = 0;
                for (int n = 0; n < N; n++) {
                    if (photo[n][m] == 1) {
                        length++;
                    } else {
                        if (length >= 2) {
                            answer = Math.max(answer, length);
                        }
                        length = 0;
                    }
                }
                if (length >= 2) {
                    answer = Math.max(answer, length);
                }
            }

            sb.append(answer + "\n");
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}