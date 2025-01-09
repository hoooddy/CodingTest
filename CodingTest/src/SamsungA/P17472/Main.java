package SamsungA.P17472;

import java.util.*;
import java.io.*;


// 섬을 다리로 이을려고 한다.
// 다리의 양 끝은 섬과 인접한 바다 위에 있어야 한다.
// 다리의 방향은 바뀔 수 없고, 다리의 길이는 2 이상이다.
// 다리는 가로 또는 세로
// 0은 바다, 1은 땅
// 2는 다리로 하자.
// 모든 섬을 연결하는 최소 다리 길이 출력하자
// 섬을 연결할 수 없으면 -1을 출력
public class Main {
//
//    static int N, M;
//    static int[][] map;
//    static int answer;
//
//    static ArrayList<ArrayList<int[]>> islands;
//
//    public static void main(String[] args) throws Exception {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//
//        map = new int[N][M];
//
//        for(int n = 0; n < N; n++){
//            st = new StringTokenizer(br.readLine());
//            for(int m = 0; m < M; m++){
//                map[n][m] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        answer = Integer.MAX_VALUE;
//
//
//    }
//
//    static void searchIsland(int n, int m){
//
//    }
//
//        // 섬을 만난다 -> 다리를 만든다.
//        // 1. 가로로 갔을 때 섬이 있고, 만들 수 있는 다리 길이가 2이상이면 만들어짐
//        // 1-2. 다리를 만들고 모든 섬이 연결되었는지 확인
//        // 1-3. 이때 다리 길이의 합을 구함.
    static int[] arr = {1, 2, 3};               // 입력 배열
    static boolean[] used = new boolean[arr.length];  // 숫자가 사용되었는지 추적하는 배열
    public static void main(String[] args) {

        int[] result = new int[arr.length];  // 결과를 저장할 배열


        generatePermutations( result, 0);  // 순열을 생성
    }

    public static void generatePermutations( int[] result, int depth) {
        if (depth == arr.length) {    // 모든 숫자를 다 선택한 경우
            printArray(result);       // 결과를 출력
            return;                   // 재귀 종료
        }

        for (int i = 0; i < arr.length; i++) {
            if (!used[i]) {           // 숫자가 사용되지 않았다면
                result[depth] = arr[i];  // 현재 depth에 숫자를 저장
                used[i] = true;       // 해당 숫자를 사용으로 표시
                generatePermutations(result, depth + 1);  // 다음 깊이로 재귀 호출
                used[i] = false;  // 백트래킹: 숫자 사용 해제
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }



}

