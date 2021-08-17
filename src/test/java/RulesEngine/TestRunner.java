package RulesEngine;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Test1.class, Test2.class, Test3.class, Test4.class, Test5.class, Test6.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        //System.out.println(result.wasSuccessful());
        printFooter(result);
    }

    protected static void printFooter(Result result) {
        if (result.wasSuccessful()) {
            System.out.println();
            System.out.println("===========================");
            System.out.println("OK");
            System.out.println(" (" + result.getRunCount() + " test" + (result.getRunCount() == 1 ? "" : "s") + ")");
        } else {
            System.out.println();
            System.out.println("FAILURES!!!");
            System.out.println("Tests run: " + result.getRunCount() + ",  Failures: " + result.getFailureCount());
        }
        System.out.println();
    }
}