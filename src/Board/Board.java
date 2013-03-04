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
	
	private Set<Integer> targets;

	public Board() {
		this.cells = new ArrayList<BoardCell>();
		this.rooms = new HashMap<Character,String>();
		this.numRows = 0;
		this.numColumns = 0;
		this.targets = new HashSet<Integer>();
	}

	public void loadConfigFiles() {
		// Given test config filenames
		String legend = "ClueLegend.txt";
		String board = "ClueLayout.csv";
		// Default config filenames
		//String legend = "Legend.txt";
		//String board = "Board.csv";
		try{
			this.loadLegendConfig(legend);			
			this.loadBoardConfig(board);
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
			FileWriter writer = null;
			try {
				writer = new FileWriter("Error Log.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			BufferedWriter out = new BufferedWriter(writer);
			try {
				out.write(e.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch(BadConfigFormatException e) {
			System.out.println(e);
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}

	public void loadLegendConfig(String legend) throws FileNotFoundException, BadConfigFormatException {
		File infile = new File(legend);
		Scanner scanner = new Scanner(infile);
		while(scanner.hasNextLine()){
            String[] item = scanner.nextLine().split(",");
            if(item.length != 2 || item[0].length() != 1)
                throw new BadConfigFormatException(errorType.INVALID_LEGEND);
            item[1] = item[1].trim();
            rooms.put(item[0].charAt(0),item[1]);
		}
	}

	public void loadBoardConfig(String board) throws FileNotFoundException, BadConfigFormatException {
		FileReader reader = new FileReader(board);
		Scanner scanner = new Scanner(reader);
		this.numRows = 0;
		for(int i = 0; scanner.hasNextLine(); i++) {
			this.numRows++;
			String[] row = scanner.nextLine().split(",");
			if(i == 0)
				this.numColumns = row.length;
			else
				if(row.length != this.numColumns)
					throw new BadConfigFormatException(errorType.INVALID_ROWS);
			for(int j = 0; j < row.length; j++) {
				if(this.rooms.containsKey(row[j].charAt(0))) {
					if(row[j].equalsIgnoreCase("W"))
						cells.add(new WalkwayCell(i,j));
					else if(row[j].length() == 2) {
	                    switch(row[j].charAt(1))
	                    {
		                    case 'U': cells.add(new RoomCell(i,j,row[j].charAt(0),DoorDirection.UP));
	                                  break;
	                        case 'D': cells.add(new RoomCell(i,j,row[j].charAt(0),DoorDirection.DOWN));
	                                  break;
	                        case 'L': cells.add(new RoomCell(i,j,row[j].charAt(0),DoorDirection.LEFT));
	                                  break;
	                        case 'R': cells.add(new RoomCell(i,j,row[j].charAt(0),DoorDirection.RIGHT));
	                                  break;
	                        case 'N': cells.add(new RoomCell(i,j,row[j].charAt(0),DoorDirection.NONE));
                            		  break;
	                    }
					}
					else
						cells.add(new RoomCell(i,j,row[j].charAt(0),DoorDirection.NONE));			
				}
				else
					throw new BadConfigFormatException(errorType.INVALID_ROOM);
			}
		}
		return;
	}

	public int calcIndex(int row, int column) {
        if(row < 0 || column < 0 || row >= numRows || column >= numColumns)
            return -1;
		return (numColumns*row + column);
	}

	public RoomCell getRoomCellAt(int row, int column) {
        int index = calcIndex(row, column);
        if(index < 0 || !cells.get(index).isRoom())
            return null;
        return (RoomCell) cells.get(index);
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

	public BoardCell getCellAt(int index) {
		return cells.get(index);
	}
	
	// Part II Stubs
	public void calcAdjacencies() {
	
	}
	
	public void startTargets(int row, int column, int steps) {
		
	}
	
	public Set<Integer> getTargets() {
		return targets;
	}
	
	public LinkedList<Integer> getAdjList(int row, int column) {
		return null;
	}
}
