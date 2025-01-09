package SORT.TEST;

import java.util.*;

public class Main {

    public static void main(String args[]){
        Item item1 = new Item(1, 3, 1);
        Item item2 = new Item(1, 2, 3);
        Item item3 = new Item(1, 1, 2);

        List<Item> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);

        // 1: 출력
        System.out.println("1: " + list);

        // 2: Item 클래스의 compareTo 메서드를 기준으로 정렬
        // a 기준 내림차순, a가 같다면 b 기준 오름차순
        Collections.sort(list);
        System.out.println("2: " + list);

        // 3: Item 클래스에 compareTo 메서드가 있어도 아래의 compare 기준으로 정렬
        // c 기준 오름차순
        Collections.sort(list, new Comparator<Item>(){
            @Override
            public int compare(Item o1, Item o2){
                return Integer.compare(o1.c, o2.c);
            }
        });
        System.out.println("3: " + list);

        // 4: getter가 있어야 사용 가능
        // b 기준 오름차순
        Collections.sort(list, Comparator.comparingInt(Item::getB));
        System.out.println("4: " + list);

        // 5: getter가 있어야 사용 가능
        // b 기준 내림차순, a 기준 오름차순
        Collections.sort(list, Comparator.comparingInt(Item::getB).reversed().thenComparingInt(Item::getA));
        System.out.println("5: " + list);
    }
}

class Item implements Comparable<Item> {
    int a;
    int b;
    int c;

    public Item(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString(){
        return "{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                "}";
    }

    // 음수 : -
    // 0 : -
    // 양수 : Swap
    @Override
    public int compareTo(Item o) {
        int comp1 = Integer.compare(o.a, a);
        if(comp1 == 0){
            return Integer.compare(b, o.b);
        } else {
            return comp1;
        }

//        if(a > o.a) {
//            return -1;
//        } else if(a == o.a){
//            return 0;
//        } else {
//            return 1;
//        }
    }


    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }
    public int getC() {
        return c;
    }
}
