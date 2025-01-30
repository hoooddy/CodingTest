package Softeer.나무_조경;
import java.io.*;
import java.util.*;

// n,n 정원
// 최대 4번 인접해 있는 두 나무를 묶는다
// 인접 -> 상, 하, 좌, 우로 맞닿아 있는 경우
// 두 나무가 묶였을 때 -> 아름다움 = 두 나무의 키의 합
// 묶은 나무끼리는 서로 겹치면 안됨
// 묶인 쌍의 아름다움의 합을 최대로 만들어라.
public class Main {

    static int N;
    static int[][] garden;

    // 상, 하, 좌, 우
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int beauty;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        garden = new int[N][N];

        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < N; m++) {
                garden[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        // 2차원 배열에서 인접한 두 값을 더하여 쌍을 만들 때 최대 4개의 쌍의 최대 합은?
        boolean[][] visited = new boolean[N][N];
        beauty = 0;

        DFS(visited, 0, 0);

        br.close();
        bw.write(String.valueOf(beauty));
        bw.flush();
        bw.close();

    }

    static void DFS(boolean[][] visited, int pair, int tmp) {
        beauty = Math.max(beauty, tmp);
        if (pair == 4) {
            return;
        }

        for(int n = 0; n < N; n++) {
            for(int m = 0; m < N; m++) {
                if(visited[n][m]) {
                    continue;
                }
                visited[n][m] = true;
                for(int i = 0; i < 4; i++) {
                    int ty = n + dy[i];
                    int tx = m + dx[i];

                    if(ty < 0 || ty >= N || tx < 0 || tx >= N) {
                        continue;
                    }

                    if(visited[ty][tx]){
                        continue;
                    }

                    visited[ty][tx] = true;
                    DFS(visited, pair + 1, tmp + garden[n][m] + garden[ty][tx]);
                    visited[ty][tx] = false;
                }

                visited[n][m] = false;
            }
        }
    }
}
