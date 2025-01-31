package Softeer.루돌프_월드컵;
import java.io.*;
import java.util.*;
import java.math.*;

// 루돌프 후보는 4
// 상위 2마리를 선발
// 1번 루돌프가 선택될 확률을 구하고 싶다
// 루돌프 힘 = F[]
// i번 루돌프가 이길 확률 4F[i] / (5F[i] + 5F[j])
// j번 루돌프가 이길 확률 4F[j] / (5F[i] + 5F[j])
// i, j가 비길 확률 (F[i] + F[j]) / (5F[i] + 5F[j])

// 모든 루돌프끼리 1번씩 경기를 진행 -> 승점 계산 후 등수를 매김
// 이기면 3점, 비기면 1점, 지면 0점 획득
// 최종 점수가 같으면 작은 번호가 더 높은 순위
// 1번 루돌프가 선택될 확률은 얼마인가

// 1번 루돌프가 선택되는 경우의 수
// 1. 1번 루돌프가 가장 승점이 많다
// 2. 1번 루돌프가 공동 1등이다 (2등과 점수가 같다)
// 3. 1번 루돌프가 2등이다.
// 4. 1번 루돌프가 공동 2등이다(3등과 점수가 같다)

// 1번 루돌프가 선택되지 않는 경우
// 1. 1번 루돌프가 3등이다.
// 2. 1번 루돌프가 공동 3등이다(4등과 점수가 같다)
// 3. 1번 루돌프가 4등이다.


// 아니다 1번 루돌프가 전체 공동 2등이어도 가능하다
// 점수의 자료형을 double로 해줘야 한다.

public class Main {
    static double[] F;
    static List<List<Integer>> orderList;
    static double answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        F = new double[5];
        for(int i = 1; i <= 4; i++) {
            F[i] = Double.parseDouble(st.nextToken());
        }

        List<Integer> tmpOrder = new ArrayList<>();
        orderList = new ArrayList<>();
        boolean[] visited = new boolean[5];
        getOrder(tmpOrder, 1, visited);

        int[] score = new int[5];
        answer = 0;
        playSoccer(0, score, 1);

        answer = (double) (Math.round(answer*100000)/1000.0);

        br.close();
        bw.write(String.valueOf(String.format("%.3f",answer)));
        bw.flush();
        bw.close();

    }

    static void getOrder(List<Integer> tmpOrder, int start, boolean[] visited) {
        if(tmpOrder.size() == 2) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(tmpOrder.get(0));
            tmp.add(tmpOrder.get(1));
            orderList.add(tmp);
            return;
        }


        for(int i = start; i < 5; i++) {
            if(!visited[i]) {
                visited[i] = true;
                tmpOrder.add(i);
                getOrder(tmpOrder, i, visited);
                visited[i] = false;
                tmpOrder.remove(tmpOrder.size() - 1);
            }
        }
    }

    static void playSoccer(int orderIdx, int[] score, double percentage) {
        if(orderIdx == 6) {
            int targetScore = score[1];
            int tmpCount = 0;
            for(int i = 2; i < 5; i++) {
                if(targetScore >= score[i]) {
                    tmpCount++;
                }
            }
            if(tmpCount >= 2) {
                answer += percentage;
            }
            return;
        }

        int i = orderList.get(orderIdx).get(0);
        int j = orderList.get(orderIdx).get(1);
        // int Fi = F[i];
        // int Fj = F[j];

        // i를 기준으로
        // 이기고
        // i번 루돌프가 이길 확률 4F[i] / (5F[i] + 5F[j])
        score[i] += 3;
        playSoccer(orderIdx + 1, score, percentage * (4*F[i] / (5*F[i] + 5*F[j])));
        score[i] -= 3;

        // 비기고
        // i, j가 비길 확률 (F[i] + F[j]) / (5F[i] + 5F[j])
        score[i] += 1;
        score[j] += 1;
        playSoccer(orderIdx + 1, score, percentage * ((F[i] + F[j]) / (5*F[i] + 5*F[j])));
        score[i] -= 1;
        score[j] -= 1;

        // 지고
        // j번 루돌프가 이길 확률 4F[j] / (5F[i] + 5F[j])
        score[j] += 3;
        playSoccer(orderIdx + 1, score, percentage * (4*F[j] / (5*F[i] + 5*F[j])));
        score[j] -= 3;


    }
}
