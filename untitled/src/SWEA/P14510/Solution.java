package SWEA.P14510;

import java.util.*;
import java.io.*;

public class Solution {

    static int N;
    static int[] forest;
    static int highest;
    static int remainOne;
    static int remainTwo;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            forest = new int[N];
            highest = Integer.MIN_VALUE;
            int tmpHeight;
            for(int n = 0; n < N; n++){
                tmpHeight = Integer.parseInt(st.nextToken());
                forest[n] = tmpHeight;
                if(tmpHeight > highest) {
                    highest = tmpHeight;
                }
            }

            remainOne = 0;
            remainTwo = 0;

            for(int n = 0; n < N; n++){
                tmpHeight = highest - forest[n];

                remainTwo += tmpHeight / 2;
                remainOne += tmpHeight % 2;

            }

            // 2는 1 2개로 바꿀 수 있으므로 remainOne과 remainTwo의 균형을 맞춰 준다.
            while(remainTwo > remainOne && remainTwo - remainOne > 1){
                remainTwo -= 1;
                remainOne += 2;
            }

            int answer = 0;
            if(remainOne > remainTwo){
                answer += remainOne * 2 - 1;
            } else if (remainTwo > remainOne) {
                answer += remainTwo * 2;
            } else {
                answer += remainOne * 2;
//                answer += remainTwo * 2;
            }

            sb.append("#" + test_case + " " + answer + "\n");

        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
