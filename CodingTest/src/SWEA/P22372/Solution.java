package SWEA.P22372;

import java.util.*;
import java.io.*;


// 1. 점이 완전히 직사각형 내부에 있다.
// 2. 점이 직사각형의 네 변 중 적어도 하나의 위에 있다.
// 3. 점이 완전히 직사각형 외부에 있다.
public class Solution {

    static Rectangle rec;
    static StringBuilder sb = new StringBuilder();
    static int inCount;
    static int onCount;
    static int outCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine());



            int x1, y1, x2, y2;
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            rec = new Rectangle(x1, y1, x1, y2, x2, y2, x2, y1);

            st = new StringTokenizer(br.readLine());
            int dotCount = Integer.parseInt(st.nextToken());

            inCount = 0;
            onCount = 0;
            outCount = 0;


            for(int i = 0; i < dotCount; i++){
                st = new StringTokenizer(br.readLine());
                check(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            sb.append("#");
            sb.append(test_case);
            sb.append(" ");
            sb.append(inCount);
            sb.append(" ");
            sb.append(onCount);
            sb.append(" ");
            sb.append(outCount);
            sb.append("\n");


            inCount = 0;
            onCount = 0;
            outCount = 0;
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void check(int x, int y){
        if(x > rec.leftDownX
        && y > rec.leftDownY
        && x > rec.leftUpX
        && y < rec.leftUpY
        && x < rec.rightDownX
        && y > rec.rightDownY
        && x < rec.rightUpX
        && y < rec.rightUpY
        ){
            inCount += 1;
        }

        else if((x == rec.leftDownX && (y >= rec.leftDownY && y <= rec.leftUpY))
                || (x == rec.rightDownX && (y>= rec.rightDownY && y <= rec.rightUpY))
                || (y == rec.rightDownY && (x >= rec.rightDownX && x <= rec.rightDownX))
                || (y == rec.leftUpY && (x >= rec.rightDownX && x <= rec.rightDownX))) {
            onCount += 1;
        }

        else {
            outCount += 1;
        }
    }
}

class Rectangle{
    int leftUpX;
    int leftUpY;

    int leftDownX;
    int leftDownY;

    int rightUpX;
    int rightUpY;

    int rightDownX;
    int rightDownY;


    Rectangle(int leftDownX, int leftDownY,
              int leftUpX, int leftUpY,
              int rightUpX, int rightUpY,
              int rightDownX, int rightDownY){
        this.leftUpX = leftUpX;
        this.leftUpY = leftUpY;

        this.leftDownX = leftDownX;
        this.leftDownY = leftDownY;

        this.rightUpX = rightUpX;
        this.rightUpY = rightUpY;

        this.rightDownX = rightDownX;
        this.rightDownY = rightDownY;
    }
}
