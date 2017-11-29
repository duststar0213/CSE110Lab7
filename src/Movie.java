public abstract class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title;

    public Movie(String title) {
        set_title(title);
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public abstract double getCharge(int _daysRented);

    public abstract int getFrequentRenterPoints(int _daysRented);
}
