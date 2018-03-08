package lt.swedbank.itacademy.domain;

public class LandLoan extends RealEstateLoan {
    private boolean isInReservation;

    public boolean isInReservation() {
        return isInReservation;
    }

    public void setInReservation(boolean inReservation) {
        isInReservation = inReservation;
    }
}
