package Board;

mport java.util.*;

public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character,String> rooms;
	private int numRows;
	private int numColumns;
	
	public Board() {
		this.cells = new ArrayList<BoardCell>();
		this.rooms = new HashMap<Character,String>();
		this.numRows = 0;
		this.numColumns = 0;
	}
	
	public void loadConfigFiles() {
		
	}
	
	public int calcIndex(int row, int column) {
		
	}
	
	public RoomCell GetRoomCellAt(int row, int column) {
		return null;
	}

	public ArrayList<BoardCell> getCells() {
		return cells;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	
	public BoardCell getBoardCell() {
		
	}
}