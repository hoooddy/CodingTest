package BOJ.BackTracking.P15663;

import java.util.*;
import java.io.*;

// N과 M (9)
// N개의 자연수 중에서 M개를 고른 수열

// 예제 입력
// 4 2
// 9 7 9 1

// 예제 출력
// 1 7
// 1 9
// 7 1
// 7 9
// 9 1
// 9 7
// 9 9

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

        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++) {
            nums[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        result = new int[M];
        visited = new boolean[N];
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
            return ;
        }

        // 같은 자리에 중복되는 값을 한번 더 사용할 수 없다
        // prev를 선언하여 이전 값과 현재 값이 같은지 확인해야 한다
        int prev = 0;
        for(int n = 0; n < N; n++) {
            if(prev != nums[n] && !visited[n]) {
                result[depth] = nums[n];
                visited[n] = true;

                BackTracking(depth + 1);

                prev = nums[n];
                visited[n] = false;
            }
        }
    }
}


























































































