package strategies;

import models.Board;
import models.Bot;

public interface BotPlayingStrategies {
	public void makeMove(Board board, Bot bot);
}
