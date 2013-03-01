package Board;

import java.util.*;
import java.io.*;

import Board.BadConfigFormatException.errorType;
import Board.RoomCell.DoorDirection;

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
	
	public void loadConfigFiles(String board, String legend) {

	}
	
	public void loadBoardConfig(String board) throws FileNotFoundException, BadConfigFormatException {
		FileReader reader = new FileReader(board);
		Scanner scanner = new Scanner(reader);
		Set<Character> validRooms = this.rooms.keySet();
		Map<Character,DoorDirection> doors = new HashMap<Character,DoorDirection>();
		for(int i = 0; scanner.hasNextLine(); i++) {
			String[] row = scanner.nextLine().split(",");
			if(i == 0)
				this.numColumns = row.length;
			else
				if(row.length != this.numColumns)
					throw new BadConfigFormatException(errorType.INVALID_ROWS);
			for(int j = 0; j < row.length; j++) {
				if(validRooms.contains(row[j])) {
					if(row[j].equalsIgnoreCase("W"))
						cells.add(new WalkwayCell(i,j));
					else
						cells.add(new RoomCell(i,j,row[j].charAt(0)));
				}
				else if(row[j].length() == 2 && (row[j].charAt(1) == 'U' || row[j].charAt(1) == 'D' ||
						row[j].charAt(1) == 'L' || row[j].charAt(1) == 'R')) {
					
				}
			}
		}
	}
	
	public int calcIndex(int row, int column) {
		return 0;
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
		return null;
	}
}