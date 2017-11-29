public class NewReleaseMovie extends Movie {

    public static final int LONG_TERM_RENTAL_COST = 3;
    public static final int FREQUENT_RENTER_POINTS = 1;

    public NewReleaseMovie(String title) {
        super(title);
    }

    @Override
    public double getCharge(int _daysRented) {
        double result = 0;
        // determine amounts for each line
        result += _daysRented * LONG_TERM_RENTAL_COST;
        return result;
    }

    @Override
    public int getFrequentRenterPoints(int _daysRented) {
        int frequentRenterPoints = FREQUENT_RENTER_POINTS;
        // add bonus for a two day new release rental
        if (_daysRented > 1)
            frequentRenterPoints += frequentRenterPoints;
        return frequentRenterPoints;
    }
}
