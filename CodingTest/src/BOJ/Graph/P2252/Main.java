package BOJ.Graph.P2252;

import java.io.*;
import java.util.*;

// 위상정렬, 인접리스트 활용
public class Main {

    static int N, M;
    static ArrayList<Integer> [] map; // 인접 리스트
    static int [] inDegree; // 각 정점의 진입 차수를 저장한 배열

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1];
        inDegree = new int[N + 1];

        // 그래프 초기화
        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }
        // 진입차수가 0이 되어 탐색 순서가 도달한 정점을 담는 큐
        ArrayDeque<Integer> dq = new ArrayDeque<Integer>();

        // 그래프 정의
        // 먼저 출력되어야 하는 학생 > 나중에 출력되어야 하는 학생 순서로 그래프를 구성함
        // 나중에 출력되어야 하는 학생들의 경우엔 진입차수 1 증가시킴
        int from, to;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            map[from].add(to);
            inDegree[to]++;
        }

        // 최초 탐색을 할 학생을 찾는다.
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                dq.addLast(i);
            }
        }

        int seq = 0; // 마지막 공백을 출력하지 않기 위한 변수
        // Queue가 빌때까지 수행된다.
        while (dq.isEmpty() == false) {
            int now = dq.pollLast();
            seq++;
            //바로 출력
            if (seq == N) {
                bw.write(String.valueOf(now));
            } else {
                bw.write(String.valueOf(now) + " ");
            }

            // 인접한 노드들을 검사한다.
            // 이때 진입차수가 0보다 큰 정점들만 탐색한다(0이면 이미 큐에 있음)
            // 진입차수를 하나씩 내려주고, 0이면 큐에 넣어준다.
            for(int next: map[now]){
                if (inDegree[next] > 0) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        dq.addLast(next);
                    }
                }
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
