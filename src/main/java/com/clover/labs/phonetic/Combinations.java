package com.clover.labs.phonetic;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    List<Combination> combinations;

    public Combinations add(Combination combination) {
        if(combinations == null) {
            combinations = new ArrayList<Combination>();
        }
        this.combinations.add(combination);

        return this;
    }

}
