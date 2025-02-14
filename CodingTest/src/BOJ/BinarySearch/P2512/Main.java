package BOJ.BinarySearch.P2512;

import java.util.*;
import java.io.*;

// 파라메트릭 서치
// 국가 예산 총액은 정해져 있음 -> 모든 예산을 배정해주기 어려울 수도 있다.
// 정해진 총액 이하에서 가능한 최대의 총 예산을 다음과 같은 방법으로 배정
// 1. 모든 요청이 배정될 수 있는 경우 요청한 그대로 배정
// 2. 모든 요청이 배정될 수 없는 경우 -> 특정한 정수 상한액을 계산
    // 상한액 이상 예산 요청은 모두 상한액을 배정, 이하는 그대로 배정


public class Main {

    static int N, M, answer;
    static int[] reqBudget;
    static long maxBudget;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        reqBudget = new int[N];

        int maxReq = Integer.MIN_VALUE;
        for(int n = 0 ; n < N; n++) {
            reqBudget[n] = Integer.parseInt(st.nextToken());
            maxReq = Math.max(reqBudget[n], maxReq);
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        maxBudget = Integer.MIN_VALUE;

        int start = 1;
        int end = maxReq;

        while(start <= end) {
            int mid = (start + end) / 2;
            long sum = calc(mid);

            if(sum <= M) {
                maxBudget = Math.max(maxBudget, sum);
                answer = mid;
                start = mid + 1;
            } else if(sum > M) {
                end = mid - 1;
            }
        }

        br.close();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static long calc(int mid){
        long sum = 0;
        for(int n = 0 ; n < N; n++) {
            if(mid < reqBudget[n]) {
                sum += mid;
            } else {
                sum += reqBudget[n];
            }
        }
        return sum;
    }
}
