package com.clear2pay.labs.phonetic;

import org.apache.commons.codec.language.Soundex;

public class Combination implements Comparable<Combination> {

    private Soundex soundex = new Soundex();

    private int numberOfWords;

    String word;
    private String wordCombination;

    private String phoenaticCode;

    public Combination(String word, String wordCombination, int numberOfWords) {
        this.word = word.replaceAll("[,]" , "");
        this.wordCombination = wordCombination;

        this.phoenaticCode = soundex.encode(word);

        this.numberOfWords = numberOfWords;
    }

    @Override
    public String toString() {
        return "Combination{" +
                "  numberOfWords=" + numberOfWords +
                ", word='" + word + '\'' +
                ", wordCombination='" + wordCombination + '\'' +
                ", phoenaticCode='" + phoenaticCode + '\'' +
                '}';
    }

    @Override
    public int compareTo(Combination o) {
        if(this.word.equalsIgnoreCase(o.word) || this.phoenaticCode.equalsIgnoreCase(o.phoenaticCode)) {
            return 0;
        }
        return -1;
    }
}
