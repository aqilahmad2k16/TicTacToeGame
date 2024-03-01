package controllers;

import java.util.List;

import exception.BotCountException;
import exception.PlayerCountDimensionMismatchException;
import exception.SymbolCountException;
import models.Game;
import models.GameState;
import models.Player;
import winningStrategies.WinningStrategy;

public class GameControllers {
	
	public Game startGame(int dimension, List<Player> players, WinningStrategy winningStrategies) throws SymbolCountException, PlayerCountDimensionMismatchException, BotCountException{
		//this method will return object of game, here will be using builder design pattern to create object
		return Game.getBuilder()
				.setDimension(dimension)
				.setPlayers(players)
				.setWinningStrategies(winningStrategies)
				.build();
	}
	
	public void makeMove(Game game) {
		game.makeMove(game);
	}
	public void displayBoard(Game game) {
		game.displayBoard();
	}
	
	public void undu(Game game) {
		game.undo(game);
	}

	public  GameState checkState(Game game) {
		// TODO Auto-generated method stub
		return game.getGameState();
	}
}
