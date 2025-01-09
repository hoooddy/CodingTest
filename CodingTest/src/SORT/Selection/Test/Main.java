package SORT.Selection.Test;

import java.util.*;

/**
 * 선택 정렬(Selection Sort)
 * 자리를 선택 놓고 그에 들어갈 원소를 선택해서 swap
 * 예) 0번 인덱스 -> 제일 작은 수
 *     1번 인덱스 -> 그 다음으로 제일 작은 수
 *     ...
 **/


public class Main {

    static int[] nums = {8, 4, 9, 3, 1, 5, 6, 7, 2, 5, 8};

    public static void main(String[] args) {

        /** 정렬 전 **/
        /** [8, 4, 9, 3, 1, 5, 6, 7, 2, 5, 8] **/
        System.out.println(Arrays.toString(nums));

        /** 오름차순 정렬 **/
        for(int i = 0; i < nums.length; i++){
            int minValueIndex = i;
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[minValueIndex] > nums[j]){
                    minValueIndex = j;
                }
            }
            swap(i,minValueIndex);
        }

        /** 정렬 후 **/
        /** [1, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9] **/
        System.out.println(Arrays.toString(nums));
    }

    static void swap(int a, int b){
        int tmp;
        tmp = nums[a];
        nums[a]=nums[b];
        nums[b] = tmp;
    }

}
