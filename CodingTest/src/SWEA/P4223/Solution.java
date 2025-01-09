package SWEA.P4223;

import java.util.*;
import java.io.*;


// 면접관의 이름에서 각 단어를 뽑아 조합해 SANSUNG을 만들어라.
// 면접관의 이름에서 각 단어를 뽑아 SAMSUNG이 되었을 때 최소가 되는 점수가 정답
// 만들 수 없다면 -1을 출력


// 면접관의 이름들에서 단어를 뽑았을 때 SAMSUNG이 되는가?
// 반대로 이름중에 SAMSUNG 이라는 철자들이 있는가?


// 면접관들을 뽑자 어떻게?
// N의 조합
public class Solution {

    static int N;

    static List<Person> people;
    static StringBuilder sb = new StringBuilder();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());


        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            people = new ArrayList<>();

            for(int n = 0; n < N; n++){
                st = new StringTokenizer(br.readLine());
                int nameLen = Integer.parseInt(st.nextToken());

                st = new StringTokenizer(br.readLine());
                char[] name = new char[nameLen];
                for(int i = 0; i < nameLen; i++){
                    name[i] = st.nextToken().charAt(0);
                }

                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());

                people.add(new Person(name, score));
            }

            answer = Integer.MAX_VALUE;
            findMinScoreForSAMSUNG(new ArrayList<>(), 0);

            if(answer == Integer.MAX_VALUE){
                sb.append("#"+test_case+" "+ -1);
            } else {
                sb.append("#"+test_case+" "+ answer);
            }
            sb.append("\n");

            answer = Integer.MAX_VALUE;

        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();


    }


    static void findMinScoreForSAMSUNG(List<Person> selected, int idx) {
        // 'SAMSUNG'을 만들 수 있는지 확인
        List<Character> target = new ArrayList<>(Arrays.asList('S', 'A', 'M', 'S', 'U', 'N', 'G'));
        for (Person person : selected) {
            for (char letter : person.name) {
                target.remove(Character.valueOf(letter));
                if (target.isEmpty()) break;
            }
        }

        if (target.isEmpty()) {
            int totalScore = 0;
            for (Person person : selected) {
                totalScore += person.score;
            }
            answer = Math.min(answer, totalScore);
            return;
        }

        // 재귀적으로 모든 조합을 확인
        for (int i = idx; i < N; i++) {
            selected.add(people.get(i));
            findMinScoreForSAMSUNG(selected, i + 1);
            selected.remove(selected.size() - 1);
        }
    }

    static class Person {
        char[] name;
        int score;

        Person(char[] name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}