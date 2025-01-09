package SWEA.P22654;

import java.util.*;
import java.io.*;

public class Solution {
    // 위, 좌, 아래, 우
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int N;
    static char[][] field;
    static int count;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        RC rc = new RC(0, 0, 0);
        for(int test_case = 0; test_case < T; test_case++) {
            sb.append("#"+(test_case+1)+" ");

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            field = new char[N][N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                String rowField = st.nextToken();
                for(int j = 0; j < N; j++){
                    field[i][j] = rowField.charAt(j);
                    if(field[i][j] == 'X'){
                        rc.row = i;
                        rc.col = j;
                        rc.dir = 0;
                    }
                }
            }


            st = new StringTokenizer(br.readLine());
            count = Integer.parseInt(st.nextToken());
            RC tmpRC;
            for(int i = 0; i < count; i++){
                st = new StringTokenizer(br.readLine());
                tmpRC = new RC(rc.row, rc.col, rc.dir);
                int commandCount = Integer.parseInt(st.nextToken());
                String commands = st.nextToken();
                for(int j = 0; j < commandCount; j++){
                    char command = commands.charAt(j);

                    // A: 앞으로 이동
                    if(command == 'A') {
                        // 위 좌 아래 우
                        // 0  1  2   3

                        int ty = tmpRC.row + dy[tmpRC.dir];
                        int tx = tmpRC.col + dx[tmpRC.dir];

                        if(ty < 0 || ty >= N || tx < 0 || tx >= N || field[ty][tx] == 'T'){
                            continue;
                        }

                        tmpRC.row = ty;
                        tmpRC.col = tx;

                    }
                    // 위 좌 아래 우
                    // 0  1  2   3


                    // L: 왼쪽으로 회전
                    else if(command == 'L') {
                        tmpRC.dir = (tmpRC.dir + 1) % 4;
                    }
                    // R: 오른쪽으로 회전
                    else if(command == 'R') {
                        tmpRC.dir = (tmpRC.dir + 3) % 4;
                    }

                }

                if(field[tmpRC.row][tmpRC.col] == 'Y'){
                    sb.append(1+" ");
                }
                else {
                    sb.append(0+" ");
                }
            }

            sb.append("\n");

        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}


class RC{
    int row;
    int col;
    int dir;

    RC(int row, int col, int dir){
        this.row = row;
        this.col = col;
        this.dir = dir;
    }
}
