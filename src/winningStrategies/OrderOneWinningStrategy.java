package winningStrategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Board;
import models.Move;
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
		
		return false;
	}

	@Override
	public void handleUndu(Move move, Board board) {
		// TODO Auto-generated method stub
		
	}

}
