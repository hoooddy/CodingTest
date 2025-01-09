package BOJ.TwoPointer.P1806;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int S;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        nums = new int[N + 1];
        for (int n = 0; n < N; n++) {
            nums[n] = Integer.parseInt(st.nextToken());
        }

        int low = 0, high = 0;
        int sum = nums[0];
        int minLen = Integer.MAX_VALUE;

        while (true) {
            // sum < S
            if (sum < S) {
                high++;
                sum += nums[high];
            }

            // sum >= S
            else if (sum >= S) {
                int tmpLen = high - low + 1;
                if (tmpLen <= minLen) {
                    minLen = tmpLen;
                }

                sum -= nums[low];
                low++;
            }

            if (high == N) {
                break;
            }
        }

        if (minLen == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minLen);
        }

    }

}
