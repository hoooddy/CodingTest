package Softeer.Pipelined;

import java.io.*;
import java.util.*;

// 슬롯 1개 -> N개 자동차 생산 공정 계획
// i 번째 자동차 -> si 단계 생산 프로세스를 거쳐야 함
// 작업 슬롯은 수직선상에서 [0, 1)로 표현 가능
// i번째 자동차 생산 -> 1/si 크기의 반 열린 구간 차지
// 1초마다 자신의 크기 만큼 뒤로 이동
// i 번째 생산 프로세스 [j-1/si , j/si) 구간 차지, 1초 후 -> [j/si, j+1/si) 구간 차지

public class Main {
    static int N, answer;

    static int[] S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        S = new int[N];
        for(int n = 0; n < N; n++) {
            S[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(S);

        answer = N-1 + S[N-1];

        br.close();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();


    }
}
