package models;

import java.util.Scanner;

public class Player {
	private String name;
	private int id;
	private Symbol symbol;
	private PlayerType playerType;
	public PlayerType getPlayerType() {
		return playerType;
	}
	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	public Move makeMove(Board board) {
		// TODO Auto-generated method stub
		//take input from user
		Scanner scan = new Scanner(System.in);
		System.out.println("enter row number");
		int row = scan.nextInt();
		
		System.out.println("enter col number");
		int col = scan.nextInt();
		
		Cell cell = board.getBoard().get(row).get(col);
		cell.setCellState(CellState.FILLED);
		cell.setPlayer(this);
		Move move = new Move();
		move.setCell(cell);
		move.setPlayer(this);
		return move;
		
	}
	
}
