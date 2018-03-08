package lt.swedbank.interestcalculator;

import java.util.Arrays;
import java.util.Scanner;

public class CompoundInterestCalculator2 {

    private static final int ALL_PERCENT = 100;
    private static final int STARTING_INTEREST_ARRAY_LENGTH = 20;

    private static Scanner scanner;
    private static double loanAmount;
    private static double interestRate;
    private static int compoundFrequency;
    private static int loanPeriod;
    private static double[] interestArray;

    private static double[] interestFrequencyArray;
    private static int interestFrequencyAmount = 0;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        interestFrequencyArray = new double[STARTING_INTEREST_ARRAY_LENGTH];

        System.out.println("Amount: ");
        loanAmount = scanner.nextDouble();

        do {
            System.out.println("Interest rate (%): ");
            interestFrequencyArray[interestFrequencyAmount] = scanner.nextDouble() / ALL_PERCENT;
            interestFrequencyAmount++;
        } while (interestFrequencyArray[interestFrequencyAmount - 1] != 0);

        System.out.println("Period length (years): ");
        loanPeriod = scanner.nextInt();

        System.out.println("Compound frequency: ");
        compoundFrequency = findCompoundFrequency(scanner.next());

        for (int i = 0; i < loanPeriod; i++) {
            for (int j = 0; j < interestFrequencyAmount; j++){

            }
        }
    }

    private static double calculateInterest(double loanAmount, double interestRate, int loanPeriod, int compoundFrequency) {
        double power = Math.pow(1 + (interestRate / compoundFrequency), loanPeriod * compoundFrequency);
        return loanAmount * power - loanAmount;
    }

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
