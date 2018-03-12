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
        //Super is not needed (super.getPrice()). "super" should be used when you want to access parent class method/field,
        // which you are overriding in child (this) class.
        int compareResult = o.getPrice().compareTo(super.getPrice());
        if (compareResult != 0) return compareResult;

        //Float.compare(o.enginePower, enginePower)
        if (o.enginePower != enginePower) {
            if (o.enginePower > enginePower) return 1;
            else return -1;
        }

        //Again, super is not needed here. Also, you can put this line directly in "return" statement.
        compareResult = super.getInterestRate().compareTo(o.getInterestRate());
        return compareResult;
    }
}
