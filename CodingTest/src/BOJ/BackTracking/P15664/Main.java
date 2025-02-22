package BOJ.BackTracking.P15664;
//
//import java.util.*;
//import java.io.*;
//
//public class Main {
//
//    static int N, M;
//    static int[] nums;
//    static StringBuilder sb = new StringBuilder();
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//
//        nums = new int[N];
//        st = new StringTokenizer(br.readLine());
//        for(int n = 0; n < N; n++) {
//            nums[n] = Integer.parseInt(st.nextToken());
//        }
//
//        Arrays.sort(nums);
//
//        boolean[] visited = new boolean[N];
//        List<Integer> result = new ArrayList<>();
//        BackTracking(result, 0);
//
//        br.close();
//        bw.write(sb.toString());
//        bw.flush();
//        bw.close();
//    }
//
//    static void BackTracking(List<Integer> result, int start) {
//        if(result.size() == M) {
//            for(Integer num : result) {
//                sb.append(num + " ");
//            }
//            sb.append("\n");
//            return;
//        }
//
//        int before = 0;
//        for(int i = start; i < N; i++) {
//            if(before == nums[i]) {
//                continue;
//            }
//            result.add(nums[i]);
//            BackTracking(result, i + 1);
//            result.remove(result.size() - 1);
//            before = nums[i];
//        }
//    }
//}



import java.util.*;
import java.io.*;

// N과 M (10)
// N개의 자연수 중에서 M개를 고른 수열
// 고른 수열은 비내림차순이어야 한다.
// 길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.

// 예제 입력
// 4 2
// 9 7 9 1

// 예제 출력
// 1 7
// 1 9
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

    // result는 오름차순이어야 하기 때문에 다음 반복의 start은 현재 n + 1로 지정해준다
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

                BackTracking(n + 1, depth + 1);

                prev = nums[n];
            }
        }
    }
}