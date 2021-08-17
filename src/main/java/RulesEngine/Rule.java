package RulesEngine;

class Rule
{
    public Rule(String theName) {
        Name = theName;
    }

    public String Name;

    public String ConditionField;
    public Conditions Condition;
    public String ConditionValue;

    public String ActionField;
    public ActionTypes ActionType;
    public String ActionValue;

    public Boolean Check(Conditions theCondition, String theField, String theValue) throws Exception {
        switch (theCondition) {
            case Equal:
                return theField.equals(theValue);

            case NotEqual:
                return !theField.equals(theValue);

            default:
                throw new Exception(String.format("Unrecognized condition: {0}", theCondition));
        }
    }

    public Boolean Check(Conditions theCondition, int theField, String theValue) throws Exception {
        int value;
        try {
            value = Integer.parseInt(theValue);
        } catch (Exception e) {
            throw new Exception(String.format("Failed to parse value {0} to integer", theValue));
        }

        switch (theCondition) {
            case Equal:
                return theField == value;
            case NotEqual:
                return theField != value;
            case LessThanOrEqual:
                return theField <= value;
            case Less:
                return theField < value;
            case GreaterThanOrEqual:
                return theField >= value;
            case Greater:
                return theField > value;
            default:
                throw new Exception(String.format("Unrecognized condition: {0}", theCondition));
        }
    }

    private Boolean CheckCondition(Person thePerson, Product theProduct) throws Exception {
        switch (ConditionField)
        {
            case "Product.Name":
                return Check(Condition, theProduct.Name, ConditionValue);

            case "Person.State":
                return Check(Condition, thePerson.State, ConditionValue);

            case "Person.CreditScore":
                return Check(Condition, thePerson.CreditScore, ConditionValue);

            default:
                throw new Exception(String.format("Unrecognized filed: {0}", ConditionField));
        }

    }

    public Boolean Set(Boolean theField, String theValue) {
        Boolean value = null;
        value = Boolean.parseBoolean(theValue);
        return value;
    }

    public double Dec(double theField, String theValue) {
        return (theField - Double.parseDouble(theValue));
    }

    public double Inc(double theField, String theValue) {
        return (theField + Double.parseDouble(theValue));
    }

    public void Apply(Person thePerson, Product theProduct) throws Exception {
        if (CheckCondition(thePerson, theProduct)) {
            switch (ActionType) {
                case Set: {
                    switch (ActionField) {
                        case "Product.Disqualified":
                            theProduct.Disqualified = Set(theProduct.Disqualified, ActionValue);
                            return;

                        default:
                            throw new Exception(String.format("Unrecognized action field: {0}", ActionField));
                    }
                }

                case Inc: {
                    switch (ActionField) {
                        case "Product.InterestRate":
                            theProduct.InterestRate = Inc(theProduct.InterestRate, ActionValue);
                            return;

                        default:
                            throw new Exception(String.format("Unrecognized action field: {0}", ActionField));
                    }
                }

                case Dec: {
                    switch (ActionField) {
                        case "Product.InterestRate":
                            theProduct.InterestRate = Dec(theProduct.InterestRate, ActionValue);
                            return;

                        default:
                            throw new Exception(String.format("Unrecognized action field: {0}", ActionField));
                }
            }
        }
      }
    }
}
