package TwoPointer.P2003;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] nums;


    public static void main(String[] args) throws Exception {

//        System.setIn(new FileInputStream("src/TwoPointer/P2003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        System.out.println(N);
        System.out.println(M);

        // 2 Pointer를 사용할 떈 1개 크게 만들어 두는게 편하다.
        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(Arrays.toString(nums));

        // low와 high로 sliding window 구성
        int low = 0, high = 0, sum = nums[0], count = 0;

        while (true) {
            // sum == M -> 답, low++
            if (sum == M) {
                count++;
                sum -= nums[low++];
            }

            // sum > M -> low++
            else if (sum > M) {
                sum -= nums[low++];
            }

            // sum < M -> high++
            else {
                sum += nums[++high];
            }

            if (high == N) {
                break;
            }
        }
        System.out.println(count);

    }

}
