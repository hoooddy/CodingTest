package SamsungA.P17471;

import java.io.*;
import java.util.*;


// 1. 구역은 1 ~ N
// 2. 각 구역은 두 선거구 중 하나에 포함
// 3. 선거구는 적어도 1 이상의 구역
// 4. 한 선거구에 포함된 구역은 모두 연결되어 있어야 함
// 5. 중간에 통하는 구역은 0개 이상
public class Main {

    static int N;
    static int[] people;
    static int[][] graph;
    static int[] areaArray;
    static Set<Integer> areaSet;
    static int total;
    static int answer;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        people = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        areaArray = new int[N + 1];
        areaSet = new HashSet<Integer>();
        total = 0;
        for(int i = 1; i < N + 1; i++) {
            people[i] = Integer.parseInt(st.nextToken());
            areaArray[i] = i;
            areaSet.add(i);
            total += people[i];
        }

        graph = new int[N + 1][N + 1];
        int u;
        for (int node = 1; node < N + 1; node++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            for(int j = 0; j < index; j++) {
                u = Integer.parseInt(st.nextToken());
                graph[node][u] = 1;
                graph[u][node] = 1;
            }
        }
        // BFS를 돌며 A 선거구, B 선거구로 나누자
        // 1. 두 선거구로 나누는 것이 불가능하면 -1을 출력
        // 2. 두 선거구로 나누는 것이 가능하다면 각 경우의수를 따져가며 인구수의 차이를 구하자.

        Set<Integer> result = new HashSet<>();
        answer = Integer.MAX_VALUE;

        for (int targetDepth = 1; targetDepth <= N/2; targetDepth++){
            divide(areaArray, result, 1, 0, targetDepth);
        }

        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }


    }

    static void divide(int[] areaArray, Set<Integer> groupA, int start, int depth, int targetDepth){
        if (depth == targetDepth) {
            HashSet groupB = new HashSet<Integer>(areaSet);
            groupB.removeAll(groupA);
            // bfs로 되는지 확인

            int groupAPeople = BFS(groupA, groupB, (int) groupA.toArray()[0]);
            int groupBPeople = BFS(groupB, groupA, (int) groupB.toArray()[0]);

            if(groupAPeople == -1 || groupBPeople == -1){
                return;
            } else {
                int tmp = Math.abs(groupAPeople - groupBPeople);
                if(tmp <= answer) {
                    answer = tmp;
                }
            }

        }

        for (int i = start; i < areaArray.length; i++) {
            groupA.add(areaArray[i]);
            divide(areaArray, groupA, i + 1, depth + 1, targetDepth);
            groupA.remove(areaArray[i]);
        }
    }


    static int BFS(Set<Integer> targetGroup, Set<Integer> anotherGroup, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        int groupPeople = 0;
        queue.add(start);
        visited[start] = true;
        groupPeople += people[start];
        int node;
        while(!queue.isEmpty()) {
            // 1. 큐에서 가져온다.
            node = queue.poll();
            // 2. 목적지 인가.
            // 3. 연결된 곳 순회
            for(int i = 1; i < N + 1; i++){
                // 4. 갈 수 있는가
                if(graph[node][i] == 1 && !visited[i]) {
                    // 5. 체크인
                    // 6. 큐에 넣는다.
                    if(!anotherGroup.contains(i)){
                        queue.add(i);
                        visited[i] = true;
                        groupPeople += people[i];
                    }
                }
            }
        }

        for(int index: targetGroup){
            if(visited[index] == false){
                return -1;
            }
        }

        return groupPeople;

        // 7. 체크아웃
    }
}
