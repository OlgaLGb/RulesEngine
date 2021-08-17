package RulesEngine;

public class Product {
    public String Name;
    public double InterestRate;
    public Boolean Disqualified;

    public Product(String theName, double theInterestRate) {
        Name = theName;
        InterestRate = theInterestRate;
        Disqualified = false;
    }
}
