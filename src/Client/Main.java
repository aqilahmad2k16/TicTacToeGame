package Client;

import controllers.GameControllers;
import exception.BotCountException;
import exception.PlayerCountDimensionMismatchException;
import exception.SymbolCountException;
import models.Game;
import models.GameState;
import models.Player;

public class Main {
	public static void main(String[] args) throws BotCountException, SymbolCountException, PlayerCountDimensionMismatchException {
		GameControllers gameControllers = new GameControllers();
		//we have started the game
		Game game = gameControllers.startGame(3, null, null);
		
		while(gameControllers.checkState(game).equals(GameState.IN_PROGRESS)) {
			gameControllers.displayBoard(game);
			gameControllers.makeMove(game);
			
			//do undo
			gameControllers.undu();
		}
		
		if(gameControllers.checkState(game).equals(GameState.ENDED)) {
			Player winner = game.getWinner();
			System.out.println("The winner is: " + winner.getName());
		} else if(gameControllers.checkState(game).equals(GameState.DRAW)) {
			System.out.println("Game is Drawn!!!!");
		}
		
	}
}
