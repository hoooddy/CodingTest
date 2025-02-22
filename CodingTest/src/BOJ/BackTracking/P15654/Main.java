package BOJ.BackTracking.P15654;

import java.util.*;
import java.io.*;

// N과 M (5)
// N개의 자연수 중에서 M개를 고른 수열

public class Main {

    static int N, M;
    static int[] nums, result;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args ) throws Exception {
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

        visited = new boolean[N];
        result = new int[M];
        BackTracking(0);

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // 다음 반복에서 앞의 원소를 다시 써야 하므로 visited[n]를 다시 false로 지정한다
    static void BackTracking(int depth) {
        if(depth == M) {
            for(Integer num : result) {
                sb.append(num + " ");
            }
            sb.append("\n");
            return;
        }

        for(int n = 0; n < N; n++) {
            if(!visited[n]) {
                visited[n] = true;
                result[depth] = nums[n];

                BackTracking(depth + 1);

                visited[n] = false;
            }
        }
    }
}
