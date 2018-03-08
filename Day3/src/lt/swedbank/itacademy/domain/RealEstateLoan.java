package lt.swedbank.itacademy.domain;

import java.util.Objects;

public class RealEstateLoan extends Loan {
    private String district;
    private float area;
    private RealEstatePurpose purpose;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public RealEstatePurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(RealEstatePurpose purpose) {
        this.purpose = purpose;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof RealEstateLoan)) return false;
        RealEstateLoan checkLoan = (RealEstateLoan) obj;

        return (super.equals(obj) &&
                Objects.equals(district, checkLoan.district) &&
                Objects.equals(area, checkLoan.area) &&
                Objects.equals(purpose, checkLoan.purpose));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), district, area, purpose);
    }
}
