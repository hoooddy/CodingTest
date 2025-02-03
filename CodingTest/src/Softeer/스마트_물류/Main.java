package Softeer.스마트_물류;

import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static char[] line;
    static List<Integer> range;
    static List<Integer> robot;
    static int answer;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        String lineTmp = br.readLine();
        line = new char[N];
        robot = new ArrayList<>();
        for(int n = 0; n < N; n++) {
            line[n] = lineTmp.charAt(n);
            if(line[n] == 'P') {
                robot.add(n);
            }
        }

        range = new ArrayList<>();

        int idx = 0;
        for(int i = -1 * K; i <= K; i++) {
            if(i != 0) {
                range.add(i);
            }
        }

        answer = 0;
        for(int rIdx = 0; rIdx < robot.size(); rIdx++) {
            int robotIdx = robot.get(rIdx);
            for(int i = 0; i < 2*K; i++) {
                int ti = robotIdx + range.get(i);
                if(ti < 0 || ti >= N) {
                    continue;
                }

                if(line[ti] == 'H') {
                    line[ti] = 'C';
                    answer++;
                    break;
                }
            }
        }

        br.close();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }
}
