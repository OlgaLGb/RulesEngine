package RulesEngine;
import org.junit.Test;
import java.util.Map;

import static org.junit.Assert.assertEquals;
public class Test5 {
    @Test
    public void verifyRules5() throws Exception {
        ParseJson parseJson = new ParseJson();

        Person person1 = parseJson.LoadPerson("src/test/resources/test5/Person.json");
        Product product1 = parseJson.LoadProduct("src/test/resources/test5/Product.json");
        Map<Integer,Rule> rules = parseJson.LoadRules("src/test/resources/test5/Rules.json");

        RulesEngine engine = new RulesEngine();
        engine.ApplyRules(person1, product1, rules);

        //"CreditScore" : 710,
        //"State" : "California"
        //  "Name" : "",
        //  "InterestRate" : 5.0
        assertEquals("Test 5: Product is not disqualified as expected ", product1.Disqualified, false);
        assertEquals("Test 5: Product's interested rate is not that expected ", product1.InterestRate, 5.5, 0);

    }
}
