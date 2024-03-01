package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import exception.BotCountException;
import exception.PlayerCountDimensionMismatchException;
import exception.SymbolCountException;
import winningStrategies.WinningStrategy;

public class Game {
	//add parameter
	private Board board;
	private List<Player> players;
	private List<Move> moves;
	private GameState gameState;
	private int currentPlayerIndex;
	private Player winner;
	private WinningStrategy winningStrategies;
	
	public Game(List<Player> players,WinningStrategy winningStrategies, int dimension) {
		super();
		this.board = new Board(dimension);
		this.players = players;
		this.moves = new ArrayList<>();
		this.gameState = GameState.IN_PROGRESS;
		this.currentPlayerIndex = 0;
		this.winningStrategies = winningStrategies;
	}

	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Move> getMoves() {
		return moves;
	}
	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}
	public GameState getGameState() {
		return gameState;
	}
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}
	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	public WinningStrategy getWinningStrategies() {
		return winningStrategies;
	}
	public void setWinningStrategies(WinningStrategy winningStrategies) {
		this.winningStrategies = winningStrategies;
	}
	
	public void displayBoard() {
		this.board.displayBoard();
	}
	
	public static Builder getBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		private List<Player>players;
		private WinningStrategy winningStrategies;
		private int dimension;
		
//		public Builder(List<Player> players, WinningStrategies winningStrategies, int dimension) {
//			super();
//			this.players = players;
//			this.winningStrategies = winningStrategies;
//			this.dimension = dimension;
//		}
		public List<Player> getPlayers() {
			return players;
		}
		public Builder setPlayers(List<Player> players) {
			this.players = players;
			return this;
		}
		public WinningStrategy getWinningStrategies() {
			return winningStrategies;
		}
		public Builder setWinningStrategies(WinningStrategy winningStrategies) {
			this.winningStrategies = winningStrategies;
			return this;
		}
		public int getDimension() {
			return dimension;
		}
		public Builder setDimension(int  dimension) {
			this.dimension = dimension;
			return this;
		}
		
		public Game build() throws SymbolCountException, BotCountException, PlayerCountDimensionMismatchException{
			//here, before creating new object of game, we can validate some attribute 
			validate();
			return new Game(this.players, this.winningStrategies, this.dimension);
		}
		
		public void validate()throws SymbolCountException, BotCountException, PlayerCountDimensionMismatchException {
			//handle for board count exception
			botValidation();
			
			//handle for playercountdimension mismatch validation
			playerDimensionMismatchValidation();
			
			//SymbolValidation
			symbolValidation();
		}
		
		public void botValidation() throws BotCountException {
			int botCount = 0;
			for(Player player: players) {
				if(player.getPlayerType().equals(PlayerType.BOT)) {
					botCount++;
				}
			}
			
			if(botCount > 1) {
				throw new BotCountException();
			}
		}
		
		public void playerDimensionMismatchValidation() throws PlayerCountDimensionMismatchException{
			//here we have to check if number of players should always be equal to dimension - 1(N-1)
			int N = dimension;
			if(players.size() != N - 1) {
				throw new PlayerCountDimensionMismatchException();
			}
		}
		
		public void symbolValidation() throws SymbolCountException {
			Map<Character, Integer> symCount = new HashMap<>();
			
			for(Player player: players) {
				if(!symCount.containsKey(player.getSymbol().getaChar())) {
					symCount.put(player.getSymbol().getaChar(), 0);
				}
				
				symCount.put(player.getSymbol().getaChar(),
						symCount.get(player.getSymbol().getaChar()) + 1);
				
				if(symCount.get(player.getSymbol().getaChar()) > 1) {
					throw new SymbolCountException();
				}
				
				
				
			}
		}
	}

	public void makeMove(Game game) {
		int currentPlayerIdx = game.getCurrentPlayerIndex();
		Player currentPlayer = game.players.get(currentPlayerIdx);
		
		//ask currentPlayer about which cell he has to move on
		Move move = currentPlayer.makeMove(board);
		moves.add(move);
		
		//now I have to check whether current player has won or not based on user selected strategy
		if(winningStrategies.checkWinner(move, board)) {
			game.setWinner(currentPlayer);
			game.setGameState(GameState.ENDED);
			return;
		}
		
		if(moves.size() == board.getSize() * board.getSize()) {
			game.setGameState(GameState.DRAW);
			return;
		}
		
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
	}
	
}
