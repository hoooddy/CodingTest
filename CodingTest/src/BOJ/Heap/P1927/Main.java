package BOJ.Heap.P1927;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


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

// Heap
// 최소 힙은 가장 작은 노드가 root에 위치한다.
// N: 연산의 수
// N개의 연산 정보
// x -> 자연수 -> 값 추가
// x -> 0 -> 가장 작은 값 출력 후 제거
// 배열이 비어 있는데 0이 입력으로 들어온 경우 -> 0을 출력
public class Main {

    static int N;

    static int[] heap;
    static StringBuilder sb = new StringBuilder();
    static int heapEndIndex;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int x;

        // 1 <= N <= 10000이므로 heap의 최대 크기를 100000+1로 지정
        // 인덱스를 1부터 사용하기 위함
        heap = new int[100000+1];
        heapEndIndex = 0;

        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());

            // 값 추가
            if(x != 0) {
                add(x);
            }

            // 최소값 출력 후 제거
            else {
                // 배열이 비어 있는데 0이 입력으로 들어온 경우 -> 0을 출력
                if(heapEndIndex == 0) {
                    sb.append(0 + "\n");
                    continue;
                }
                printAndDelete();
            }
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void add(int x){
        // heap의 맨 끝에 추가하기
        heapEndIndex += 1;

        heap[heapEndIndex] = x;
        if(heapEndIndex == 1) {
            return;
        }

        int tmpIndex = heapEndIndex;

        while(true){
            if(heap[tmpIndex/2] > heap[tmpIndex]) {
                switchNode(tmpIndex/2, tmpIndex);
                tmpIndex /= 2;
            } else {
                break;
            }
        }
    }

    static void printAndDelete(){
        sb.append(heap[1] + "\n");

        heap[1] = heap[heapEndIndex];
        heap[heapEndIndex] = 0;

        if(heapEndIndex >= 1) {
            heapEndIndex -= 1;
        }

        int tmpIndex = 1;
        int targetIndex;
        while (true) {
            if(heapEndIndex == 1){
                break;
            }

            if(tmpIndex * 2 > heapEndIndex){
                break;
            }

            if(heap[tmpIndex * 2] == 0 && heap[tmpIndex * 2 + 1] == 0) {
                break;
            }

            // 왼쪽 노드 검사
            if(heap[tmpIndex * 2] < heap[tmpIndex * 2 + 1]){
                if(heap[tmpIndex * 2] == 0) {
                    targetIndex =  tmpIndex * 2 + 1;
                } else {
                    targetIndex = tmpIndex * 2;
                }
            }

            // 오른쪽 노드 검사
            else {
                if(heap[tmpIndex * 2 + 1] == 0) {
                    targetIndex = tmpIndex * 2;
                } else {
                    targetIndex =  tmpIndex * 2 + 1;
                }
            }

            if(heap[targetIndex] <= heap[tmpIndex]) {
                switchNode(targetIndex, tmpIndex);
            }

            tmpIndex = targetIndex;

        }
    }

    static void switchNode(int a, int b){
        int tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }
}
