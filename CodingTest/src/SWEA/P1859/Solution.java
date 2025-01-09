package SWEA.P1859;

import java.util.*;
import java.io.*;

public class Solution {

    static int N;
    static int[] priceArray;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();


        int T;

        T = Integer.parseInt(st.nextToken());


        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            priceArray = new int[N];

            st = new StringTokenizer(br.readLine());


            for(int i = 0; i < N; i++) {
                priceArray[i] = Integer.parseInt(st.nextToken());
            }



            long price = priceArray[N - 1];
            long count = 0;
            long spend = 0;
            long margin = 0;
            long tmp;
            for(int i = N-2; i >= 0; i--) {
                tmp = priceArray[i];

                if(tmp > price){
                    if(count != 0){
                        margin += count * price - spend;
                        count = 0;
                        spend = 0;
                    }

                    price = tmp;
                } else if(tmp < price){
                    count += 1;
                    spend += tmp;
                }

                if(i==0 && count != 0){
                    margin += count * price - spend;
                }

            }


            sb.append("#"+test_case+" "+margin+"\n");

        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
