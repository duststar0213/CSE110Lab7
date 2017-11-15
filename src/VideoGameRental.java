
public class VideoGameRental {
	private Object _videoGame;
    private int _daysRented;
    private boolean _consoleRented;

    public VideoGameRental(Object videoGame, int daysRented, boolean consoleRented) {
    	if(videoGame.getClass().isAssignableFrom(Ps3Game.class)||
    	   videoGame.getClass().isAssignableFrom(Xbox360Game.class)||
    	   videoGame.getClass().isAssignableFrom(WiiGame.class))
    		_videoGame = videoGame;
        _daysRented = daysRented;
        _consoleRented = consoleRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Object getVideoGame() {
        return _videoGame;
    }
    
    public double getCharge(){
    	if(_videoGame.getClass().isAssignableFrom(Ps3Game.class))
    		return ((Ps3Game)_videoGame).getCharge(_daysRented, _consoleRented);
    	if(_videoGame.getClass().isAssignableFrom(Xbox360Game.class))
    		return ((Xbox360Game)_videoGame).getCharge(_daysRented, _consoleRented);
    	if(_videoGame.getClass().isAssignableFrom(WiiGame.class))
    		return ((WiiGame)_videoGame).getCharge(_daysRented, _consoleRented);
    	return 0;
    }
    
    public int getFrequentRenterPoints(){
    	if(_videoGame.getClass().isAssignableFrom(Ps3Game.class))
    		return ((Ps3Game)_videoGame).getFrequentRenterPoints(_daysRented, _consoleRented);
    	if(_videoGame.getClass().isAssignableFrom(Xbox360Game.class))
    		return ((Xbox360Game)_videoGame).getFrequentRenterPoints(_daysRented, _consoleRented);
    	if(_videoGame.getClass().isAssignableFrom(WiiGame.class))
    		return ((WiiGame)_videoGame).getFrequentRenterPoints(_daysRented, _consoleRented);
    	return 0;
    }
}