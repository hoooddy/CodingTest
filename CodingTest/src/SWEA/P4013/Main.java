package SWEA.P4013;

import java.util.*;
import java.io.*;

// 회전 방향 1 -> 시계, 2 -> 반시계
// 자석 N -> 0, 자석 S -> 1
// 빨간 화살표부터 시계 방향 순서대로

public class Main {
    static int T;

    static List<LinkedList<Integer>> gearList;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        int K;

        for(int t = 0; t < T; t++){
            gearList = new ArrayList<>();
            K = Integer.parseInt(br.readLine());

            gearList.add(new LinkedList<>());
            for(int gearIndex = 1; gearIndex < 5; gearIndex++){
                st = new StringTokenizer(br.readLine());
                LinkedList<Integer> gear = new LinkedList<>();
                for(int gearBlade = 0; gearBlade < 8; gearBlade++){
                    gear.add(Integer.parseInt(st.nextToken()));
                }
                gearList.add(gear);
            }

            int gearIndex;
            int spinDirection;
            for(int k = 0; k < K; k++){
                st = new StringTokenizer(br.readLine());

                gearIndex = Integer.parseInt(st.nextToken());
                spinDirection = Integer.parseInt(st.nextToken());

                spinStart(gearIndex, spinDirection);
            }

            gearList.remove(0);
            int target;
            int answer = 0;
            int score = 1;
            for(LinkedList<Integer> gear : gearList){
                target = gear.poll();
                if(target == 1) {
                    answer += score;
                }
                score *= 2;
            }
            System.out.println("#"+(t+1)+" "+answer);

        }
    }

    static void spinStart(int gearIndex, int spinDirection) {
        int[] targetGear = new int[5];

        if(gearIndex == 1) {
            targetGear[1] = spinDirection;

            if(gearList.get(1).get(2) != gearList.get(2).get(6)){
                targetGear[2] = targetGear[1] * -1;
                if(gearList.get(2).get(2) != gearList.get(3).get(6)) {
                    targetGear[3] = targetGear[2] * -1;
                    if(gearList.get(3).get(2) != gearList.get(4).get(6)){
                        targetGear[4] = targetGear[3] * -1;
                    }
                }
            }
            spin(targetGear);
        }

        else if (gearIndex == 2) {
            targetGear[2] = spinDirection;

            if(gearList.get(1).get(2) != gearList.get(2).get(6)) {
                targetGear[1] = targetGear[2] * -1;
            }
            if(gearList.get(2).get(2) != gearList.get(3).get(6)) {
                targetGear[3] = targetGear[2] * -1;
                if(gearList.get(3).get(2) != gearList.get(4).get(6)){
                    targetGear[4] = targetGear[3] * -1;
                }
            }

            spin(targetGear);
        }
        else if (gearIndex == 3) {
            targetGear[3] = spinDirection;

            if(gearList.get(2).get(2) != gearList.get(3).get(6)){
                targetGear[2] = targetGear[3] * -1;
                if(gearList.get(1).get(2) != gearList.get(2).get(6)) {
                    targetGear[1] = targetGear[2] * -1;
                }
            }
            if(gearList.get(3).get(2) != gearList.get(4).get(6)){
                targetGear[4] = targetGear[3] * -1;
            }


            spin(targetGear);

        }
        else if (gearIndex == 4) {
            targetGear[4] = spinDirection;

            if(gearList.get(4).get(6) != gearList.get(3).get(2)){
                targetGear[3] = targetGear[4] * -1;
                if(gearList.get(3).get(6) != gearList.get(2).get(2)){
                    targetGear[2] = targetGear[3] * -1;
                    if(gearList.get(2).get(6) != gearList.get(1).get(2)){
                        targetGear[1] = targetGear[2] * -1;
                    }
                }
            }

            spin(targetGear);
        }
    }

    static void spin(int[] targetGear) {
        int gearIndex;
        int spinDirection;
        int tmp;
        for (int i = 1; i < 5; i++){
            gearIndex = i;
            spinDirection = targetGear[i];
            if(spinDirection == 1) {
                tmp = gearList.get(gearIndex).pollLast();
                gearList.get(gearIndex).addFirst(tmp);
            } else if (spinDirection == -1) {
                tmp = gearList.get(gearIndex).poll();
                gearList.get(gearIndex).add(tmp);
            }
        }
    }

}
