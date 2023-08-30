package com.clover.labs.phonetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ForwardOnlyCombinationsOPF {

    private static final String SWIFT_NAME = "David Lloyd George";
    private static final String CORE_BANKING_NAME = "David Lloyd George";

    private static final ForwardOnlyCombinationsOPF ALGO = ForwardOnlyCombinationsOPF.getInstance();

    public static void main(String[] args) {
        //String[] sanatizedName = ALGO.sanatizeNames("ADIBBankGroup", SWIFT_NAME, CORE_BANKING_NAME);

        //System.out.println(ALGO.splitString(sanatizedName[0].trim(),"[ ]").length == 1 );

        System.out.println(ALGO.getMatchingPercentage("Amit Kapoor", "Amit Kumar Singh"));

    }


    private static final Pattern pattern = Pattern.compile("[^,]*,");

    private static ForwardOnlyCombinationsOPF instance = null;

    private ForwardOnlyCombinationsOPF() {
    }

    public static ForwardOnlyCombinationsOPF getInstance() {
        if (instance == null)
            instance = new ForwardOnlyCombinationsOPF();

        return instance;
    }

    public String[] sanatizeNames(String bankGroupId, String swiftName, String coreName) {
        //1.1 get the exclusion single chars pattern from Paraneter
     /*   String pattern = getExclusionPattern(bankGroupId);
        if (pattern.contains(ADIBValidationCnsts.SPECIAL_CHAR)) {
            pattern = replaceAllString(pattern,ADIBValidationCnsts.SPECIAL_CHAR, "");
            swiftName = replaceAllString(swiftName,"[" + ADIBValidationCnsts.SPECIAL_CHAR + "]", "");
            coreName = replaceAllString(coreName,"[" + ADIBValidationCnsts.SPECIAL_CHAR + "]", "");
      *//*  }*/

        //1.2 remove extra whitespace from name
        swiftName = replaceAllString(swiftName,"\\s{2,}", " ").trim();
        coreName = replaceAllString(coreName,"\\s{2,}", " ").trim();

        /*//1.2. replace the single name pattern along with extra whitespace from names
        if (!StringUtils.isEmpty(pattern)) {
            swiftName = replaceAllString(swiftName,"[" + pattern + "]", "");
            coreName = replaceAllString(coreName,"[" + pattern + "]", "");
        }

        List<String> salutationSet = getSalutation(bankGroupId);

        //1.4 replace the multi-word from names
        if (salutationSet != null) {
            for (String key : salutationSet) {
                if (key != null && swiftName.startsWith(key)) {
                    swiftName = swiftName.substring(key.length()).trim();
                }
                if (key != null && coreName.startsWith(key)) {
                    coreName = coreName.substring(key.length()).trim();
                }
            }
        }*/

        return new String[] { swiftName, coreName };
    }

    public double getMatchingPercentage(String swiftName, String coreName) {

        //2. split string into words based on spaces
        List<String> swiftNameWordList = splitStringInto(swiftName);
        List<String> coreNameWordList = splitStringInto(coreName);

        //3. get the word count before match
        int swiftNameCountBeforeMatch = swiftNameWordList.size();
        int coreNameCountBeforeMatch = coreNameWordList.size();

        //4.1 get all the combination for swiftName
        Combinations swiftNameCombinations = new Combinations();
        for (int i = 1; i <= swiftNameWordList.size(); i++) {
            combination(swiftNameCombinations, swiftNameWordList, i, "");
        }

        //4.2 get all the combination for coreName
        Combinations coreNameCombinations = new Combinations();
        for (int i = 1; i <= coreNameWordList.size(); i++) {
            combination(coreNameCombinations, coreNameWordList, i, "");
        }

        //5. match the combinations
        match(swiftNameCombinations, coreNameCombinations, swiftNameWordList, coreNameWordList);

        //6. calculate the matching percentage
        int swiftNameCountAfterMatch = swiftNameWordList.size();
        int coreNameCountAfterMatch = coreNameWordList.size();

        double percentage = ((((double) (swiftNameCountBeforeMatch - swiftNameCountAfterMatch) / swiftNameCountBeforeMatch)) * 100 +
                (((double) (coreNameCountBeforeMatch - coreNameCountAfterMatch) / coreNameCountBeforeMatch)) * 100) / 2;

        return percentage;
    }

    private void match(Combinations swiftNameCombinations, Combinations coreNameCombinations, List<String> swiftNameWordList, List<String> coreNameWordList) {
        Iterator<Combination> dt1 = swiftNameCombinations.combinations.iterator();

        boolean matchFound = false;
        while (dt1.hasNext()) {

            Combination name1 = dt1.next();
            Iterator<Combination> dt2 = coreNameCombinations.combinations.iterator();
            while (dt2.hasNext()) {
                Combination name2 = dt2.next();

                if (name1.compareTo(name2) == 0) {
                    matchFound = !matchFound;
                    removeAll(swiftNameWordList, name1.word);
                    removeAll(coreNameWordList, name2.word);
                }
            }
        }
    }

    private String getPattern(Set<String> exclusionKeys) {
        StringBuilder pattern = new StringBuilder();
        exclusionKeys.stream().forEach(key -> pattern.append(key));
        return pattern.toString();
    }

    private void removeAll(List<String> list, String element) {
        list.removeIf(n -> Objects.equals(n, element));
    }

    public void combination(Combinations combinations, List<String> e, int k, String accumulated) {
        if (!StringUtils.isEmpty(accumulated)) {
            accumulated = accumulated + ",";
        }

        // 3.1.1. stop
        if (e.size() < k) {
            return;
        }

        // 3.1.2. add each element in e to accumulated
        if (k == 1) {
            for (String s : e) {
                Combination comb1 = new Combination(accumulated + s, accumulated + s, countOccurances(accumulated + s, ",") + 1);
                combinations.add(comb1);
            }
        }

        // 3.1.3. add all elements in e to accumulated
        else if (e.size() == k) {
            int i = 1;
            for (String s : e) {
                accumulated = accumulated + s;
                if (i != e.size()) {
                    accumulated = accumulated + ",";
                }
                i++;
            }

            Combination comb = new Combination(accumulated, accumulated, countOccurances(accumulated, ",") + 1);
            combinations.add(comb);
        }

        // 3.1.4. for each element, call combination
        else if (e.size() > k) {
            for (int i = 0; i < e.size(); i++) {
                combination(combinations, e.subList(i + 1, e.size()), k - 1, accumulated + e.get(i));
            }
        }
    }

    private int countOccurances(String value, String key) {
        Matcher matcher = pattern.matcher(value);
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count;
    }

    private String replaceAllString(String text, String regEx, String replaceWith) {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        String replacedText = m.replaceAll(replaceWith);
        return replacedText;
    }

    private List<String> splitStringInto(String name) {
        //return new ArrayList<String>(Arrays.asList(name.split("[ ]")));
        Pattern pattern= Pattern.compile(Pattern.quote("[ ]"));
        return new ArrayList<String>(Arrays.asList(pattern.split(name)));
    }

    public String[] splitString(String var1, String var2) {
        Pattern pattern= Pattern.compile(var2);
        return pattern.split(var1);
    }
}
