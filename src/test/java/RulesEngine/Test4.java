package RulesEngine;

import org.junit.Test;
import java.util.Map;

import static org.junit.Assert.assertEquals;
public class Test4 {
    @Test
    public void verifyRules4() throws Exception {
        ParseJson parseJson = new ParseJson();

        Person person1 = parseJson.LoadPerson("src/test/resources/test4/Person.json");
        Product product1 = parseJson.LoadProduct("src/test/resources/test4/Product.json");
        Map<Integer,Rule> rules = parseJson.LoadRules("src/test/resources/test4/Rules.json");

        RulesEngine engine = new RulesEngine();
        engine.ApplyRules(person1, product1, rules);

        //"CreditScore" : 710,
        //"State" : "California"
        //  "Name" : "7-1 ARM",
        //  "InterestRate" : 5.0
        assertEquals("Test 4: Product is not disqualified as expected ", product1.Disqualified, false);
        assertEquals("Test 4: Product's interested rate is not that expected ", product1.InterestRate, 6.0, 0);

        //System.out.printf("Test: Is the product disqualified ? %b", product1.Disqualified);
        //System.out.println(product1.Disqualified ? "PASSED" : "FAILED");
        //System.out.printf("Test: Is InterestRate equals expected value? %f", product1.InterestRate);

    }
}
