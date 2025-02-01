package Softeer.성적_평가;

import java.io.*;
import java.util.*;

// N명 참여, 대회 3개, 모든 구성원이 각 대회에 참가
// 점수는 0 이상 1000이하, 한 대회에 둘 이상이 동점일 수 있음
// 각 대회별 등수 및 최종 등수, 10, 7, 6, 6, 4 -> 6점은 공동 3등, 4점은 5등
// 나보다 점수가 큰 사람의 수를 세어 1을 더한 것이 자신의 등수
// 대회별 등수는 각 대회에서 얻은 점수, 최종 등수는 세 대회의 점수의 합을 기준
// 각 참가자의 대회별 등수 및 최종 등수를 출력하라

public class Main {

    static int N;
    static Integer[] totalScoreArray;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        totalScoreArray = new Integer[N];
        Arrays.fill(totalScoreArray, 0);
        Map<Integer, Integer> scoreCountMap;
        for(int k = 0; k < 3; k++) {
            st = new StringTokenizer(br.readLine());
            Integer[] scoreArray = new Integer[N];
            scoreCountMap = new HashMap<>();

            for(int n = 0; n < N; n++) {
                scoreArray[n] = Integer.parseInt(st.nextToken());
                totalScoreArray[n] += scoreArray[n];

                if(scoreCountMap.get(scoreArray[n]) == null) {
                    scoreCountMap.put(scoreArray[n], 0);
                }

                scoreCountMap.put(scoreArray[n], scoreCountMap.get(scoreArray[n]) + 1);
            }
            getRank(scoreArray, scoreCountMap);
        }

        scoreCountMap = new HashMap<>();
        for(int n = 0; n < N; n++) {
            if(scoreCountMap.get(totalScoreArray[n]) == null) {
                scoreCountMap.put(totalScoreArray[n], 0);
            }

            scoreCountMap.put(totalScoreArray[n], scoreCountMap.get(totalScoreArray[n]) + 1);
        }
        getRank(totalScoreArray, scoreCountMap);

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // 점수 별 개수
    static void getRank(Integer[] scoreArray, Map<Integer, Integer> scoreCountMap) {
        Set<Integer> tmpScoreSet = new HashSet<>(Arrays.asList(scoreArray));
        List<Integer> tmpScoreList = new ArrayList<>(tmpScoreSet);

        Collections.sort(tmpScoreList, Comparator.reverseOrder());

        Map<Integer, Integer> rankMap = new HashMap<>();

        int rank = 1;
        for(Integer score : tmpScoreList) {
            rankMap.put(score, rank);
            int soreCount = scoreCountMap.get(score);
            if(soreCount >= 2) {
                rank += soreCount;
            } else {
                rank += 1;
            }
        }

        for(int score : scoreArray) {
            sb.append(rankMap.get(score) + " ");
        }
        sb.append("\n");
    }
}

