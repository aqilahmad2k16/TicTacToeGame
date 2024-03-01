package winningStrategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;
import models.Symbol;

public class OrderOneWinningStrategy implements WinningStrategy{
	
	private List<Map<Symbol, Integer>> rows;
	private List<Map<Symbol, Integer>> cols;
	
	private Map<Symbol, Integer> diagonal;
	private Map<Symbol, Integer> reverseDiagonal;
	
	public OrderOneWinningStrategy(int size) {
		rows = new ArrayList<>();
		cols = new ArrayList<>();
		
		diagonal = new HashMap<>();
		reverseDiagonal = new HashMap<>();
		
		for(int i=0; i<size; i++) {
			rows.add(new HashMap<>());
			cols.add(new HashMap<>());
		}
	}

	@Override
	public boolean checkWinner(Move move, Board board) {
		// TODO Auto-generated method stub
		Player player = move.getPlayer();
		Cell cell = move.getCell();
		int row = cell.getRow();
		int col = cell.getCol();
		Map<Symbol, Integer> rowMap = rows.get(row);
		Map<Symbol, Integer> colMap = cols.get(col);
		
		//add player symbol to corresponding row and col
		rowMap.put(player.getSymbol(), rowMap.getOrDefault(player.getSymbol(), 0) + 1);
		
		//add to col
		colMap.put(player.getSymbol(), colMap.getOrDefault(player.getSymbol(), 0) + 1);
		
		//add to diangoal or reverse diagonal based on condition
		if(row == col) {
			diagonal.put(player.getSymbol(), diagonal.getOrDefault(player.getSymbol(), 0) + 1);
		}
		
		//add to reverse diagonal
		if(row + col == board.getSize() - 1) {
			reverseDiagonal.put(player.getSymbol(), reverseDiagonal.getOrDefault(player.getSymbol(), 0) + 1);
		}
		
		//check for winner
		return rowMap.get(player.getSymbol()) == board.getSize() || colMap.get(player.getSymbol()) == board.getSize() ||
				diagonal.getOrDefault(player.getSymbol(), 0) == board.getSize() || reverseDiagonal.getOrDefault(player.getSymbol(), 0) == board.getSize();
	}

	@Override
	public void handleUndu(Move move, Board board) {
		Player player = move.getPlayer();
		Cell cell = move.getCell();
		int row = cell.getRow();
		int col = cell.getCol();
		Map<Symbol, Integer> rowMap = rows.get(row);
		Map<Symbol, Integer> colMap = cols.get(col);
		
		//undu from rowMap and colMap 
		rowMap.put(player.getSymbol(), rowMap.getOrDefault(player.getSymbol(), 0) - 1);
		
		//undu from colMap
		colMap.put(player.getSymbol(), colMap.getOrDefault(player.getSymbol(), 0) - 1);
		
		if(row == col) {
			diagonal.put(player.getSymbol(), diagonal.getOrDefault(player.getSymbol(), 0) - 1);
		}
		
		if(row + col == board.getSize() - 1) {
			reverseDiagonal.put(player.getSymbol(), reverseDiagonal.getOrDefault(player.getSymbol(), 0) - 1);
		}
		
	}

}
