package lt.swedbank.itacademy.app;

import lt.swedbank.itacademy.domain.Loan;
import lt.swedbank.itacademy.domain.LoanRiskType;
import lt.swedbank.itacademy.domain.VehicleLoan;
import lt.swedbank.itacademy.service.LoanService;
import lt.swedbank.itacademy.service.LoanServiceInterface;

import java.util.List;

public class ClientApp {

    public static void main(String[] args) {

        Loan[] loans = getInitializer().initializeLoans();
        LoanServiceInterface service = new LoanService(loans);

        //firstTaskSysout(service);
        //secondTaskSysout(service);
        thirdTaskSysout(service);
    }


    public static DomainInitializer getInitializer() {
        return new Task3DomainInitializer();
    }

    private static void firstTaskSysout(LoanServiceInterface service) {
        System.out.println("High risk loans: " + (service.calculateHighRiskLoans()).length);

        System.out.println("Average loan cost: " + service.calculateAverageLoanCost() + "\n");

        System.out.println("Normal avg loan cost: " + service.calculateAverageLoanCost(LoanRiskType.NORMAL_RISK));
        System.out.println("High avg loan cost: " + service.calculateAverageLoanCost(LoanRiskType.HIGH_RISK));
        System.out.println("Low avg loan cost: " + service.calculateAverageLoanCost(LoanRiskType.LOW_RISK) + "\n");

        System.out.println("AverageCostOfHighRiskLoans " + service.calculateAverageCostOfHighRiskLoans());
        System.out.println("MaximumPriceOfNonExpiredLoans " + service.calculateMaximumPriceOfNonExpiredLoans());
    }

    private static void secondTaskSysout(LoanServiceInterface service) {
        System.out.println("calculateNormalRiskVehicleLoans: " + service.calculateNormalRiskVehicleLoans().length);
        System.out.println("calculateMaximumAgeOfLowRiskLoanedVehicles: " + service.calculateMaximumAgeOfLowRiskLoanedVehicles());
        System.out.println("calculatePersonalRealEstateLoans: " + service.calculatePersonalRealEstateLoans().length);
        System.out.println("calculateExpiredHighRiskVehicleLoansOfHighestDuration: " + service.calculateExpiredHighRiskVehicleLoansOfHighestDuration().length);
    }

    private static void thirdTaskSysout (LoanServiceInterface service) {
        System.out.println("getLowRiskHarvesterLoans: " + service.calculateLowRiskHarvesterLoans().size());
        System.out.println("getExpiredLandLoansInReservation: " + service.calculateExpiredLandLoansInReservation().size());
        System.out.println("calculateLoansOfHigherThanAverageDepreciation: " + service.calculateLoansOfHigherThanAverageDepreciation().size());
    }
}