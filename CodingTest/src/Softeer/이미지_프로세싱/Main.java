package Softeer.이미지_프로세싱;

import java.io.*;
import java.util.*;

// HxW 크기의 2차원 비트맵, HxW개의 픽셀
// 1 <= i <= H, 1 <= j <= W
// Q번 연산을 수행
// (i, j, c) -> 픽셀(i, j) 선택 -> 해당 픽셀과 색이 같으면서 가로, 세로로 연결된 모든 픽셀 선택 -> 해당 픽셀을 모두 c로 바꾼다.

// 연결 되어 있다 -> BFS로 탐색 -> 색 바꾸기

class Pixel {
    int y;
    int x;

    Pixel(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int H, W, Q;

    static int[][] bitmap;
    static int dy[] = {-1, 1, 0, 0};
    static int dx[] = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        bitmap = new int[H+1][W+1];

        for(int h = 1; h <= H; h++) {
            st = new StringTokenizer(br.readLine());
            for(int w = 1; w <= W; w++) {
                bitmap[h][w] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        Q = Integer.parseInt(st.nextToken());

        for(int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // BFS를 통해 연산 진행
            BFS(y, x, c);

        }

        // 정답 출력
        StringBuilder sb = new StringBuilder();

        for(int h = 1; h <= H; h++) {
            for(int w = 1; w <= W; w++) {
                sb.append(bitmap[h][w] + " ");
            }
            sb.append("\n");
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void BFS(int y, int x, int c) {
        int targetPixel = bitmap[y][x];

        boolean[][] visited = new boolean[H+1][W+1];
        visited[y][x] = true;
        bitmap[y][x] = c;

        Queue<Pixel> queue = new LinkedList<>();

        queue.add(new Pixel(y, x));

        while(!queue.isEmpty()) {
            Pixel pixel = queue.poll();

            for(int i = 0; i < 4; i++) {
                int ty = pixel.y + dy[i];
                int tx = pixel.x + dx[i];

                // bitmap 범위를 벗어날 경우 continue
                if(ty < 1 || ty > H || tx < 1 || tx > W) {
                    continue;
                }

                // targetPixel과 같지 않으면 continue
                if(bitmap[ty][tx] != targetPixel) {
                    continue;
                }

                if(visited[ty][tx]) {
                    continue;
                }

                bitmap[ty][tx] = c;
                visited[ty][tx] = true;
                queue.add(new Pixel(ty, tx));

            }
        }
    }
}

