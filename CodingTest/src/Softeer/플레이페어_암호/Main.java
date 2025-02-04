package Softeer.플레이페어_암호;

import java.io.*;
import java.util.*;

// 시뮬레이션
// 플레이페어 암호 : 알파벳으로 이루어진 문자열을 암호화
// 문자열 key가 필요하다,
// 빈도 분석을 어렵게 하기 위함 -> 두글자 단위로 암호화 진행
// 5x5 표를 사용, I와 J는 동일하다고 생각, J는 없다
// 1. 키를 5x5 표로 변환하며 채워 넣음, 칸이 남으면 남은 알파벳으로 채워 넣음
// 2. 암호화 하려는 메시지를 2글자씩 나눔(쌍), 2글자(쌍)가 같으면 중간에 X를 채워 넣음
// 2-1. 쌍이 XX면 Q를 넣음
// 2-2. 마지막이 하나가 남으면 X를 넣음
// 3. 두 글자가 표에서 같은 행에 존재 -> 오른쪽으로 한칸 이동한 칸에 적힌 글자로 암호화, 열이 5 -> 1로 이동
// 4. 두 글자가 표에서 같은 열에 존재 -> 아래쪽으로 한칸 이동한 칸에 적힌 글자로 암호화, 행이 5 -> 1로 이동
// 5. 다른 행, 열에 존재 -> 서로의 열에 존재하는 글자로 암호화
//
public class Main {

    static String plainText;
    static String key;
    static char[][] keyArray;
    static Map<Character, String> keyMap;
    static List<List<Character>> pairList;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        plainText = br.readLine();
        key = br.readLine();

        Map<Character, Boolean> tmp = new HashMap<>();
        for(int i = 'A'; i <= 'Z'; i++) {
            if(Character.toChars(i)[0] == 'J') {
                continue;
            }
            tmp.put(Character.toChars(i)[0], false);
        }

        keyArray = new char[5][5];
        keyMap = new HashMap<>();
        int row = 0, col = 0;
        for(int i = 0; i < key.length(); i++) {
            if(tmp.get(key.charAt(i))) {
                continue;
            }

            tmp.put(key.charAt(i), true);

            keyMap.put(key.charAt(i), row+","+col);
            keyArray[row][col] = key.charAt(i);

            col += 1;
            if(col == 5) {
                col = 0;
                row += 1;
            };
        }

        for(Character alphabet : tmp.keySet()) {
            if(tmp.get(alphabet)){
                continue;
            }

            keyMap.put(alphabet, row+","+col);
            keyArray[row][col] = alphabet;

            col += 1;
            if(col == 5) {
                col = 0;
                row += 1;
            };
        }

        pairList = new ArrayList<>();
        int idx = 0;
        while(true) {
            if(idx >= plainText.length()){
                break;
            }

            if(idx == plainText.length() - 1) {
                List<Character> tmpList = new ArrayList<>();
                tmpList.add(plainText.charAt(idx++));
                tmpList.add('X');
                pairList.add(tmpList);
            }

            else if(plainText.charAt(idx) == plainText.charAt(idx+1)) {
                if(plainText.charAt(idx) == 'X') {
                    List<Character> tmpList = new ArrayList<>();
                    tmpList.add(plainText.charAt(idx++));
                    tmpList.add('Q');
                    pairList.add(tmpList);
                } else {
                    List<Character> tmpList = new ArrayList<>();
                    tmpList.add(plainText.charAt(idx++));
                    tmpList.add('X');
                    pairList.add(tmpList);
                }
            }

            else if (plainText.charAt(idx) != plainText.charAt(idx+1)){
                List<Character> tmpList = new ArrayList<>();
                tmpList.add(plainText.charAt(idx));
                tmpList.add(plainText.charAt(idx+1));
                pairList.add(tmpList);
                idx += 2;
            }
        }

        for(List<Character> pair : pairList) {
            char a = pair.get(0);
            char b = pair.get(1);

            // 3. 두 글자가 표에서 같은 행에 존재 -> 오른쪽으로 한칸 이동한 칸에 적힌 글자로 암호화, 열이 5 -> 1로 이동
            // 4. 두 글자가 표에서 같은 열에 존재 -> 아래쪽으로 한칸 이동한 칸에 적힌 글자로 암호화, 행이 5 -> 1로 이동
            // 5. 다른 행, 열에 존재 -> 서로의 열에 존재하는 글자로 암호화

            String coordinateA = keyMap.get(a);
            String coordinateB = keyMap.get(b);

            int aRow = Integer.parseInt(coordinateA.split(",")[0]);
            int aCol = Integer.parseInt(coordinateA.split(",")[1]);

            int bRow = Integer.parseInt(coordinateB.split(",")[0]);
            int bCol = Integer.parseInt(coordinateB.split(",")[1]);

            if(aRow == bRow) {
                aCol += 1;
                bCol += 1;

                aCol %= 5;
                bCol %= 5;

                sb.append(keyArray[aRow][aCol]);
                sb.append(keyArray[bRow][bCol]);
            } else if (aCol == bCol) {
                aRow += 1;
                bRow += 1;

                aRow %= 5;
                bRow %= 5;

                sb.append(keyArray[aRow][aCol]);
                sb.append(keyArray[bRow][bCol]);
            }
            else {
                sb.append(keyArray[aRow][bCol]);
                sb.append(keyArray[bRow][aCol]);
            }
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
