package SamsungA.P16637;

import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static String expression;
    static int answer;
    static int maxResult = Integer.MIN_VALUE;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        expression = br.readLine();

        // 초기 값 계산 (괄호 없이 계산한 결과)
        dfs(0, expression.charAt(0) - '0');

        System.out.println(maxResult);
    }

    // 깊이 우선 탐색으로 괄호를 넣을 수 있는 모든 경우를 시뮬레이션
    public static void dfs(int idx, int currentResult) {
        // 1. 체크인
        // 2. 목적지 인가
        if (idx >= N - 1) {
            maxResult = Math.max(maxResult, currentResult);
            return;
        }

        // 현재 연산자와 다음 숫자를 계산
        char operator = expression.charAt(idx + 1);
        int nextNumber = expression.charAt(idx + 2) - '0';

        // 3. 연결된 곳을 순회
        // 4. 갈 수 있는가?
        // 5. 간다
        // 괄호 없이 계산
        dfs(idx + 2, calculate(currentResult, operator, nextNumber));

        // 괄호를 넣는 경우
        if (idx + 4 < N) {
            int nextNextNumber = expression.charAt(idx + 4) - '0';
            int tempResult = calculate(nextNumber, expression.charAt(idx + 3), nextNextNumber);
            dfs(idx + 4, calculate(currentResult, operator, tempResult));
        }

        // 6. 체크아웃
    }

    // 간단한 사칙연산 수행
    public static int calculate(int a, char op, int b) {
        if (op == '+') return a + b;
        else if (op == '-') return a - b;
        else return a * b;
    }

}
