package RulesEngine;

import org.junit.Test;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Test6 {
    @Test
    public void verifyRules6() throws Exception {
        ParseJson parseJson = new ParseJson();

        Person person1 = parseJson.LoadPerson("src/test/resources/test6/Person.json");
        Product product1 = parseJson.LoadProduct("src/test/resources/test6/Product.json");
        Map<Integer,Rule> rules = parseJson.LoadRules("src/test/resources/test6/Rules.json");

        RulesEngine engine = new RulesEngine();
        engine.ApplyRules(person1, product1, rules);

        //"CreditScore" : 710,
        //"State" : "California"
        //  "Name" : "7-1 ARM",
        //  "InterestRate" : 5.0

        assertEquals("Test 6: Product is not disqualified as expected ", product1.Disqualified, false);
        assertEquals("Test 6: Product's interested rate is not that expected ", product1.InterestRate, 16.0, 0);

        //System.out.printf("Test: Is the product disqualified ? %b", product1.Disqualified);
        //System.out.println(product1.Disqualified ? "PASSED" : "FAILED");
        //System.out.printf("Test: Is InterestRate equals expected value? %f", product1.InterestRate);

    }
}
