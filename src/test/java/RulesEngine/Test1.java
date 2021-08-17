package RulesEngine;

import org.junit.Test;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Test1 {

        @Test
        public void verifyRules1() throws Exception {
            ParseJson parseJson = new ParseJson();

            Person person1 = parseJson.LoadPerson("src/test/resources/test1/Person.json");
            Product product1 = parseJson.LoadProduct("src/test/resources/test1/Product.json");
            Map<Integer,Rule> rules = parseJson.LoadRules("src/test/resources/test1/Rules.json");

            RulesEngine engine = new RulesEngine();
            engine.ApplyRules(person1, product1, rules);

            assertEquals("Test 1: Product is not disqualified as expected ", product1.Disqualified, true);

            //System.out.printf("Test: Is the product disqualified ? %b", product1.Disqualified);
            //System.out.println(product1.Disqualified ? "PASSED" : "FAILED");
        }
}
