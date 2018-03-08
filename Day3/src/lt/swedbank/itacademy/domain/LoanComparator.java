package lt.swedbank.itacademy.domain;

import lt.swedbank.itacademy.util.LoanUtil;

import java.math.BigDecimal;
import java.util.Comparator;

public class LoanComparator  implements Comparator<Loan> {

    @Override
    public int compare(Loan o1, Loan o2) {
        int compareInteger;

        if ((compareInteger = compareRiskTypes(o2.getRiskType(), o1.getRiskType())) != 0) return compareInteger;
        if ((compareInteger = compareLoanCosts(o2, o1)) != 0) return compareInteger;
        return o1.getCreationDate().compareTo(o2.getCreationDate());

    }

    private int compareRiskTypes (LoanRiskType risk1, LoanRiskType risk2) {
        if (risk1 == risk2) return 0;
        if (risk1 == LoanRiskType.HIGH_RISK) return 1;
        if (risk1 == LoanRiskType.NORMAL_RISK && risk2 == LoanRiskType.LOW_RISK) return 1;
        else return -1;
    }

    private int compareLoanCosts (Loan loan1, Loan loan2) {
        BigDecimal cost1 = LoanUtil.calculateTotalLoanCost(loan1);
        BigDecimal cost2 = LoanUtil.calculateTotalLoanCost(loan2);

        return cost1.compareTo(cost2);
    }
}
