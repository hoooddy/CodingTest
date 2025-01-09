package SamsungA.P17281;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] iningInfo;
    static boolean[] base;
    static int answer;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        iningInfo = new int[N][10];
        for (int i = 0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j<10; j++){
                if(j == 0){
                    continue;
                }
                iningInfo[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        // iningInfo에서 한 줄은 각 선수가 칠 ~을 의미
        // 문제 요구 사항 -> 어떻게 선수를 배치 했을 때 가장 점수가 높은가?
        // 1번은 무조건 4번 타자이다.

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] result = new int[9];
        boolean[] used = new boolean[9];

        base = new boolean[4];
        selectPlayerOrder(arr, result, used, 0);

        br.close();
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }


    static void selectPlayerOrder(int[] oneToNine, int[] result, boolean[] used, int depth) {
        if (depth == oneToNine.length) {
            play(result);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if(!used[i]){
                if (depth == 3 && oneToNine[i] != 1) {
                    return;
                }
                result[depth] = oneToNine[i];
                used[i] = true;
                selectPlayerOrder(oneToNine, result, used, depth+1);
                used[i] = false;
            }
        }
    }

    static void play(int[] player){
        int score = 0;
        int out = 0;
        int playerIndex = 0;
        for (int i = 0; i < N; i++) {
            while (true) {
                switch (iningInfo[i][player[playerIndex]]) {
                    case 1 :
                        score += goBase(1);
                        break;
                    case 2 :
                        score += goBase(2);
                        break;
                    case 3 :
                        score += goBase(3);
                        break;
                    case 4 :
                        score += goBase(4);
                        break;
                    case 0 :
                        out += 1;
                        break;
                }

                playerIndex++;
                if (playerIndex == 9) {
                    playerIndex = 0;
                }

                if(out == 3){
                    base[0] = false;
                    base[1] = false;
                    base[2] = false;
                    base[3] = false;
                    out = 0;

                    break;
                }
            }
        }

        if(score >= answer){
            answer = score;
        }

    }

    static int goBase(int bat){
        int addScore = 0;
        base[0] = true;
        for(int i = 0; i<bat; i++){
            if(base[3] == true){
                addScore += 1;
                base[3] = false;
            }
            if(base[2] == true){
                base[2] = false;
                base[3] = true;
            }
            if(base[1] == true){
                base[1] = false;
                base[2] = true;
            }
            if(base[0] == true){
                base[0] = false;
                base[1] = true;
            }
        }
        return addScore;

    }

}
