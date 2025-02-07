package Softeer.강의실_배정;

import java.io.*;
import java.util.*;

// 강의실 1개에 최대한 많은 강의 배정
// 배정된 강의는 겹치지 않아야 함
// 수업시간 길이와 상관 없이 최대한 많은 강의를 배정
// 단 두 강의의 시작 시간과 종료 시간은 겹쳐도 됨.
// 수업 시간의 길이는 상관 없으니 강의가 빨리 끝나는 순서대로 골라주면 된다.
// 1. 강의가 끝나는 시간 기준으로 오름차순 정렬
// 2. 강의를 선택할 때 이전 강의 시간의 끝나는 시간이 현재 강의 시간의 시작 시간보다 크면 continue
class Lecture implements Comparable<Lecture> {
    int start;
    int end;

    Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int compareTo(Lecture o) {
        return this.end - o.end;
    }
}

public class Main {

    static int N;
    static Lecture[] lectures;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        lectures = new Lecture[N];

        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            lectures[n] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lectures);

        int previousEnd = Integer.MIN_VALUE;
        int answer = 0;
        for(Lecture lecture : lectures) {
            if(previousEnd > lecture.start) {
                continue;
            }
            answer += 1;
            previousEnd = lecture.end;
        }
        System.out.println(answer);
    }
}
