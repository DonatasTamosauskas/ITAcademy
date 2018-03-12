package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.*;
import lt.swedbank.itacademy.util.LoanUtil;

import java.math.BigDecimal;
import java.util.*;

public class LoanService implements LoanServiceInterface {

    private Loan[] loanArray;

    public LoanService(Loan[] loans) {
        loanArray = loans;
    }


    @Override
    //Task 1: rewrite "Loan[]" -> "Collection<Loan>"/"List<Loan>"
    public Loan[] calculateHighRiskLoans() {
        ArrayList<Loan> highRiskLoans = new ArrayList<>(Arrays.asList(loanArray));

        for (Loan currentLoan : loanArray) {
            if (currentLoan.getRiskType() == LoanRiskType.HIGH_RISK) {
                highRiskLoans.add(currentLoan);
            }
        }
        return highRiskLoans.toArray(new Loan[highRiskLoans.size()]);
    }

    @Override
    public BigDecimal calculateAverageLoanCost() {
        BigDecimal averageLoanCost = BigDecimal.ZERO;

        for (Loan currentLoan : loanArray) {
            averageLoanCost = averageLoanCost.add(LoanUtil.calculateTotalLoanCost(currentLoan));
        }
        return averageLoanCost.divide(new BigDecimal(loanArray.length), ROUNDING_PRECISION, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public BigDecimal calculateAverageLoanCost(LoanRiskType loanRiskType) {
        BigDecimal averageLoanCost = BigDecimal.ZERO;
        int numberOfLoans = 0;

        for (Loan currentLoan : loanArray) {
            if (currentLoan.getRiskType() == loanRiskType) {
                averageLoanCost = averageLoanCost.add(LoanUtil.calculateTotalLoanCost(currentLoan));
                numberOfLoans++;
            }
        }
        return averageLoanCost.divide(new BigDecimal(numberOfLoans), ROUNDING_PRECISION, BigDecimal.ROUND_UP);
    }

    @Override
    public BigDecimal calculateAverageCostOfHighRiskLoans() {
        BigDecimal averageLoanCost = BigDecimal.ZERO;
        int numberOfLoans = 0;

        for (Loan currentLoan : loanArray) {
            if (currentLoan.getRiskType() == LoanRiskType.HIGH_RISK) {
                averageLoanCost = averageLoanCost.add(LoanUtil.calculateTotalLoanCost(currentLoan));
                numberOfLoans++;
            }
        }
        return averageLoanCost.divide(new BigDecimal(numberOfLoans), ROUNDING_PRECISION, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public BigDecimal calculateMaximumPriceOfNonExpiredLoans() {
        BigDecimal maximumPrice = new BigDecimal(0);

        for (Loan currentLoan : loanArray) {
            if (LoanUtil.calculateIfLoanIsValid(currentLoan)) {
                if (currentLoan.getPrice().compareTo(maximumPrice) > 0) {
                    maximumPrice = currentLoan.getPrice();
                }
            }
        }
        return maximumPrice;
    }

    @Override
    public Set<String> findVehicleModels() {
        Set<String> differentVehicleModels = new HashSet<>();

        for (Loan currentLoan : loanArray) {
            if (currentLoan instanceof VehicleLoan) {
                differentVehicleModels.add(((VehicleLoan) currentLoan).getModel());
            }
        }
        return differentVehicleModels;
    }

    @Override
    public Map<LoanRiskType, Collection<Loan>> groupLoansByRiskType() {
        //You should choose a better name than "loans".
        Map<LoanRiskType, Collection<Loan>> loans = new HashMap<>();

        for (Loan currentLoan : loanArray) {
            if (!loans.containsKey(currentLoan.getRiskType())) {
                List<Loan> differentRiskLoans = new ArrayList<>();
                differentRiskLoans.add(currentLoan);
                loans.put(currentLoan.getRiskType(), differentRiskLoans);
            } else {
                loans.get(currentLoan.getRiskType()).add(currentLoan);
            }
        }

        return loans;
    }

    @Override
    //Task 1: rewrite "Loan[]" -> "Collection<Loan>"/"List<Loan>"
    public Loan[] calculateNormalRiskVehicleLoans() {
        Collection<VehicleLoan> highRiskVehicles = calculateLoanedVehiclesByRisk(LoanRiskType.HIGH_RISK);
        return highRiskVehicles.toArray(new VehicleLoan[highRiskVehicles.size()]);
    }

    @Override
    public int calculateMaximumAgeOfLowRiskLoanedVehicles() {
        Collection<VehicleLoan> lowRiskVehicles = calculateLoanedVehiclesByRisk(LoanRiskType.LOW_RISK);
        int maximumAge = 0;

        for (VehicleLoan vehicleLoan : lowRiskVehicles) {
            if (LoanUtil.calculateVehicleLoanAge(vehicleLoan) > maximumAge) {
                maximumAge = LoanUtil.calculateVehicleLoanAge(vehicleLoan);
            }
        }
        return maximumAge;
    }

    @Override
    //Task 1: rewrite "Loan[]" -> "Collection<Loan>"/"List<Loan>"
    public Loan[] calculatePersonalRealEstateLoans() {
        //Don't use concrete types in collection declarations!
        ArrayList<RealEstateLoan> personalLoans = calculateRealEstateLoansByPurpose(RealEstatePurpose.PERSONAL);
        return personalLoans.toArray(new Loan[personalLoans.size()]);
    }

    @Override
    //Task 1: rewrite "Loan[]" -> "Collection<Loan>"/"List<Loan>"
    public Loan[] calculateExpiredHighRiskVehicleLoansOfHighestDuration() {
        Collection<VehicleLoan> highRiskVehicles = calculateLoanedVehiclesByRisk(LoanRiskType.HIGH_RISK);
        Collection<VehicleLoan> expiredVehicles = new ArrayList<>();

        for (VehicleLoan currentVehicle : highRiskVehicles) {
            if (!LoanUtil.calculateIfLoanIsValid(currentVehicle)) {
                expiredVehicles.add(currentVehicle);
            }
        }
        return expiredVehicles.toArray(new Loan[expiredVehicles.size()]);
    }

    public Set<Loan> prioritizeLoans() {
        Set<Loan> prioritizedLoans = new TreeSet<>(new LoanComparator());
        prioritizedLoans.addAll(Arrays.asList(loanArray));

        return prioritizedLoans;
    }

    public Collection<HarvesterLoan> calculateLowRiskHarvesterLoans() {
        Collection<Loan> lowRiskLoans = groupLoansByRiskType(LoanRiskType.LOW_RISK);
        Collection<HarvesterLoan> lowRiskHarvesterLoans = new HashSet<>();

        for (Loan currentLoan : lowRiskLoans) {
            if (currentLoan instanceof HarvesterLoan) {
                lowRiskHarvesterLoans.add((HarvesterLoan) currentLoan);
            }
        }

        return lowRiskHarvesterLoans;
    }

    public Collection<LandLoan> calculateExpiredLandLoansInReservation() {
        Collection<LandLoan> expiredLandLoansInReservation = new ArrayList<>();

        for (Loan currentLoan : loanArray) {
            if (currentLoan instanceof LandLoan &&
                    LoanUtil.calculateIfLoanIsValid(currentLoan) &&
                    ((LandLoan) currentLoan).isInReservation()) {
                expiredLandLoansInReservation.add((LandLoan) currentLoan);
            }
        }

        return expiredLandLoansInReservation;
    }

    public Collection<VehicleLoan> calculateLoansOfHigherThanAverageDepreciation() {
        Collection<VehicleLoan> vehicleLoans = calculateVehicleLoans();
        Collection<VehicleLoan> higherThanAverageDepreciation = new ArrayList<>();
        BigDecimal averageDepreciation = calculateAverageDepreciation();

        for (VehicleLoan currentLoan : vehicleLoans) {
            if (LoanUtil.calculateVehicleDeprecation(currentLoan).compareTo(averageDepreciation) > 0) {
                higherThanAverageDepreciation.add(currentLoan);
            }
        }

        return higherThanAverageDepreciation;
    }

    private Collection<VehicleLoan> calculateVehicleLoans() {
        Collection<VehicleLoan> vehicleLoans = new ArrayList<>();

        for (Loan currentLoan : loanArray) {
            if (currentLoan instanceof VehicleLoan) {
                vehicleLoans.add((VehicleLoan) currentLoan);
            }
        }

        return vehicleLoans;
    }

    private BigDecimal calculateAverageDepreciation() {
        Collection<VehicleLoan> vehicleLoans = calculateVehicleLoans();
        BigDecimal average = BigDecimal.ZERO;

        for (VehicleLoan currentLoan : vehicleLoans) {
            average = average.add(LoanUtil.calculateVehicleDeprecation(currentLoan));
        }

        return average.divide(new BigDecimal(vehicleLoans.size()), 2, BigDecimal.ROUND_HALF_EVEN);
    }

    private Collection<VehicleLoan> calculateLoanedVehiclesByRisk(LoanRiskType riskType) {
        Collection<Loan> riskTypeLoans = groupLoansByRiskType(riskType);
        Collection<VehicleLoan> vehicles = new ArrayList<>();

        for (Loan currentLoan : riskTypeLoans) {
            if (currentLoan instanceof VehicleLoan) {
                vehicles.add((VehicleLoan) currentLoan);
            }
        }
        return vehicles;

    }

    private ArrayList<RealEstateLoan> calculateRealEstateLoansByPurpose(RealEstatePurpose realEstatePurpose) {
        ArrayList<RealEstateLoan> realEstateLoans = new ArrayList<>();
        for (Loan currentLoan : loanArray) {
            if (currentLoan instanceof RealEstateLoan && ((RealEstateLoan) currentLoan).getPurpose() == realEstatePurpose) {
                realEstateLoans.add((RealEstateLoan) currentLoan);
            }
        }
        return realEstateLoans;
    }


    private Collection<Loan> groupLoansByRiskType(LoanRiskType riskType) {
        Collection<Loan> groupedLoans = new HashSet<>();

        for (Loan currentLoan : loanArray) {
            if (currentLoan.getRiskType() == riskType) {
                groupedLoans.add(currentLoan);
            }
        }

        return groupedLoans;
    }
}
