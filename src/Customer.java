import java.util.*;

class Customer {

    public String _name;
    private Vector<MovieRental> _movieRentals = new Vector<MovieRental>();
    private Vector<VideoGameRental> _videoGameRental = new Vector<VideoGameRental>(); 
    public Customer(String name) {
        _name = name;
    }

    public void addMovieRental(MovieRental arg) {
        _movieRentals.addElement(arg);
    }

    public void addVideoGameRental(VideoGameRental arg) {
        _videoGameRental.addElement(arg);
    }
    
    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration<MovieRental> movieRentals = _movieRentals.elements();
        Enumeration<VideoGameRental> videoGameRentals = _videoGameRental.elements();
        String result = "Rental Record for " + _name + "\n";

        while (movieRentals.hasMoreElements()) {
            MovieRental each = (MovieRental) movieRentals.nextElement();
            frequentRenterPoints += getFrequentRenterPoints(each);


            // show figures for this rental
            result += "\t" + each.getMovie().get_title() + "\t"
                    + String.valueOf(each.getCharge()) + "\n";
            totalAmount += each.getCharge();
        }
        while (videoGameRentals.hasMoreElements()) {
            VideoGameRental each = (VideoGameRental) videoGameRentals.nextElement();
            frequentRenterPoints += each.getFrequentRenterPoints();
            result += "\t" + each.getVideoGame() + "\t"
            		+ String.valueOf(each.getCharge()) + "\n";
            totalAmount += each.getCharge();
        }

        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints)
                + " frequent renter points";
        return result;
    }

    private int getFrequentRenterPoints(MovieRental each) {
        // add frequent renter points
       int frequentRenterPoints = 1;

        // add bonus for a two day new release rental
        if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                && each.getDaysRented() > 1) frequentRenterPoints++;
        return frequentRenterPoints;
    }

}
