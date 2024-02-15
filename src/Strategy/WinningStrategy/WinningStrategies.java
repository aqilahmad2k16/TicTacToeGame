package Strategy.WinningStrategy;

import models.Board;
import models.Move;

public interface WinningStrategies {

	boolean checkWinner(Move move, Board board);

}
