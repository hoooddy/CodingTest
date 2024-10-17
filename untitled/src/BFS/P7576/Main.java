package BFS.P7576;

import java.util.*;
import java.io.*;


// 익은 토마토와 인접한(좌,우,위,아래) 토마토는 하루가 지나면 익는다.
    // 토마토가 없을 수도 있음
// 며칠이 지나야 모든 토마토가 익는지 최소 일수를 구하자.
public class Main {

    static int M, N;
    static int[][] box;
    //좌 우 위 아래
    static int[] dy = {0,0,-1,1};
    static int[] dx = {-1,1,0,0};
    static int numYetTomato;
    static int numEmpty;
    static int numDoneTomato;
    static int answer;

    static Queue<Tomato> tomatoQueue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());


        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        numYetTomato = 0;
        numEmpty = 0;
        numDoneTomato = 0;

        tomatoQueue = new LinkedList<>();


        int tmp;
        for(int  n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M ; m++){
                tmp = Integer.parseInt(st.nextToken());
                box[n][m] = tmp;
                if(tmp == 1) {
                    numDoneTomato += 1;
                    tomatoQueue.add(new Tomato(n, m, 0));
                } else if (tmp == -1){
                    numEmpty += 1;
                } else if (tmp == 0){
                    numYetTomato += 1;
                }
            }
        }

        answer = 0;


        int day = 0;
        while(!tomatoQueue.isEmpty()){
            // 1. 큐에서 꺼내옴
            Tomato tomato = tomatoQueue.poll();
            day = tomato.day;
            // 2. 목적지 인가
            // 3. 연결된 곳 순회
            for(int i = 0; i < 4; i++){
                // 4. 갈 수 있는가
                if(tomato.y + dy[i] < N
                        && tomato.y + dy[i] >= 0
                        && tomato.x + dx[i] < M
                        && tomato.x + dx[i] >= 0
                        && box[tomato.y + dy[i]][tomato.x + dx[i]] == 0) {
                    // 5. 체크인
                    // 6. 큐에 넣는다.
                    box[tomato.y + dy[i]][tomato.x + dx[i]] = 1;
                    tomatoQueue.add(new Tomato(tomato.y + dy[i], tomato.x + dx[i], tomato.day+1));
                }
            }
            answer = day;
        }
        // 7. 체크아웃


        if (checkTomatoBox()) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }

    }

    static boolean checkTomatoBox() {
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++) {
                if (box[n][m] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Tomato {
        int y;
        int x;
        int day;
        Tomato(int y, int x, int day){
            this.y = y;
            this.x = x;
            this.day = day;
        }
    }
}

