import org.junit.*;

public class MovieRentalTest extends junit.framework.TestCase {

    Movie theManWhoKnewTooMuch, mulan, slumdogMillionaire;
    @Before
    public void setUp() {
        theManWhoKnewTooMuch = new Movie("The Man Who Knew Too Much", Movie.REGULAR);
        mulan = new Movie("Mulan", Movie.CHILDRENS);
        slumdogMillionaire = new Movie("Slumdog Millionaire", Movie.NEW_RELEASE);
    }

    @Test
    public void testGetPriceCode() {
        assertEquals(Movie.REGULAR, theManWhoKnewTooMuch.getPriceCode());
    }

    @Test
    public void testGetTitle() {
        assertEquals("The Man Who Knew Too Much", theManWhoKnewTooMuch.get_title());
    }

    @Test
    public void testSetPriceCode() {
        theManWhoKnewTooMuch.setPriceCode(Movie.NEW_RELEASE);
        assertEquals(Movie.NEW_RELEASE, theManWhoKnewTooMuch.getPriceCode());
        theManWhoKnewTooMuch.setPriceCode(Movie.REGULAR);
        assertEquals(Movie.REGULAR, theManWhoKnewTooMuch.getPriceCode());
    }

    @Test
    public void testGetDaysRented() {
        assertEquals(2, new MovieRental(theManWhoKnewTooMuch, 2).getDaysRented());
    }

    @Test
    public void testGetMovie() {
        assertEquals(theManWhoKnewTooMuch, new MovieRental(theManWhoKnewTooMuch, 2).getMovie());
    }

    @Test
    public void testGetName() {
        String name = "John Doe";
        assertEquals(name, new Customer(name)._name);
    }

    @Test
    public void testStatementRegularMovieOnly() {
        // regular movies cost $2.00 for the first 2 days, and $1.50/day thereafter
        // a rental earns 1 frequent-renter point no matter how many days
        Customer johnDoe = new Customer("John Doe");
        johnDoe.addMovieRental(new MovieRental(slumdogMillionaire, 1));
        assertEquals("Rental Record for John Doe\n" +
                        "\tSlumdog Millionaire\t3.0\n" +
                        "Amount owed is 3.0\n" +
                        "You earned 1 frequent renter points",
                johnDoe.statement());

        johnDoe.addMovieRental(new MovieRental(slumdogMillionaire, 4));
        assertEquals("Rental Record for John Doe\n" +
                        "\tSlumdog Millionaire\t3.0\n" +
                        "\tSlumdog Millionaire\t12.0\n" +
                        "Amount owed is 15.0\n" +
                        //notice it is the 1 point from before and 2 points for 2 or more days for the current movie.
                        "You earned 3 frequent renter points",
                johnDoe.statement());

        johnDoe.addMovieRental(new MovieRental(slumdogMillionaire, 5));
        assertEquals("Rental Record for John Doe\n" +
                        //the first movie rental above
                        "\tSlumdog Millionaire\t3.0\n" +
                        "\tSlumdog Millionaire\t12.0\n" +
                        "\tSlumdog Millionaire\t15.0\n" +
                        "Amount owed is 30.0\n" +
                        "You earned 5 frequent renter points",
                johnDoe.statement());
    }

    @Test
    public void testStatementChildrensMovieOnly() {
        // childrens' movies cost $1.50 for the first 3 days, and $1.25/day thereafter
        // a rental earns 1 frequent-renter point no matter how many days
        Customer johnDoeJr = new Customer("Johnny Doe, Jr.");
        johnDoeJr.addMovieRental(new MovieRental(slumdogMillionaire, 1));
        assertEquals("Rental Record for Johnny Doe, Jr.\n" +
                        "\tSlumdog Millionaire\t3.0\n" +
                        "Amount owed is 3.0\n" +
                        "You earned 1 frequent renter points",
                johnDoeJr.statement());

        johnDoeJr.addMovieRental(new MovieRental(slumdogMillionaire, 4));
        assertEquals("Rental Record for Johnny Doe, Jr.\n" +
                        "\tSlumdog Millionaire\t3.0\n" +
                        "\tSlumdog Millionaire\t12.0\n" +
                        "Amount owed is 15.0\n" +
                        //notice it is the 1 point from before and 2 points for 2 or more days for the current movie.
                        "You earned 3 frequent renter points",
                johnDoeJr.statement());

        johnDoeJr.addMovieRental(new MovieRental(slumdogMillionaire, 5));
        assertEquals("Rental Record for Johnny Doe, Jr.\n" +
                        //the first movie rental above
                        "\tSlumdog Millionaire\t3.0\n" +
                        "\tSlumdog Millionaire\t12.0\n" +
                        "\tSlumdog Millionaire\t15.0\n" +
                        "Amount owed is 30.0\n" +
                        "You earned 5 frequent renter points",
                johnDoeJr.statement());
    }

    @Test
    public void testStatementNewReleaseOnly() {
        // new releases cost $3.00/day
        // a rental earns 1 frequent-renter point 1 day; 2 points for 2 or more days
        Customer janeDoe = new Customer("Jane Doe");
        janeDoe.addMovieRental(new MovieRental(slumdogMillionaire, 1));
        assertEquals("Rental Record for Jane Doe\n" +
                        "\tSlumdog Millionaire\t3.0\n" +
                        "Amount owed is 3.0\n" +
                        "You earned 1 frequent renter points",
                janeDoe.statement());

        janeDoe.addMovieRental(new MovieRental(slumdogMillionaire, 2));
        assertEquals("Rental Record for Jane Doe\n" +
                        //the first movie rental above
                        "\tSlumdog Millionaire\t3.0\n" +
                        //the current movie rental
                        "\tSlumdog Millionaire\t6.0\n" +
                        "Amount owed is 9.0\n" +
                        //notice it is the 1 point from before and 2 points for 2 or more days for the current movie.
                        "You earned 3 frequent renter points",
                janeDoe.statement());

        janeDoe.addMovieRental(new MovieRental(slumdogMillionaire, 5));
        assertEquals("Rental Record for Jane Doe\n" +
                        //the first movie rental above
                        "\tSlumdog Millionaire\t3.0\n" +
                        "\tSlumdog Millionaire\t6.0\n" +
                        "\tSlumdog Millionaire\t15.0\n" +
                        "Amount owed is 24.0\n" +
                        "You earned 5 frequent renter points",
                janeDoe.statement());

    }
}