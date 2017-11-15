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
            double thisAmount = 0;
    		
    		// determine amounts for each line
    		switch (each.getMovie().getPriceCode()) {
    		    case Movie.REGULAR:
    		        thisAmount += 2;
    		        if (each.getDaysRented() > 2)
    		            thisAmount += (each.getDaysRented() - 2) * 1.5;
    		        break;
    		    case Movie.NEW_RELEASE:
    		        thisAmount += each.getDaysRented() * 3;
    		        break;
    		    case Movie.CHILDRENS:
    		        thisAmount += 1.5;
    		        if (each.getDaysRented() > 3)
    		            thisAmount += (each.getDaysRented() - 3) * 1.25;
    		        break;
    		}

            // add frequent renter points
            frequentRenterPoints++;

            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    && each.getDaysRented() > 1) frequentRenterPoints++;

            // show figures for this rental
            result += "\t" + each.getMovie()._title+ "\t"
                    + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
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
}
