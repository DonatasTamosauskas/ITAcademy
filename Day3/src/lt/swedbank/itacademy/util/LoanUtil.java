package lt.swedbank.itacademy.util;

import lt.swedbank.itacademy.domain.Loan;
import lt.swedbank.itacademy.domain.VehicleLoan;

import java.math.BigDecimal;
import java.util.Date;

public class LoanUtil {
    private static final int DAYS_IN_YEAR = 365;

    public static BigDecimal calculateVehicleDeprecation(VehicleLoan vehicleLoan) {
        long yearsInUse = calculateYearsInUse(vehicleLoan.getManufacturedDate());
        BigDecimal maximumAge = new BigDecimal(vehicleLoan.getMaximumAge());
        return vehicleLoan.getPrice().multiply(new BigDecimal(yearsInUse)).divide(maximumAge, 2, BigDecimal.ROUND_HALF_EVEN);
    }

    private static long calculateYearsInUse(Date manufacturedDate) {
        long dayDifference = DateUtil.differenceInDays(new Date(), manufacturedDate);
        return dayDifference / DAYS_IN_YEAR;
    }

    public static BigDecimal calculateTotalLoanCost(Loan loan) {
        BigDecimal interest = loan.getPrice().multiply(loan.getInterestRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_UP));
        return loan.getPrice().add(interest);
    }

    public static boolean calculateIfLoanIsValid(Loan loan) {
        return DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears()).after(new Date());
    }

    public static int calculateVehicleLoanAge(VehicleLoan vehicleLoan) {
        return (int) (DateUtil.differenceInDays(new Date(), vehicleLoan.getManufacturedDate()) / DAYS_IN_YEAR);
    }
}
