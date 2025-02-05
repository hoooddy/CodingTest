package Softeer.자동차_테스트;

import java.io.*;
import java.util.*;


// 연비가 높다 -> 연료 소비가 적다
// 중앙값을 통해 평균적인 연비를 파악할 수 있다.
// 한번에 3대의 자동차에 대해서만 테스트 할 수 있다.
// q개의 질의에서 중앙값이 mi 값이 나오는 서로 다른 경우의 수를 구하라.

// 연비를 오름차순으로 정렬, 중앙값을 기준으로 좌, 우에 존재하는 연비의 개수를 곱해준다.
// 해시를 사용해 오름차순으로 정렬된 연비의 인덱스를 지정해주고 찾아준다.
public class Main {

    static int N, Q;
    static List<Integer> km;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        km = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++) {
            km.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(km);
        Map<Integer, Integer> kmMap = new HashMap<>();

        for(int n = 0; n < N; n++) {
            kmMap.put(km.get(n), n);
        }

        for(int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            Integer mIdx = kmMap.get(m);

            if(mIdx == null) {
                sb.append(0 + "\n");
                continue;
            }

            int left = mIdx;
            int right = N - 1 - mIdx;

            sb.append(left * right + "\n");
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}