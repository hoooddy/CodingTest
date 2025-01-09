package SORT.Quick.Test;

import java.util.*;

/**
 * 퀵 정렬(Quick Sort)
 * 분할 정복 을 통해 주어진 배열을 정렬
 * 분할 정복: 문제를 작은 2개의 문제로 분리하고 각각을 해결 후 결과를 모아서 원래 문제를 해결
 *
 *
 **/

public class Main {

    static int[] nums = {8, 4, 9, 3, 1, 5, 6, 7, 2, 5, 8};


    public static void main(String[] args) {

        /** 정렬 전 **/
        /** [8, 4, 9, 3, 1, 5, 6, 7, 2, 5, 8] **/
        System.out.println(Arrays.toString(nums));

        /** 오름차순 정렬 **/


        /** 정렬 후 **/
        /** [1, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9] **/
        System.out.println(Arrays.toString(nums));
    }

}
