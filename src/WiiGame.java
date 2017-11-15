
public class WiiGame {
	private static double CONSOLE_COST = 199.99 ;
	private static double CONSOLE_RENTAL_COST = 29.99 ;
	private static double MODIFIER = .75;
	private static int NUM_DAYS_BASE_PRICE = 4;
	private static double BASE_PRICE = 3;
	private static double EXTRA_PRICE = 1.50;
	private static int FREQUENT_RENTER_POINTS = 1;
	private static String CONSOLE_NAME = "Nintendo Wii";
	
	private String _gameName;
		
	public WiiGame(String name) {
		super();
		_gameName = name;
	}

	public String get_gameName() {
		return _gameName;
	}

	public String get_consoleName() {
		return CONSOLE_NAME;
	}
	
	public double getCharge(int _daysRented, boolean _consoleRented) {
		double result = 0;
		result += BASE_PRICE;
		if(_daysRented > NUM_DAYS_BASE_PRICE)
			result += (_daysRented - NUM_DAYS_BASE_PRICE)*EXTRA_PRICE;
		if(_consoleRented){
			result += MODIFIER;
			result += CONSOLE_RENTAL_COST + _daysRented;
		}
		return result;
	}
	
	public int getFrequentRenterPoints(int _daysRented, boolean _consoleRented) {
		if(_consoleRented)
			return FREQUENT_RENTER_POINTS + _daysRented;
		else 
			return FREQUENT_RENTER_POINTS;
	}

	public String toString() {
		return _gameName;
	}
}
