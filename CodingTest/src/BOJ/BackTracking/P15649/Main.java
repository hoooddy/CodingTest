package BOJ.BackTracking.P15649;

import java.util.*;
import java.io.*;

// N과 M (1)
// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열

public class Main {

    static int N, M;
    static int[] nums, result;
    static boolean[] visited;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N+1];
        for(int n = 1; n <= N; n++) {
            nums[n] = n;
        }

        result = new int[M];
        visited = new boolean[N+1];
        BackTracking(0);

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // 다음 반복에서 앞의 원소를 다시 써야 하므로 visited[n]를 다시 false로 지정한다
    static void BackTracking(int depth) {
        if(depth == M) {
            for(int num : result) {
                sb.append(num + " ");
            }
            sb.append("\n");
            return;
        }

        for(int n = 1; n <= N; n++) {
            if(!visited[n]) {
                visited[n] = true;
                result[depth] = nums[n];

                BackTracking(depth + 1);

                visited[n] = false;
            }
        }
    }
}
