package BOJ.BackTracking.P15666;

import java.util.*;
import java.io.*;


// N과 M (12)
// N개의 자연수 중에서 M개를 고른 수열
// 같은 수를 여러 번 골라도 된다.
//    고른 수열은 비내림차순이어야 한다.
//    길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.

// 예제 입력
// 4 2
// 9 7 9 1

// 예제 출력
// 1 1
// 1 7
// 1 9
// 7 7
// 7 9
// 9 9

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

    // result는 비내림차순이어야 하기 때문에 다음 반복의 start은 현재 n로 지정해준다
        // 현재 값은 다음 반복 때 사용할 수 있기 때문
    static void BackTracking(int start, int depth) {
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
        for(int n = start; n < N; n++) {
            if(prev != nums[n]) {
                result[depth] = nums[n];

                BackTracking(n, depth + 1);

                prev = nums[n];
            }
        }
    }
}
