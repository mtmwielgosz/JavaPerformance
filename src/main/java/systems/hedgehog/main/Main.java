package systems.hedgehog.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        List<List<Integer>> linkedList = new LinkedList<>();
        for (int j = 0; j < 1000; j++) {
            long currentTimeMilli = new Date().getTime();
            for (int i = 0; i < 50000; i++) {
                int a = i * i;
                int b = Math.abs(a);
                a = b * a * i;
                list.add(a);
                linkedList.add(list);
            }
            list.clear();
            linkedList.clear();
            long nowTime = new Date().getTime() - currentTimeMilli;
            System.out.println(j + ", time> " + nowTime);
        }
    }
}