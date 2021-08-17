package RulesEngine;

import java.util.HashMap;
import java.util.Map;

public class RunRules {
    public static void main(String[] args) throws Exception {

        //Person person1 = new Person(720, "Florida");
        Person person1 = new Person(720, "Washington");
        Product product1 = new Product("7-1 ARM", 5.0);

        RulesEngine engine = new RulesEngine();

        ParseJson parseJson = new ParseJson();
        Map<Integer,Rule> rules = parseJson.LoadRules("src/main/resources/Rules.json");

        engine.ApplyRules(person1, product1, rules);

        System.out.printf("Test: Is the product disqualified? %b", product1.Disqualified);
        System.out.println(product1.Disqualified ? "PASSED" : "FAILED");

        System.out.printf("Test: Is InterestRate equals expected value? %f", product1.InterestRate);

        // without my own rule 4
        //System.out.println(product1.InterestRate == 5.2 ? "PASSED" : "FAILED");

        // with my own rule 4
        //System.out.println(product1.InterestRate == 5.3 ? "PASSED" : "FAILED");
    }
}