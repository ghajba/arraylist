package hu.hajba.arraylist.misc;

import hu.hajba.arraylist.CircularArrayList;
import hu.hajba.arraylist.PlainArrayList;

import java.util.*;

public class PerformanceComparison {

    private static final int LIST_ELEMENT_COUNT = 100_000;
    private static final int TEST_TIME_MS = 1000;
    private static final int CYCLE_COUNT = 1_000;
    private static final int TEST_RUN_COUNT = 10;

    static void main(String[] args) {
        List<List<Integer>> lists = Arrays.asList(
                new ArrayList<>(),
                new PlainArrayList<>(),
                new CircularArrayList<>());

        lists.forEach(PerformanceComparison::fillList);

        System.out.println("-------");

        List<List<Integer>> sizedLists = Arrays.asList(
                new ArrayList<>(LIST_ELEMENT_COUNT),
                new PlainArrayList<>(LIST_ELEMENT_COUNT),
                new CircularArrayList<>(LIST_ELEMENT_COUNT));

        sizedLists.forEach(PerformanceComparison::fillList);

        System.out.println("-------");

        for (int i = 0; i < 10; i++) {
            sizedLists.forEach(PerformanceComparison::testPerformance);
        }

        System.out.println("-------");
        System.out.println("-------");

        List<List<Integer>> emptyLists = Arrays.asList(
                new ArrayList<>(),
                new PlainArrayList<>(),
                new CircularArrayList<>());

        for (int i = 0; i < TEST_RUN_COUNT; i++) {
            emptyLists.forEach(PerformanceComparison::testPerformance);
        }

    }

    private static void fillList(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < LIST_ELEMENT_COUNT; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.printf("List %s initialized in %d ms\n", list.getClass(), end - start);
    }

    private static void testPerformance(List<Integer> list) {
        long start = System.currentTimeMillis();

        long cycles = 0;
        while (System.currentTimeMillis() - start < TEST_TIME_MS) {
            for (int i = 0; i < CYCLE_COUNT; i++) {
                list.add(42);
                list.remove(0);
                cycles++;
            }
        }

        long end = System.currentTimeMillis();

        System.out.printf(Locale.US, "%,d cycles done in %d ms with %s%n", cycles, end - start, list.getClass());
    }
}
