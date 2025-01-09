//package P17136;
//
//import java.util.*;
//import java.io.*;
//
//
//// 다섯 종류의 색종이가 있다. 각 색종이는 5개 있다.
//	// 1x1, 2x2, 3x3, 4x4, 5x5
//// 크기가 10x10 인 종이 위에 색종이를 덮으려 한다.
//	// 종이에는 0 또는 1이 적혀 있고 1이 적힌 칸은 모두 색종이로 덮여져야 한다.
//// 색종이를 붙일 때 경계 밖은 안됨, 겹쳐도 안됨, 칸의 경계와 일치하게 붙여야 한다.
//// 종이가 주어졌을 때 1이 적힌 모든 칸을 붙이는데 필요한 색조이의 최소 개수를 구해보자.
//// 1을 모두 덮는 것이 불가능하면 -1을 출력하자.
//// 순방향으로 종이 크기가 큰 애들부터 갔다 대는데 이게 잘못될 케이스가 있는가?
//
//
//public class Main {
//
//	static int[][] paper;
//	static int[] colorPaperNum = {0, 5, 5, 5, 5, 5};
//	static int blankCount;
//	public static void main(String[] args) throws Exception {
//
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//		paper = new int[10][10];
//		blankCount = 0;
//		StringTokenizer st;
//
//		int value;
//		for(int i = 0; i < 10; i++) {
//			st = new StringTokenizer(br.readLine());
//			for(int j = 0; j < 10; j++) {
//				value = Integer.parseInt(st.nextToken());
//				paper[i][j] = value;
//				if(value == 1) {
//					blankCount += 1;
//				}
//			}
//		}
//
//		for(int size = 5; size > 0; size--) {
//			for(int i = 0; i < 10; i++) {
//				for(int j = 0; j < 10; j++) {
//					if(blankCount == 0) {
//						System.out.println(countColorPaper());
//						return;
//					}
//					if(paper[i][j] == 1) {
//						checkColorPaper(i, j, size);
//					}
//				}
//			}
//		}
//
//
//		if(blankCount == 0) {
//			System.out.println(countColorPaper());
//		} else {
//			System.out.println(-1);
//		}
//
//
//
//	}
//
//
//	static void checkColorPaper(int i, int j, int size) {
//		if(colorPaperNum[size] == 0) {
//			return;
//		}
//
//
//		for(int k = 0; k < size; k++) {
//			for(int l = 0; l < size; l++) {
//				if((i + k >= 10) || (j + l >= 10)) {
//					return;
//				}
//				if(paper[i+k][j+l] == 0) {
//					return;
//				}
//			}
//		}
//
//		for(int k = 0; k < size; k++) {
//			for(int l = 0; l < size; l++) {
//				paper[i+k][j+l] = 0;
//			}
//		}
//
//		colorPaperNum[size] -= 1;
//		blankCount -= (int) Math.pow(size, 2);
//
//		return;
//	}
//
//
//	static int countColorPaper() {
//		int tmp = 0;
//		for(int i = 0; i < 6; i++) {
//			tmp += colorPaperNum[i];
//		}
//		return 25 - tmp;
//	}
//}



package SamsungA.P17136;

import java.util.*;
import java.io.*;


// 다섯 종류의 색종이가 있다. 각 색종이는 5개 있다.
// 1x1, 2x2, 3x3, 4x4, 5x5
// 크기가 10x10 인 종이 위에 색종이를 덮으려 한다.
// 종이에는 0 또는 1이 적혀 있고 1이 적힌 칸은 모두 색종이로 덮여져야 한다.
// 색종이를 붙일 때 경계 밖은 안됨, 겹쳐도 안됨, 칸의 경계와 일치하게 붙여야 한다.
// 종이가 주어졌을 때 1이 적힌 모든 칸을 붙이는데 필요한 색조이의 최소 개수를 구해보자.
// 1을 모두 덮는 것이 불가능하면 -1을 출력하자.
// 순방향으로 종이 크기가 큰 애들부터 갔다 대는데 이게 잘못될 케이스가 있는가?
    // 있다. 1이 6x6일 때 5x5 한장을 대고 답이 없다고 할 수도 있다.

import java.util.*;
import java.io.*;

public class Main {

    static int[][] paper;
    static int[] colorPaperNum = {0, 5, 5, 5, 5, 5};
    static int blankCount;
    static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        paper = new int[10][10];
        blankCount = 0;

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
                if (paper[i][j] == 1) {
                    blankCount++;
                }
            }
        }

        if (blankCount == 0) {
            System.out.println(0);
        } else {
            solve(0, 0, 0);
            System.out.println(minCount == Integer.MAX_VALUE ? -1 : minCount);
        }
    }

    static void solve(int x, int y, int usedPapers) {
        if (x >= 10) {
            minCount = Math.min(minCount, usedPapers);
            return;
        }

        if (y >= 10) {
            solve(x + 1, 0, usedPapers);
            return;
        }

        if (paper[x][y] == 0) {
            solve(x, y + 1, usedPapers);
            return;
        }

        for (int size = 5; size >= 1; size--) {
            if (canAttach(x, y, size)) {
                attachPaper(x, y, size, 0);
                colorPaperNum[size]--;
                solve(x, y + 1, usedPapers + 1);
                attachPaper(x, y, size, 1); // 다시 원상복귀
                colorPaperNum[size]++;
            }
        }
    }

    static boolean canAttach(int x, int y, int size) {
        if (colorPaperNum[size] == 0) return false;
        if (x + size > 10 || y + size > 10) return false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (paper[x + i][y + j] == 0) return false;
            }
        }
        return true;
    }

    static void attachPaper(int x, int y, int size, int state) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                paper[x + i][y + j] = state;
            }
        }
    }
}

