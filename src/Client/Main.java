package Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controllers.GameControllers;
import exception.BotCountException;
import exception.PlayerCountDimensionMismatchException;
import exception.SymbolCountException;
import models.Game;
import models.GameState;
import models.Player;
import models.PlayerType;
import models.Symbol;
import winningStrategies.OrderOneWinningStrategy;

public class Main {
	public static void main(String[] args) throws BotCountException, SymbolCountException, PlayerCountDimensionMismatchException {
		GameControllers gameControllers = new GameControllers();
		//we have started the game, will do other stuff as well
		Player john = new Player("John", 1, new Symbol('X'), PlayerType.HUMAN);
		Player michel = new Player("Michel", 2, new Symbol('O') , PlayerType.BOT);
		Game game = gameControllers.startGame(3, Arrays.asList(john, michel), new OrderOneWinningStrategy(3));
		
		while(gameControllers.checkState(game).equals(GameState.IN_PROGRESS)) {
			gameControllers.displayBoard(game);
			gameControllers.makeMove(game);
			
			//do undo
			gameControllers.undu(game);
		}
		
		if(gameControllers.checkState(game).equals(GameState.ENDED)) {
			Player winner = game.getWinner();
			System.out.println("The winner is: " + winner.getName());
		} else if(gameControllers.checkState(game).equals(GameState.DRAW)) {
			System.out.println("Game is Drawn!!!!");
		}
		
	}
}
