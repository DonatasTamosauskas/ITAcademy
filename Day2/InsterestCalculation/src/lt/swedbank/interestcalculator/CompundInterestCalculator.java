package lt.swedbank.interestcalculator;

import java.util.Arrays;
import java.util.Scanner;

//"Compund" ?
public class CompundInterestCalculator {

    private static final int ALL_PERCENT = 100;

    //You don't need these to be declared as class fields.
    //General rule for variables: if they are used outside a class or in more than one method - field, only in one method - local variable.
    private static Scanner scanner;
    private static double loanAmount;
    private static double interestRate;
    private static int loanPeriod;
    private static int compoundFrequency;
    private static double[] interestArray;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        System.out.println("Amount: ");
        loanAmount = scanner.nextDouble();

        System.out.println("Interest rate (%): ");
        interestRate = scanner.nextDouble() / ALL_PERCENT;

        System.out.println("Period length (years): ");
        loanPeriod = scanner.nextInt();

        System.out.println("Compound frequency: ");
        compoundFrequency = findCompoundFrequency(scanner.next());

        interestArray = new double[compoundFrequency * loanPeriod];
        double interest = 0;

        for (int i = 0; i < loanPeriod * compoundFrequency; i++) {
            //You are using this variable only once. You can pass "loanAmount + interest" directly to "calculateInterest(...)".
            double temp = loanAmount + interest;

            interestArray[i] = calculateInterest(temp, interestRate, 1, compoundFrequency);
            interest += interestArray[i];

            if (i % compoundFrequency == 0) {
                System.out.printf("Interest amount after year %d: %.2f \n", i / compoundFrequency + 1, calculateInterest(loanAmount, interestRate, i / compoundFrequency + 1, compoundFrequency));
            }
        }

        System.out.printf("\nIntermediate interest amounts: %s\n", Arrays.toString(interestArray));
        System.out.printf("Total amount: %.2f", loanAmount + calculateInterest(loanAmount, interestRate, loanPeriod, compoundFrequency));
    }

    private static double calculateInterest(double loanAmount, double interestRate, int loanPeriod, int compoundFrequency) {
        //"power" is only used once. You can calculate it in "return" statement.
        double power = Math.pow(1 + (interestRate / compoundFrequency), loanPeriod * compoundFrequency);
        return loanAmount * power - loanAmount;
    }

    //Perfect!
    private static int findCompoundFrequency(String timePeriod) {
        switch (timePeriod) {
            case "D":
                return 365;
            case "W":
                return 52;
            case "M":
                return 12;
            case "Q":
                return 4;
            case "H":
                return 2;
            default:
                return 1;
        }
    }
}
