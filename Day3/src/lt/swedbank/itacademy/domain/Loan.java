package lt.swedbank.itacademy.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Loan {

    private Date creationDate;
    private int termInYears;
    private String name;
    private BigDecimal interestRate;
    private BigDecimal price;
    private LoanRiskType riskType;


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getTermInYears() {
        return termInYears;
    }

    public void setTermInYears(int termInYears) {
        this.termInYears = termInYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LoanRiskType getRiskType() {
        return riskType;
    }

    public void setRiskType(LoanRiskType riskType) {
        this.riskType = riskType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Loan)) return false;
        Loan checkLoan = (Loan) obj;
        return (Objects.equals(creationDate, checkLoan.creationDate) &&
                Objects.equals(termInYears, checkLoan.termInYears) &&
                Objects.equals(name, checkLoan.name) &&
                Objects.equals(interestRate, checkLoan.interestRate) &&
                Objects.equals(price, checkLoan.price) &&
                Objects.equals(riskType, checkLoan.riskType));
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDate, termInYears, name, interestRate, price, riskType);
    }
}
