package RulesEngine;

import org.junit.Test;

import org.junit.Test;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Test2 {
    @Test
    public void verifyRules2() throws Exception {
        ParseJson parseJson = new ParseJson();

        Person person1 = parseJson.LoadPerson("src/test/resources/test2/Person.json");
        Product product1 = parseJson.LoadProduct("src/test/resources/test2/Product.json");
        Map<Integer,Rule> rules = parseJson.LoadRules("src/test/resources/test2/Rules.json");

        RulesEngine engine = new RulesEngine();
        engine.ApplyRules(person1, product1, rules);

        //"CreditScore" : 720,
        //"State" : "California"
        //  "Name" : "",
        //  "InterestRate" : 5.0
        assertEquals("Test 2: Product is not disqualified as expected ", product1.Disqualified, false);
        assertEquals("Test 2: Product's interested rate is not that expected ", product1.InterestRate, 4.7, 0);

        //System.out.printf("Test: Is the product disqualified ? %b", product1.Disqualified);
        //System.out.println(product1.Disqualified ? "PASSED" : "FAILED");
        //System.out.printf("Test: Is InterestRate equals expected value? %f", product1.InterestRate);

    }
}
