package com.clear2pay.labs.phonetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ForwardOnlyCombinations {

    private static final Pattern pattern = Pattern.compile("[^,]*,");

    private static final String SWIFT_NAME = "Amit Kumar Singh";
    private static final String CORE_BANKING_NAME = "Amit Kapoor";


    public static void main(String[] args) {
        System.out.println(new ForwardOnlyCombinations().percentage(SWIFT_NAME, CORE_BANKING_NAME));
    }

    public double percentage(String swiftName, String coreName) {
        //swiftName = swiftName.replaceAll("[().'/&+'']", "").replaceAll("[-]", " ").replaceAll("\\s{2,}", " ").trim();
        //coreName = coreName.replaceAll("[.,/()&+']", "").replaceAll("[-]", " ");

        List<String> nameDT1 = splitStringInto(swiftName);
        List<String> nameDT2 = splitStringInto(coreName);

        int countDT1BeforeMatch = nameDT1.size();
        int countDT2BeforeMatch = nameDT2.size();

        // get all combinations
        Combinations combinations1 = new Combinations();
        for(int i=1; i<=nameDT1.size(); i++) {
            combination(combinations1, nameDT1, i, "");
        }

//        for(Combination comb: combinations1.combinations) {
//            System.out.println(comb.toString());
//        }

//        System.out.println("---------------------------");

        Combinations combinations2 = new Combinations();
        for(int i=1; i<=nameDT2.size(); i++) {
            combination(combinations2, nameDT2, i, "");
        }

//        for(Combination comb: combinations2.combinations) {
//            System.out.println(comb.toString());
//        }

        match(combinations1, combinations2, nameDT1, nameDT2);

        int countDT1AfterMatch = nameDT1.size();
        int countDT2AfterMatch = nameDT2.size();

        double percentage = ((((double)(countDT1BeforeMatch - countDT1AfterMatch)/countDT1BeforeMatch))*100 +
                (((double)(countDT2BeforeMatch - countDT2AfterMatch)/countDT2BeforeMatch))*100)/2;

        //System.out.println(percentage);

        return percentage;

    }

    private static List<String> splitStringInto(String name) {
        return new ArrayList<String>(Arrays.asList(name.split("[ ]")));
    }

    public static void combination(Combinations combinations, List<String> e, int k, String accumulated) {
        if(!StringUtils.isEmpty(accumulated)) {
            accumulated = accumulated + ",";
        }

        // 1. stop
        if (e.size() < k) {
            return;
        }

        // 2. add each element in e to accumulated
        if (k == 1) {
            for (String s : e) {
                Combination comb1 = new Combination(accumulated +s, accumulated +s, countOccurances(accumulated +s, ",")+1);
                combinations.add(comb1);

                /*if(!StringUtils.isEmpty(accumulated)) {
                    String swap = swap(accumulated.replaceAll("[,]" , ""), s);
                    Combination comb2 = new Combination(swap, accumulated +s, countOccurances(accumulated +s, ",")+1);
                    combinations.add(comb2);
                }*/

            }
        }

        // 3. add all elements in e to accumulated
        else if (e.size() == k) {
            int i = 1;
            for (String s : e) {
                accumulated = accumulated +s;
                if(i != e.size()) {
                    accumulated = accumulated + ",";
                }
                i++;
            }

            Combination comb = new Combination(accumulated, accumulated, countOccurances(accumulated, ",")+1);
            combinations.add(comb);
        }

        // 4. for each element, call combination
        else if (e.size() > k) {
            for (int i = 0; i < e.size(); i++) {
                combination(combinations, e.subList(i + 1, e.size()), k - 1, accumulated + e.get(i));
            }
        }
    }

    private static void match(Combinations combinationsDT1, Combinations combinationsDT2, List<String> nameDT1,
            List<String> nameDT2) {
        Iterator<Combination> dt1 = combinationsDT1.combinations.iterator();

        boolean matchFound = false;
        while (dt1.hasNext()) {

            Combination name1 = dt1.next();
            Iterator<Combination> dt2 = combinationsDT2.combinations.iterator();
            while (dt2.hasNext()) {
                Combination name2 = dt2.next();

                if (name1.compareTo(name2) == 0) {
                    matchFound = !matchFound;
                    removeAll(nameDT1, name1.word);
                    removeAll(nameDT2, name2.word);
                }
            }
        }
    }

    private static int countOccurances(String value, String key) {
        Matcher matcher = pattern.matcher(value);
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count;
    }

//    private static String swap(String accumulated, String str2) {
//        return str2 + "," + accumulated;
//    }

    private static void removeAll(List<String> list, String element) {
        list.removeIf(n -> Objects.equals(n, element));
    }
}
