package RulesEngine;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class ParseJson {

    public Person LoadPerson(String thePath) throws IOException, Exception {
        Person person = null;

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(thePath)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            Long creditScore = (Long)((JSONObject)jsonObject).get("CreditScore");
            String state =  (String)((JSONObject)jsonObject).get("State");

            person = new Person(creditScore.intValue(), state);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return person;
    }

    public Product LoadProduct(String thePath) throws IOException, Exception {
        Product product = null;

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(thePath)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            Double interestRate = (Double)((JSONObject)jsonObject).get("InterestRate");
            String name =  (String)((JSONObject)jsonObject).get("Name");

            product = new Product(name, interestRate.doubleValue());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return product;
    }

    public Map<Integer,Rule> LoadRules(String thePath) throws IOException, Exception {

        Map<Integer,Rule> map = new TreeMap<Integer,Rule>();
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(thePath)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray rules = (JSONArray) jsonObject.get("Rules");

            int ruleNo = 1;
            for (Object rule : rules) {

                String ruleName = (String)((JSONObject)rule).get("Name");
                //System.out.println(String.format("Loading rule: %s", ruleName));

                Rule newRule = new Rule(ruleName);
                String condition = (String)((JSONObject)rule).get("Condition");

                switch (condition) {
                    case "Equal":
                        newRule.Condition = Conditions.Equal;
                        break;

                    case "NotEqual":
                        newRule.Condition = Conditions.NotEqual;
                        break;

                    case "Less":
                        newRule.Condition = Conditions.Less;
                        break;

                    case "Greater":
                        newRule.Condition = Conditions.Greater;
                        break;

                    case "LessThanOrEqual":
                        newRule.Condition = Conditions.LessThanOrEqual;
                        break;

                    case "GreaterThanOrEqual":
                        newRule.Condition = Conditions.GreaterThanOrEqual;
                        break;

                    default:
                        throw new Exception(String.format("Unrecognized condition: %s", condition));
                }

                newRule.ConditionField = (String)((JSONObject)rule).get("ConditionField");
                newRule.ConditionValue = (String)((JSONObject)rule).get("ConditionValue");

                String actionType = (String)((JSONObject)rule).get("ActionType");
                switch (actionType) {
                    case "Set":
                        newRule.ActionType = ActionTypes.Set;
                        break;

                    case "Inc":
                        newRule.ActionType = ActionTypes.Inc;
                        break;

                    case "Dec":
                        newRule.ActionType = ActionTypes.Dec;
                        break;

                    default:
                        throw new Exception(String.format("Unrecognized action type: %s", condition));
                }

                newRule.ActionField = (String)((JSONObject)rule).get("ActionField");
                newRule.ActionValue = (String)((JSONObject)rule).get("ActionValue");

                map.put(ruleNo++, newRule);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return map;
    }

}