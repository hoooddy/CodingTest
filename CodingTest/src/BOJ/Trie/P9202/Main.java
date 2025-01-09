package BOJ.Trie.P9202;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[] mx = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] my = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};

    static int W, N;
    static char[][] map;
    static boolean[][] visited;
    static String answer;
    static int sum;
    static int count;
    static StringBuilder sb = new StringBuilder();
    static TrieNode root = new TrieNode();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        W = Integer.parseInt(br.readLine());

        for (int i = 0; i < W; i++) {
            insertTrieNode(br.readLine());
        }

        br.readLine();
        N = Integer.parseInt(br.readLine());
        StringBuilder resultSb = new StringBuilder();
        for (int n = 0; n < N; n++) {
            map = new char[4][4];
            visited = new boolean[4][4];
            answer = "";
            sum = 0;
            count = 0;
            sb = new StringBuilder();

            for (int i = 0; i < 4; i++) {
                String in = br.readLine();
                for (int k = 0; k < 4; k++) {
                    map[i][k] = in.charAt(k);
                }
            }
            br.readLine();
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    // 출발 가능 조건 -> root가 해당 child를 가지면
                    if (root.hasChild(map[y][x])) {
                        search(x, y, 1, root.getChild(map[y][x]));
                    }
                }
            }
            // 결과 출력
            root.clearHit();
        }
        System.out.println(resultSb.toString());
    }

    static void search(int y, int x, int length, TrieNode node) {
        // 1. 체크인
        visited[y][x] = true;
        sb.append(map[y][x]);
        // 2. 목적지에 도달하였는가? -> isEnd, isHit
        if (node.isEnd && node.isHit == false) {
            node.isHit = true;
            sum += score[length];
            count++;
            String foundWord = sb.toString();
            if (compare(answer, foundWord) > 0) {
                answer = foundWord;
            }
        }
        // 3. 연결된 곳을 순회 -> 8방
        for (int i = 0; i < 8; i++) {
            int ty = y + my[i];
            int tx = x + mx[i];
            // 4. 가능한가? -> map 경계, 방문하지 않았는지, node가 해당 자식을 가지고 있는지
            if (0 <= ty && ty < 4 && 0 <= tx && tx < 4) {
                if (visited[ty][tx] == false && node.hasChild(map[ty][tx])) {
                    // 5. 간다.
                    search(ty, tx, length + 1, node.getChild(map[ty][tx]));
                }
            }
        }

        // 6. 체크아웃
        visited[y][x] = false;
        sb.deleteCharAt(length - 1);
    }

    // 5. 간다.
    static int compare(String arg0, String arg1) {
        int result = Integer.compare(arg1.length(), arg0.length());
        if (result == 0) {
            return arg0.compareTo(arg1);
        } else {
            return result;
        }
    }

    static void insertTrieNode(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if (current.hasChild(word.charAt(i)) == false) {
                current.children[word.charAt(i) - 'A'] = new TrieNode();
            }
            current = current.getChild(word.charAt(i));
        }
        current.isEnd = true;
    }
}


class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
    boolean isHit;

    boolean hasChild(char c) {
        return children[c - 'A'] != null;
    }

    TrieNode getChild(char c) {
        return children[c - 'A'];
    }

    void clearHit(){
        isHit =false;

        for (int i = 0; i < children.length; i++) {
            TrieNode child = children[i];
            if (child != null) {
                child.clearHit();
            }
        }
    }
}