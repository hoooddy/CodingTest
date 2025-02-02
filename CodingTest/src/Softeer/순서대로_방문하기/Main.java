package Softeer.순서대로_방문하기;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] road;
    static List<String> target;
    static int startY, startX, endY, endX;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        road = new int[N+1][N+1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                road[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        target = new ArrayList<>();
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());


            if(m == 0) {
                startY = y;
                startX = x;
            } else if(m == M - 1) {
                endY = y;
                endX = x;
            }

            target.add(y+" "+x);
        }

        boolean[][] visited = new boolean[N+1][N+1];
        answer = 0;
        visited[startY][startX] = true;
        List<String> checkPoint = new ArrayList<>();
        checkPoint.add(target.get(0));
        DFS(visited, startY, startX, checkPoint);

        br.close();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
    static void DFS(boolean[][] visited, int y, int x, List<String> checkPoint) {
        if(y == endY && x == endX) {
            if(checkPoint.size() != M) {
                return;
            }

            for(int m = 0; m < M; m++) {
                if(!target.get(m).equals(checkPoint.get(m))){
                    return;
                }
            }

            answer += 1;

            return;
        }


        for(int i = 0; i < 4; i++) {
            int ty = y + dy[i];
            int tx = x + dx[i];

            if(ty < 1 || ty > N || tx < 1 || tx > N) {
                continue;
            }
            if(road[ty][tx] == 1 || visited[ty][tx]) {
                continue;
            }

            visited[ty][tx] = true;
            if(target.contains(ty+" "+tx)) {
                checkPoint.add(ty+" "+tx);
            }
            DFS(visited, ty, tx, checkPoint);
            if(target.contains(ty+" "+tx)) {
                checkPoint.remove(checkPoint.size() - 1);
            }
            visited[ty][tx] = false;


        }

    }
}

