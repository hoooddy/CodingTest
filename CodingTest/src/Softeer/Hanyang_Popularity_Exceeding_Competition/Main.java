package Softeer.Hanyang_Popularity_Exceeding_Competition;

import java.io.*;
import java.util.*;

// DP
// 철민이의 현재 인기도 X
// i번 유명인의 인기도 Pi
// i번 유명인의 친화력 Ci
// |Pi - X <= Ci| 여야 철민이의 인기도가 1 올라감
// |Pi - X > Ci| 는 변하지 않음 -> 인기도가 줄어들지 않음 -> DFS X -> DP O
// 일부 유명인만 만나서 인기도를 최대화 하고 싶음
public class Main {

    static int N;
    static int[] popularity, friendly;
    static int answer;
    // static int[] X;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        popularity = new int[N];
        friendly = new int[N];
        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            popularity[n] = Integer.parseInt(st.nextToken());
            friendly[n] = Integer.parseInt(st.nextToken());
        }

        answer = 0;
        getPopularity();

        br.close();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static void getPopularity() {

        for(int n = 0; n < N; n++) {
            if(Math.abs(answer - popularity[n]) <= friendly[n]) {
                answer++;
            }
        }
    }
}
