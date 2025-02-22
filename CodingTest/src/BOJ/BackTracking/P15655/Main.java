package BOJ.BackTracking.P15655;

import java.util.*;
import java.io.*;


// N과 M (6)
// N개의 자연수 중에서 M개를 고른 수열
// 고른 수열은 오름차순이어야 한다.

public class Main {

    static int N, M;
    static int[] nums, result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++) {
            nums[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        result = new int[M];
        BackTracking(0, 0);

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // result는 오름차순이어야 하기 때문에 다음 반복의 start은 현재 n + 1로 지정해준다
    static void BackTracking(int start, int depth) {
        if(depth == M) {
            for(Integer num : result) {
                sb.append(num + " ");
            }
            sb.append("\n");
            return ;
        }

        for(int n = start; n < N; n++) {
            result[depth] = nums[n];
            BackTracking(n + 1, depth + 1);
        }
    }
}
