package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface LoanServiceInterface {
    int DAYS_IN_YEAR = 365;
    int ROUNDING_PRECISION = 2;

    Loan[] calculateHighRiskLoans();

    BigDecimal calculateAverageLoanCost();

    BigDecimal calculateAverageLoanCost(LoanRiskType loanRiskType);

    BigDecimal calculateAverageCostOfHighRiskLoans();

    BigDecimal calculateMaximumPriceOfNonExpiredLoans();

    Set<String> findVehicleModels();

    Map<LoanRiskType, Collection<Loan>> groupLoansByRiskType();

    Loan[] calculateNormalRiskVehicleLoans();

    int calculateMaximumAgeOfLowRiskLoanedVehicles();

    Loan[] calculatePersonalRealEstateLoans();

    Loan[] calculateExpiredHighRiskVehicleLoansOfHighestDuration();

    Set<Loan> prioritizeLoans ();

    Collection<HarvesterLoan> calculateLowRiskHarvesterLoans();

    Collection<LandLoan> calculateExpiredLandLoansInReservation();

    Collection<VehicleLoan> calculateLoansOfHigherThanAverageDepreciation();

}
