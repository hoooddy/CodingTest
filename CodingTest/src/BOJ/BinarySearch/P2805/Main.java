package BOJ.BinarySearch.P2805;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파라메트릭 서치(Parametric Search, 이분 탐색)

public class Main {
    static int N;
    static int M;
    static int[] trees;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];
        int max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(trees[i], max);
        }

        long s = 0;
        long e = max;
        long mid = 0;
        long result = 0;
        while (true) {
            mid = (s + e) / 2;
            long sum = calc(mid);
            // sum == M -> 정답
            if (sum == M) {
                result = mid;
                break;
            }
            // sum < M -> mid -> e
            else if(sum < M){
                e = mid - 1;
            }
            // sum < M -> mid -> s, 정답 후보
            else {
                s = mid + 1;
                result = mid;
            }

            if(s >= e){
                break;
            }
        }
        System.out.println(result);
    }

    static long calc(long value){
        long result = 0;
        for(int i = 0; i < trees.length; i++){
            int tree = trees[i];
            if(tree > value){
                result += tree - value;
            }
        }
        return result;
    }
}