
public interface OnePlayerGame {
	public void createPlay(String play);
	
	public int validPlay(String play);
	
	public int gameOver();
	
	public String stateOfGame();
}
