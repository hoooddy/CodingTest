package SWEA.P1213;

import java.util.*;
import java.io.*;

public class Solution {


    static int N;
    static String targetString;
    static String string;

    public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("src/SWEA1213/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = 10;


        for(int test_case = 1; test_case <= T; test_case++){

            N = Integer.parseInt(br.readLine());

            targetString = br.readLine();
            string = br.readLine();


            int tmp = 0;

            int answer = 0;
            for(int i = 0; i < string.length(); i++) {
                if(string.charAt(i) == targetString.charAt(tmp)) {
                    tmp += 1;

                    if(tmp == targetString.length()) {
                        tmp = 0;
                        answer += 1;
                    }
                } else {
                    tmp = 0;
                    if(string.charAt(i) == targetString.charAt(tmp)) {
                        tmp += 1;

                        if(tmp == targetString.length()) {
                            tmp = 0;
                            answer += 1;
                        }
                    }
                }
            }

            sb.append("#" + N +" "+answer+"\n");

        }

        br.close();
		bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
