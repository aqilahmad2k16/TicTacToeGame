package strategies;

import models.Board;
import models.BotPlayer;

public interface BotPlayerStrategies {
	public void makeMove(Board board, BotPlayer botPlayer);
}
