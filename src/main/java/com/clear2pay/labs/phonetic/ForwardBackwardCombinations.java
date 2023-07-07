package com.clear2pay.labs.phonetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ForwardBackwardCombinations {

    private static final String SWIFT_NAME = "Mohamed M Mahmoud";
    private static final String CORE_BANKING_NAME = "Mohamed Mahmoud";
    static int count = 0;

    public static void main(String[] args) {
        List<String> nameDT1 = splitStringInto(SWIFT_NAME);
        List<String> nameDT2 = splitStringInto(CORE_BANKING_NAME);

        int countDT1BeforeMatch = nameDT1.size();
        int countDT2BeforeMatch = nameDT2.size();

        List<String> combinationsDT1 = new ArrayList<String>();
        permutation(0, nameDT1, combinationsDT1);

        for(String comb: combinationsDT1) {
            System.out.println(comb);
        }

        count = 0;
        List<String> combinationsDT2 = new ArrayList<String>();
        permutation(0, nameDT2, combinationsDT2);

        int n1Total = combinationsDT1.size();
        int n2Total = combinationsDT2.size();

        match(combinationsDT1, combinationsDT2, nameDT1, nameDT2);

        int n1NotMatchingCount = combinationsDT1.size();
        int n2NotMatchingCount = combinationsDT2.size();

        int countDT1AfterMatch = nameDT1.size();
        int countDT2AfterMatch = nameDT2.size();

        double percentage = ((((double)(countDT1BeforeMatch - countDT1AfterMatch)/countDT1BeforeMatch))*100 +
                (((double)(countDT2BeforeMatch - countDT2AfterMatch)/countDT2BeforeMatch))*100)/2;

        System.out.println(percentage);
    }

    private static List<String> splitStringInto(String name) {
        return new ArrayList(Arrays.asList(name.split("[ ]")));
    }

    private static List<String> permutation(int start, List<String> name, List<String> combinationsDT1) {
        String temp = "";
        if (start != 0) {
            for (int i = 0; i < start; i++) {
                temp = temp + name.get(i);
            }
            combinationsDT1.add(temp);
            temp = "";
            count++;
        }

        for (int i = start; i < name.size(); i++) {
            swap(name, start, i);
            permutation(start + 1, name, combinationsDT1);
            swap(name, start, i);
        }

        return combinationsDT1;
    }

    private static void swap(List<String> name, int pos1, int pos2) {
        String temp = name.get(pos1);
        name.set(pos1, name.get(pos2));
        name.set(pos2, temp);
    }

    private static void match(List<String> combinationsDT1, List<String> combinationsDT2, List<String> nameDT1, List<String> nameDT2) {
        Iterator<String> dt1 = combinationsDT1.iterator();

        while (dt1.hasNext()) {
            String name1 = dt1.next();
            Iterator<String> dt2 = combinationsDT2.iterator();
            while (dt2.hasNext()) {
                String name2 = dt2.next();

                if (name1.equalsIgnoreCase(name2)) {
//					dt1.remove();
//					dt2.remove();

                    nameDT1.remove(name1);
                    nameDT2.remove(name2);
                }
            }
        }
    }
}
