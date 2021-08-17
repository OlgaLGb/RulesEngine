package RulesEngine;

import java.util.Map;

public class RulesEngine{

    public void ApplyRules(Person thePerson, Product theProduct, Map<Integer, Rule> theRules) throws Exception {

        for ( Map.Entry<Integer, Rule> getRule : theRules.entrySet() ) {
            getRule.getValue().Apply(thePerson, theProduct);
        }
    }

}
