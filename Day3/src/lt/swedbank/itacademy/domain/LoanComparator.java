package lt.swedbank.itacademy.domain;

import lt.swedbank.itacademy.util.LoanUtil;

import java.math.BigDecimal;
import java.util.Comparator;

public class LoanComparator  implements Comparator<Loan> {

    @Override
    public int compare(Loan o1, Loan o2) {
        int compareInteger;

        //Wow, seems you've learned some stuff :)
        if ((compareInteger = compareRiskTypes(o2.getRiskType(), o1.getRiskType())) != 0) return compareInteger;
        if ((compareInteger = compareLoanCosts(o2, o1)) != 0) return compareInteger;
        return o1.getCreationDate().compareTo(o2.getCreationDate());

    }

    //Seems like overly complicated and hard to change (if additional "LoanRiskType" would be added).
    //Please look here - https://github.com/andrewmic/enum-comparator-example
    //I've created some examples on how you can compare enums. Spoiler alert: I think "SwitchlessMappedValueBasedLoanRiskComparator" is the best approach.
    private int compareRiskTypes (LoanRiskType risk1, LoanRiskType risk2) {
        if (risk1 == risk2) return 0;
        if (risk1 == LoanRiskType.HIGH_RISK) return 1;
        if (risk1 == LoanRiskType.NORMAL_RISK && risk2 == LoanRiskType.LOW_RISK) return 1;
        else return -1;
    }

    //You can rewrite this method into a single return statement
    private int compareLoanCosts (Loan loan1, Loan loan2) {
        BigDecimal cost1 = LoanUtil.calculateTotalLoanCost(loan1);
        BigDecimal cost2 = LoanUtil.calculateTotalLoanCost(loan2);

        return cost1.compareTo(cost2);
    }
}
