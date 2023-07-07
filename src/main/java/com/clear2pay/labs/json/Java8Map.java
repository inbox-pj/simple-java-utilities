package com.clear2pay.labs.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Java8Map {

    private static Consumer<StringBuffer> supplier = (narrative) -> {
        if (narrative.length() > 5) {
            narrative.delete(5, narrative.length());
        }
    };

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        List<CustomerRelationSignatory> signatory = new ArrayList<>();
        signatory.add(new CustomerRelationSignatory("8", "7"));
        signatory.add(new CustomerRelationSignatory("8", "2"));
        signatory.add(new CustomerRelationSignatory("8", "9"));
        signatory.add(new CustomerRelationSignatory("8", "4"));

        List<NarrowedCustomerRelation> employeRise = signatory.stream()
                .map(emp -> new NarrowedCustomerRelation().firstName(emp.getFirstName()).middleInitial(emp.getMiddleInitial()).lastName(emp.getLastName())
                        .matchedPercentage(Double.valueOf(emp.getFirstName() + emp.getLastName() + "." + emp.getFirstName() + emp.getLastName())))
                .collect(Collectors.toList());

        String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeRise);
        System.out.println("[3]\t\t " + result);

        NarrowedCustomerRelation[] json = objectMapper.readValue(result, NarrowedCustomerRelation[].class);
        System.out.println("[4]\t\t " + json);

        NarrowedCustomerRelation object = Arrays.stream(json)
                .max(Comparator.comparingDouble(NarrowedCustomerRelation::getMatchedPercentage))
                .orElseThrow(NoSuchElementException::new);

        System.out.println(object);
        
        StringBuffer s = new StringBuffer("aadfdrChargessfasdf");
        supplier.accept(s);

        System.out.println(s.toString());

    }
}
