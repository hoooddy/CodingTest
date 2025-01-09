package SWEA.P1204;
import java.util.*;
import java.io.*;


public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            Map<Integer, Integer> scoreCountMap = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            st.nextToken();

            st = new StringTokenizer(br.readLine());
            Integer score;
            Integer count;
            for(int studentIdx = 0; studentIdx < 1000; studentIdx++){
                score = Integer.parseInt(st.nextToken());
                count = scoreCountMap.get(score);
                if(count == null){
                    scoreCountMap.put(score, 0);
                }
                scoreCountMap.put(score, scoreCountMap.get(score) + 1);
            }

            int answerScore = 0;
            int answerCount = 0;

            int tmpCount;
            for (Integer key : scoreCountMap.keySet()){

                tmpCount = scoreCountMap.get(key);
                if(tmpCount >= answerCount){
                    answerCount = tmpCount;
                    if(key > answerScore){
                        answerScore = key;
                    }
                }
            }

            sb.append("#"+test_case+" "+answerScore+"\n");

        }


        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();


    }
}
