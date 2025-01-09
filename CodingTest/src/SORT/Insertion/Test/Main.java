package SORT.Insertion.Test;

import java.util.*;

/**
 * 삽입 정렬(Insertion Sort)
 * 순차적으로 돌며 앞의 원소들과 비교하여 삽입될 위치를 선정
 * 카드 정렬을 떠올려보자
 **/

public class Main {

    static int[] nums = {8, 4, 9, 3, 1, 5, 6, 7, 2, 5, 8};

    public static void main(String[] args){

        /** 정렬 전 **/
        /** [8, 4, 9, 3, 1, 5, 6, 7, 2, 5, 8] **/
        System.out.println(Arrays.toString(nums));

        /** 오름차순 정렬 **/
        for(int i = 1; i < nums.length; i++){
            int tmp = nums[i];
            for(int j = i; j > 0; j--){
                if(tmp < nums[j-1]){
                    nums[j] = nums[j-1];
                    if(j==1){
                        nums[j-1] = tmp;
                    }
                } else {
                    nums[j] = tmp;
                    break;
                }
            }
        }

        /** 정렬 후 **/
        /** [1, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9] **/
        System.out.println(Arrays.toString(nums));
    }
}
