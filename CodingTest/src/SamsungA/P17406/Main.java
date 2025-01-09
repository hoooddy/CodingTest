package SamsungA.P17406;

import java.util.*;
import java.io.*;


// 배열의 값은 각 행의 모든 수의 합 중 최솟 값
// 회전 (r, c, s) 가장 왼쪽 위(r-s, c-s), 가장 오른쪽 아래(r+s, c+s)
// 3,4,2  (1,2), (5,6)
public class Main {

    static int N, M, K;
    static int[][] map;
    static int[][] mapBackup;
    static int answer;
    static int[][] rotateInfo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        mapBackup = new int[N + 1][M + 1];
        for(int n = 1; n < N + 1; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 1; m < M + 1; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
            }
            mapBackup[n] = map[n].clone();
        }


        rotateInfo = new int[K][3];
        int[] rotateOrder = new int[K];

        for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            rotateOrder[k] = k;
            for(int i = 0; i < 3; i++){
                rotateInfo[k][i] = Integer.parseInt(st.nextToken());
            }
        }
        answer = Integer.MAX_VALUE;


        boolean[] used = new boolean[K];
        int[] result = new int[K];
        permutation(rotateOrder, result, used, 0);

        System.out.println(answer);

    }

    static void permutation(int[] arr, int[] result, boolean[] used, int depth){
        if(depth == result.length){

            for(int i = 0; i < mapBackup.length; i++){
                map[i] = mapBackup[i].clone();
            }

            for(int i = 0; i < result.length; i++){
                rotateStart(rotateInfo[result[i]][0], rotateInfo[result[i]][1], rotateInfo[result[i]][2]);
            }

            for(int n = 1; n < N + 1; n++){
                int tmp = 0;
                for(int m = 1; m < M + 1; m++){
                    tmp += map[n][m];
                }
                if(tmp <= answer) {
                    answer = tmp;
                }
            }

        }

        for(int i = 0; i < arr.length; i++){
            if(!used[i]){
                result[depth] = arr[i];
                used[i] = true;
                permutation(arr, result, used, depth+1);
                used[i] = false;
            }

        }

    }


    static void rotateStart(int r, int c, int s){
        int leftUpY, leftUpX, rightDownY, rightDownX;
        int leftDownY, leftDownX, rightUpY, rightUpX;
        leftUpY = r - s;
        leftUpX = c - s;

        rightDownY = r + s;
        rightDownX = c + s;

        leftDownY = rightDownY;
        leftDownX = leftUpX;

        rightUpY = leftUpY;
        rightUpX = rightDownX;

        while(leftUpY < rightDownY){
            int tmpY = leftUpY, tmpX = leftUpX;

            int tmpP = 0;
            int tmpN;
            // 오른쪽

            while(tmpX != rightUpX) {
                if(tmpX == leftUpX){
                    tmpP = map[tmpY][tmpX + 1];
                    map[tmpY][tmpX + 1] = map[tmpY][tmpX];
                    tmpX += 1;
                    continue;
                }
                tmpN = map[tmpY][tmpX+1];
                map[tmpY][tmpX+1] = tmpP;
                tmpP = tmpN;
                tmpX += 1;
            }

            //아래쪽
            while(tmpY != rightDownY) {
                tmpN = map[tmpY+1][tmpX];
                map[tmpY+1][tmpX] = tmpP;
                tmpP = tmpN;

                tmpY += 1;
            }

            // 왼쪽
            while(tmpX != leftDownX) {
                tmpN = map[tmpY][tmpX-1];
                map[tmpY][tmpX-1] = tmpP;
                tmpP = tmpN;

                tmpX -= 1;
            }

            // 위쪽
            while(tmpY != leftUpY) {
                tmpN = map[tmpY-1][tmpX];
                map[tmpY-1][tmpX] = tmpP;
                tmpP = tmpN;

                tmpY -= 1;
            }


            leftUpY += 1;
            leftUpX += 1;

            rightDownY -= 1;
            rightDownX -= 1;

            leftDownY -= 1;
            leftDownX += 1;

            rightUpY += 1;
            rightUpX -= 1;
        }

    }
}

