package lt.swedbank.itacademy.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class VehicleLoan extends Loan {

    private static final BigDecimal HIGH_RISK_COEFFICENT = new BigDecimal(1.5);
    private static final BigDecimal NORMAL_RISK_COEFFICENT = new BigDecimal(1);
    private static final BigDecimal LOW_RISK_COEFFICENT = new BigDecimal(0.8);

    private Date manufactured;
    private String model;
    private int maximumAge;

    public Date getManufacturedDate() {
        return manufactured;
    }

//    @Override
//    public void setInterestRate(BigDecimal interestRate) {
//        if (super.getRiskType() == LoanRiskType.HIGH_RISK) {
//            super.setInterestRate(interestRate.multiply(HIGH_RISK_COEFFICENT));
//        } else if (super.getRiskType() == LoanRiskType.NORMAL_RISK) {
//            super.setInterestRate(interestRate.multiply(NORMAL_RISK_COEFFICENT));
//        } else if (super.getRiskType() == LoanRiskType.LOW_RISK) {
//            super.setInterestRate(interestRate.multiply(LOW_RISK_COEFFICENT));
//        }
//    }

    public void setManufactured(Date manufactured) {
        this.manufactured = manufactured;
    }

    public void setManufacturedDate(Date manufactured) {
        this.manufactured = manufactured;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof VehicleLoan)) return false;
        VehicleLoan checkLoan = (VehicleLoan) obj;

        return (super.equals(obj) &&
                Objects.equals(manufactured, checkLoan.manufactured) &&
                Objects.equals(model, checkLoan.model) &&
                Objects.equals(maximumAge, checkLoan.maximumAge));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), manufactured, model, maximumAge);
    }
}
