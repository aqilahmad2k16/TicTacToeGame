package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private int size;
	private List<List<Cell>> board;
	
	
	public Board(int dimension) {
		super();
		this.size = dimension;
		//let's create board
		board = new ArrayList<>();
		for(int i=0; i<dimension; i++) {
			List<Cell> row = new ArrayList<>();
			for(int j=0; j<dimension; j++) {
				Cell cell = new Cell();
				cell.setRow(i);
				cell.setCol(j);
				cell.setCellState(CellState.EMPTY);
				row.add(cell);
			}
			
			board.add(row);
		}
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<List<Cell>> getBoard() {
		return board;
	}
	public void setBoard(List<List<Cell>> board) {
		this.board = board;
	}
	public void displayBoard() {
		// TODO Auto-generated method stub
		System.out.println("Displaying board");
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				Cell cell = board.get(i).get(j);
				
				if(cell.getPlayer() == null) {
					System.out.println("-");
				}else
					System.out.println(cell.getPlayer().getSymbol().getaChar());
			}
			System.out.println();
		}
		
	}
	
	
	
}
