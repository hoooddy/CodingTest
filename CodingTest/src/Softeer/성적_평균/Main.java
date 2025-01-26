package Softeer.성적_평균;
import java.io.*;
import java.util.*;

// 소수점을 반올림하여 출력할 수 있느냐를 물어보는 문제
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, K;
    static int[] score;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        score = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++) {
            score[n] = Integer.parseInt(st.nextToken());
        }

        for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            float tmp = 0;
            for(int a = A; a <=B; a++) {
                tmp += score[a];
            }

            sb.append(String.format("%.2f",tmp / (B-A+1))+"\n");
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
