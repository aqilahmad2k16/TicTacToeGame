package models;

public class BotPlayer extends Player{
	BotDifficultyLevel botDifficultyLevel;

	public BotPlayer(String name, int id, Symbol symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
		super(name, id, symbol, playerType);
		// TODO Auto-generated constructor stub
		this.botDifficultyLevel = botDifficultyLevel;
	}

	public BotDifficultyLevel getBotDifficultyLevel() {
		return botDifficultyLevel;
	}

	public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
		this.botDifficultyLevel = botDifficultyLevel;
	}
	
	

}
