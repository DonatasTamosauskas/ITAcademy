package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface LoanServiceInterface {
    int DAYS_IN_YEAR = 365;

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

    public Set<Loan> prioritizeLoans ();

    public Collection<HarvesterLoan> calculateLowRiskHarvesterLoans();

    public Collection<LandLoan> calculateExpiredLandLoansInReservation();

    public Collection<VehicleLoan> calculateLoansOfHigherThanAverageDepreciation();

}
