package lt.swedbank.itacademy.domain;

public class CarLoan extends VehicleLoan implements Comparable<CarLoan> {
    private float enginePower;

    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public int compareTo(CarLoan o) {
        int compareResult = o.getPrice().compareTo(super.getPrice());
        if (compareResult != 0) return compareResult;

        if (o.enginePower != enginePower) {
            if (o.enginePower > enginePower) return 1;
            else return -1;
        }

        compareResult = super.getInterestRate().compareTo(o.getInterestRate());
        return compareResult;
    }
}
