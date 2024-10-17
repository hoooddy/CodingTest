package Heap.P11279;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;

    static int[] maxHeap;

    static int lastIndex;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        maxHeap = new int[N + 1];
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
        maxHeap[++lastIndex] = value;

        int tmpIndex = lastIndex;
        int tmpValue;
        while (tmpIndex / 2 != 0) {
            if (maxHeap[tmpIndex / 2] < maxHeap[tmpIndex]) {
                tmpValue = maxHeap[tmpIndex / 2];
                maxHeap[tmpIndex / 2] = maxHeap[tmpIndex];
                maxHeap[tmpIndex] = tmpValue;
            }

            tmpIndex /= 2;
        }
    }

    static void printAndDelete() {
        System.out.println(maxHeap[1]);

        maxHeap[1] = maxHeap[lastIndex];
        maxHeap[lastIndex] = 0;

        if(lastIndex > 0){
            lastIndex--;
        }

        int tmpIndex = 1;
        int targetIndex;
        while ((tmpIndex * 2 <= lastIndex) && lastIndex != 1) {

            if (maxHeap[tmpIndex * 2] >= maxHeap[tmpIndex * 2 + 1]) {
                if(maxHeap[tmpIndex * 2] == 0){
                    break;
                }
                targetIndex = tmpIndex * 2;
            } else {
                if(maxHeap[tmpIndex * 2 + 1] == 0){
                    if(maxHeap[tmpIndex * 2] == 0){
                        break;
                    } else{
                        targetIndex = tmpIndex * 2;
                    }
                } else {
                    targetIndex = tmpIndex * 2 + 1;
                }
            }

            if (maxHeap[targetIndex] >= maxHeap[tmpIndex]) {
                swap(targetIndex, tmpIndex);
            }

            tmpIndex = targetIndex;
        }
    }

    static void swap(int a, int b){
        int tmpValue;
        tmpValue = maxHeap[b];
        maxHeap[b] = maxHeap[a];
        maxHeap[a] = tmpValue;
    }
}