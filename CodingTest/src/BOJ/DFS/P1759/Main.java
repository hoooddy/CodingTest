package BOJ.DFS.P1759;


import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int L, C;
    static char[] data;
    static List<String> result;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DFS/P1759/input.txt"));
        Scanner sc = new Scanner(System.in);

        L = sc.nextInt();
        C = sc.nextInt();

        System.out.println(L + ", " + C);
        data = new char[C];
        result = new LinkedList<>();

        for (int i = 0; i < C; i++) {
             data[i] = sc.next().charAt(0);
        }

        System.out.println(Arrays.toString(data));
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

        dfs(0, 0, 0, -1, "");

        for (String pwd : result) {
            System.out.println(pwd);
        }
    }

    static void dfs(int length, int ja, int mo, int current, String pwd){

        // 1. 체크인
        // 2. 목적지 인가?
        if(length == L){
            if(ja >= 2 && mo >= 1){
                result.add(pwd);
            }
        }

        else{
            // 3. 연결된 곳을 순회
            for (int nextIndex = current + 1; nextIndex < data.length; nextIndex++) {
                char nextData = data[nextIndex];

                // 4. 갈 수 있는가?
                if(nextData == 'a'
                        || nextData == 'e'
                        || nextData == 'i'
                        || nextData == 'o'
                        || nextData == 'u'){
                    // 5. 간다 - dfs(nextData) + 모음
                    dfs(length  + 1, ja, mo + 1, nextIndex, pwd + nextData);
                } else {
                    // 5. 간다 - dfs(nextData) + 자음
                    dfs(length  + 1, ja + 1, mo, nextIndex, pwd + nextData);
                }
            }
        }
        // 6. 체크아웃
    }
}