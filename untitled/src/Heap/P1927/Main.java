package Heap.P1927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


//public class Main {
//
//    static int N;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        N = Integer.parseInt(br.readLine());
//        MinHeap mh = new MinHeap();
//
//
//        for (int i = 0; i < N; i++) {
//            int input = Integer.parseInt(br.readLine());
//            if (input == 0) {
//                System.out.println(mh.delete());
//            } else {
//                mh.insert(input);
//            }
//        }
//    }
//}
//
//class MinHeap {
//    List<Integer> list;
//
//    public MinHeap() {
//        list = new ArrayList<>();
//        list.add(0);
//    }
//
//    public void insert(int val) {
//        // 1. leaf 마지막에 삽입
//        list.add(val);
//
//        int current = list.size() - 1;
//        int parent = current / 2;
//        // 2. 부모와 비교 후 조건에 맞지 않으면 SWAP
//        // 3. 조건이 만족되거나 root  까지 반복
//
//        while (true) {
//            if(parent == 0 || list.get(parent) <= list.get(current)){
//                break;
//            }
//
//            int temp = list.get(parent);
//            list.set(parent, list.get(current));
//            list.set(current, temp);
//
//            current = parent;
//            parent = current / 2;
//        }
//    }
//
//    public int delete(){
//        if (list.size() == 1){
//            return 0;
//        }
//
//        int top = list.get(1);
//        // 1. Root에 leaf 마지막 데이터 가져옴
//        list.set(1, list.get(list.size() - 1));
//        list.remove(list.size() - 1);
//
//        // 2. 자식과 비교 후 조건이 맞지 않으면 swap
//        // 3. 조건이 만족되거나 leaf까지 반복
//        int currentPos = 1;
//        while (true) {
//            int leftPos = currentPos * 2;
//            int rightPos = currentPos * 2 + 1;
//
//            // 자식 존재 유무 확인
//            if (leftPos >= list.size()) {
//                break;
//            }
//
//            // 왼쪽 자식 먼저 확인
//            int minValue = list.get(leftPos);
//            int minPos = leftPos;
//
//            // 오른쪽 자식 확인
//            if (rightPos < list.size() && list.get(rightPos) < minValue){
//                minValue = list.get(rightPos);
//                minPos = rightPos;
//            }
//
//            // Swap
//            if (list.get(currentPos) > minValue) {
//                int temp = list.get(currentPos);
//                list.set(currentPos, minValue);
//                list.set(minPos, temp);
//                currentPos = minPos;
//            } else {
//                break;
//            }
//        }
//        return top;
//    }
//}

public class Main {
    static int N;

    static int[] minHeap;

    static int lastIndex;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        minHeap = new int[N + 1];
        lastIndex = 0;

        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(br.readLine());
            if (value != 0) {
                insert(value);
            } else {
                printAndDelete();
            }
        }
    }

    static void insert(int value) {
        minHeap[++lastIndex] = value;

        int tmpIndex = lastIndex;
        int tmpValue;
        while (tmpIndex / 2 != 0) {
            if (minHeap[tmpIndex / 2] > minHeap[tmpIndex]) {
                tmpValue = minHeap[tmpIndex / 2];
                minHeap[tmpIndex / 2] = minHeap[tmpIndex];
                minHeap[tmpIndex] = tmpValue;
            }

            tmpIndex /= 2;
        }
    }

    static void printAndDelete() {
        System.out.println(minHeap[1]);

        minHeap[1] = minHeap[lastIndex];
        minHeap[lastIndex] = 0;

        if(lastIndex > 0){
            lastIndex--;
        }

        int tmpIndex = 1;
        int targetIndex;
        while ((tmpIndex * 2 <= lastIndex) && lastIndex != 1) {
            if (minHeap[tmpIndex * 2] <= minHeap[tmpIndex * 2 + 1]) {
                if(minHeap[tmpIndex * 2] == 0){
                    break;
                }
                targetIndex = tmpIndex * 2;
            } else {
                if(minHeap[tmpIndex * 2 + 1] == 0){
                    if(minHeap[tmpIndex * 2] == 0){
                        break;
                    } else{
                        targetIndex = tmpIndex * 2;
                    }
                } else {
                    targetIndex = tmpIndex * 2 + 1;
                }
            }

            if (minHeap[targetIndex] <= minHeap[tmpIndex]) {
                swap(targetIndex, tmpIndex);
            }

            tmpIndex = targetIndex;
        }

    }

    static void swap(int a, int b){
        int tmpValue;
        tmpValue = minHeap[b];
        minHeap[b] = minHeap[a];
        minHeap[a] = tmpValue;
    }
}
