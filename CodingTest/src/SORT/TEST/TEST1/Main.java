package SORT.TEST.TEST1;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<Score> scoreList = new ArrayList<>();

        scoreList.add(new Score(58, 22));
        scoreList.add(new Score(70, 32));
        scoreList.add(new Score(14, 23));
        scoreList.add(new Score(54, 20));
        scoreList.add(new Score(23, 20));
        scoreList.add(new Score(62, 67));
        scoreList.add(new Score(42, 20));
        scoreList.add(new Score(52, 32));
        scoreList.add(new Score(42, 11));
        scoreList.add(new Score(54, 63));
        scoreList.add(new Score(62, 56));

        for(int i = 0; i<scoreList.size(); i++){
            System.out.println(scoreList.get(i).toString());
        }

        Collections.sort(scoreList);
        System.out.println("====================");

        for(int i = 0; i<scoreList.size(); i++){
            System.out.println(scoreList.get(i).toString());
        }

        Collections.reverse(scoreList);
        System.out.println("====================");

        for(int i = 0; i<scoreList.size(); i++){
            System.out.println(scoreList.get(i).toString());
        }

//        Collections.sort(scoreList,)


    }

}




class Score implements Comparable<Score> {
    int english;
    int math;

    Score(int english, int math){
        this.english = english;
        this.math = math;
    }

    @Override
    public String toString() {
        return "english: " + english + " math: " + math;
    }

    @Override
    public int compareTo(Score o){
        int comp1 = o.english - this.english;
        if(comp1 == 0){
            return o.math - this.math;
        } else {
            return comp1;
        }
    }

}
