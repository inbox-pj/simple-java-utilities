package com.clear2pay.labs.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        List<CustomerRelationSignatory> signatory = new ArrayList<>();
        signatory.add(new CustomerRelationSignatory("8", "1"));
        signatory.add(new CustomerRelationSignatory("8", "2"));
        signatory.add(new CustomerRelationSignatory("8", "3"));
        signatory.add(new CustomerRelationSignatory("8", "4"));

        CustomerRelationSignatory matchingObject = signatory.stream().
                filter(p -> p.getFirstName().equals("11")).
                findFirst()
                .orElse(null);

        System.out.println("[1]\t\t Id :" + matchingObject);

        List<NarrowedCustomerRelation> employeRise = signatory.stream()
                .map(emp -> new NarrowedCustomerRelation().firstName(emp.getFirstName()).middleInitial(emp.getMiddleInitial()).lastName(emp.getLastName()))
                .collect(Collectors.toList());

        employeRise.stream()
                .forEach(s -> System.out.println("[2]\t\t Id :" + s.getFirstName()));

        String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeRise);
        System.out.println("[3]\t\t " + result);

        NarrowedCustomerRelation[] json = objectMapper.readValue(result, NarrowedCustomerRelation[].class);
        System.out.println("[4]\t\t " + json);

        ValidationIssue issues = Arrays.stream(json)
                .map(p -> validate(p)).filter(p -> p.issue.equals("83"))
                .findFirst()
                .orElse(new ValidationIssue("0000"));

        System.out.println("[5]\t\t " + issues);

        ValidationIssue i = StreamSupport.stream(Spliterators
                .spliteratorUnknownSize(signatory.iterator(), 0), false).map(p -> validate1(p)).filter(p -> p.issue.equals("89"))
                .findFirst()
                .orElse(validate2(issues));

        System.out.println("[6]\t\t " + i.issue);



        //Iterate over Map
        Map <String, BigDecimal > values = new HashMap<String, BigDecimal>();
        values.put("1", BigDecimal.valueOf(1));
        values.put("2", BigDecimal.valueOf(2));
        values.put("3", BigDecimal.valueOf(3));
        values.put("4", BigDecimal.valueOf(4));
        values.put("5", BigDecimal.valueOf(5));

        boolean valid = values.entrySet().stream()
                .filter(e -> e.getValue().compareTo(BigDecimal.valueOf(39)) == 0)
                .map(p -> Boolean.TRUE.booleanValue())
                .findFirst()
                .orElse(Boolean.FALSE);

        System.out.println("[7]\t\t" + valid);
    }

    private static String greet(NarrowedCustomerRelation s) {
        return s.getFirstName();
    }

    private static ValidationIssue  validate(NarrowedCustomerRelation o) {
        o.setRimNumber("Hello World " + o.getFirstName() + o.getLastName());
        return new ValidationIssue(o.getFirstName() + o.getLastName());
    }

    private static ValidationIssue  validate1(CustomerRelationSignatory o) {
        return new ValidationIssue(o.getFirstName() + o.getLastName());
    }

    private static ValidationIssue  validate2(ValidationIssue o) { return o; }

}
