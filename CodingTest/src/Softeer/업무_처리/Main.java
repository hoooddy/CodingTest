package Softeer.업무_처리;

import java.io.*;
import java.util.*;

// 업무 조직 -> 완전 이진 트리
// root: 부서장
// leaf: 부하직원이 없다 -> 말단 직원
// 업무는 R일 동안 진행
// 처음엔 말단 직원만 K개의 순서가 정해진 업무를 가지고 있음.
// 각 업무에는 번호가 있다.
// 각 날짜에 남은 업무가 있는 경우 말단 직원은 하나의 업무를 처리 해 상사에게 올린다.
// 다른 직원도 대기하는 업무가 있는 경우 업무를 올라온 순서대로 처리해서 상사에게 올린다.
// 단, 홀 수번째 날짜는 왼쪽 부하직원이 올린 업무를, 짝수 번째 날짜에는 오른쪽 부하직원이 올린 업무를 처리
// 부서장이 처리한 일은 완료된 것.
// 업무를 올리는 것은 모두 동시에 진행
// 그날 올린 업무를 상사가 처리하는 것은 그 다음날 가능
// 요구사항: 부서의 조직과 대기하는 업무들을 입력 받아 처리가 완료된 업무들의 번호의 합을 계산하라.

class Person {
    // 말단 직원(Leaf)는 leftJob만 활용
    Queue<Integer> leftJob;
    Queue<Integer> rightJob;
    Queue<Integer> leafJob;

    Person(boolean leaf) {
        if(leaf) {
            this.leafJob = new LinkedList<>();
        } else {
            this.leftJob = new LinkedList<>();
            this.rightJob = new LinkedList<>();
        }

    }
}

public class Main {

    static int H, K, R;
    static Person[] people;

    public static void main(String[] args) throws Exception {
        Queue<Integer> queue = new LinkedList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int orgLength = 1;
        for(int h = 0; h < H; h++) {
            orgLength = 2 * orgLength + 1;
        }

        int leafStart = orgLength - (int) Math.pow(2, H) + 1;

        people = new Person[orgLength + 1];
        for(int i = 1; i <=orgLength; i++) {
            boolean leaf = false;

            if (i >= leafStart) {
                leaf = true;
            }
            people[i] = new Person(leaf);
        }

        // 말단 직원에게 업무 할당
        for(int i = leafStart ; i <= orgLength; i++) {
            st = new StringTokenizer(br.readLine());
            for(int k = 0; k < K; k++) {
                Integer tmp = Integer.parseInt(st.nextToken());
                people[i].leafJob.add(tmp);
            }
        }

        int answer = 0;
        for(int r = 1; r <= R; r++) {
            Integer job = null;

            // 부서장 업무 처리
            if(r % 2 == 1) {
                job = people[1].leftJob.poll();
            } else {
                job = people[1].rightJob.poll();
            }
            if(job != null) {
                answer += job;
            }

            // 일반 직원 업무 처리
            for(int i = 2; i < leafStart; i++) {
                if(r % 2 == 1) {
                    job = people[i].leftJob.poll();
                }
                else {
                    job = people[i].rightJob.poll();
                }

                if(job != null) {
                    // left
                    if(i % 2 == 0) {
                        people[i/2].leftJob.add(job);
                    }
                    // right
                    else {
                        people[i/2].rightJob.add(job);
                    }
                }
            }

            // 말단 직원 업무 처리
            for(int i = leafStart; i <= orgLength; i++) {
                job = people[i].leafJob.poll();

                if(job != null) {
                    // left
                    if(i % 2 == 0) {
                        people[i/2].leftJob.add(job);
                    }
                    // right
                    else {
                        people[i/2].rightJob.add(job);
                    }
                }
            }
        }

        br.close();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }
}
