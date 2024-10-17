package SORT.Bubble.Test;


/**
 * 거품 정렬(Bubble Sort)
 * 거품이 보글보글 올라가는 것처럼 인접한 두 원소를 (처음부터) 비교하여 자리 교환
 * **/

import java.util.*;
import java.io.*;

public class Main {
    static int[] nums = {8, 4, 9, 3, 1, 5, 6, 7, 2};
    public static void main(String[] args) throws Exception {

        /** 정렬 전 **/
        /** [8, 4, 9, 3, 1, 5, 6, 7, 2] **/
        System.out.println(Arrays.toString(nums));

        /** 오름차순 정렬 **/
        for(int i = 0; i < nums.length; i++){
            // 한번 크게 돌 때마다 제일 큰 숫자는 맨 뒤에 위치한다.
            // 그러므로 nums.length - i만큼 돌아야 한다.
            for(int j = 1; j < nums.length - i; j++){
                if(nums[j-1] > nums[j]){
                    swap(j-1, j);
                }
            }
        }

        /** 정렬 후 **/
        /** [1, 2, 3, 4, 5, 6, 7, 8, 9] **/
        System.out.println(Arrays.toString(nums));
    }

    static void swap(int a, int b){
        int tmp;
        tmp = nums[a];
        nums[a]=nums[b];
        nums[b] = tmp;
    }


}
