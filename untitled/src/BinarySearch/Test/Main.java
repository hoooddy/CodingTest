package BinarySearch.Test;

import java.util.*;


/**
 * 이분탐색
 * 찾고자 하는 대상의 범위를 반씩 줄여 나가는 탐색 방법
 * 탐색이 이루어지는 범위는 반드시 정렬되어 있어야 한다.
 * **/

public class Main {

    static int[] nums = {8, 4, 9, 3, 1, 5, 6, 7, 2};

    public static void main(String[] args) {

        int target = 3;
        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));

        System.out.println("Index of Target(" + target + ") is " + binarySearch(target));


    }

    static int binarySearch(int target){
        int low = 0;
        int high = nums.length - 1;
        int mid = (low + high) / 2;

        while(nums[mid] != target){
            if(nums[mid] < target) {
                low = mid+1;
                mid = (low + high) / 2;
            } else if(nums[mid] > target) {
                high = mid - 1;
                mid = (low + high) / 2;
            }
        }
        return mid;



    }

}
