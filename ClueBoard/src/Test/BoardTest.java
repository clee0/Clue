package Test;

import org.junit.*;

import junit.framework.Assert;

import Board.*;
import Board.RoomCell.*;

public class BoardTest {
	private Board board;
	
	@Before
	public void setup() {
		board = new Board();
		board.loadConfigFiles("Board.csv", "Legend.txt");
	}

	@Test
	public void testRoomsMap() {
		Assert.assertEquals(board.getRooms().size(), 9);
		Assert.assertEquals(board.getRooms().get('K'), "Kitchen");
		Assert.assertEquals(board.getRooms().get('D'), "Dining Room");
		Assert.assertEquals(board.getRooms().get('V'), "Living Room");
		Assert.assertEquals(board.getRooms().get('B'), "Ballroom");
		Assert.assertEquals(board.getRooms().get('R'), "Library");
		Assert.assertEquals(board.getRooms().get('S'), "Study");
		Assert.assertEquals(board.getRooms().get('T'), "Bathroom");
		Assert.assertEquals(board.getRooms().get('Y'), "Lobby");
		Assert.assertEquals(board.getRooms().get('E'), "Bedroom");
		Assert.assertEquals(board.getRooms().get('X'), "Closet");
	}
	
	@Test
	public void testBoard() {
		Assert.assertEquals(board.getNumRows(), 25);
		Assert.assertEquals(board.getNumColumns(), 25);
	}
	
	@Test
	public void testRooms() {
		Assert.assertEquals(board.GetRoomCellAt(2,2).getRoom(),'K');
		Assert.assertEquals(board.GetRoomCellAt(4,12).getRoom(),'D');
		Assert.assertEquals(board.GetRoomCellAt(12,5).getRoom(),'B');
		Assert.assertEquals(board.GetRoomCellAt(22,3).getRoom(),'V');
		Assert.assertEquals(board.GetRoomCellAt(21,13).getRoom(),'Y');
		Assert.assertEquals(board.GetRoomCellAt(1,8).getRoom(),'T');
		Assert.assertEquals(board.GetRoomCellAt(2,22).getRoom(),'S');
		Assert.assertEquals(board.GetRoomCellAt(9,20).getRoom(),'R');
		Assert.assertEquals(board.GetRoomCellAt(18,22).getRoom(),'E');
	}
	
	@Test
	public void testDoors() {
		Assert.assertEquals(board.GetRoomCellAt(4,3).getDoorDirection(),DoorDirection.DOWN);
		Assert.assertEquals(board.GetRoomCellAt(20,3).getDoorDirection(),DoorDirection.UP);
		Assert.assertEquals(board.GetRoomCellAt(9,16).getDoorDirection(),DoorDirection.LEFT);
		Assert.assertEquals(board.GetRoomCellAt(13,6).getDoorDirection(),DoorDirection.RIGHT);
		Assert.assertEquals(board.GetRoomCellAt(4,3).isDoorway(),false);
		
		final int DOORS = 14;
		int numDoors = 0;
		for(int i = 0; i < board.getCells().size(); i++) {
			if(board.getCells().get(i).isDoorway())
				numDoors++;
			}
		Assert.assertEquals(numDoors,DOORS);
	}
	
	@Test
	public void testCalcIndex() {
		Assert.assertEquals(board.calcIndex(0,0),0);
		Assert.assertEquals(board.calcIndex(0,11),11);
		Assert.assertEquals(board.calcIndex(18,0),400);
		Assert.assertEquals(board.calcIndex(14,13),338);
		Assert.assertEquals(board.calcIndex(24,24),624);
		Assert.assertEquals(board.calcIndex(26,24),-1);
		Assert.assertEquals(board.calcIndex(-2,14),-1);
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void testBadConfigFormatException1() throws BadConfigFormatException {
		board.loadConfigFiles("Board.csv", "BadLegendTooManyItems.txt");
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void testBadConfigFormatException2() throws BadConfigFormatException {
		board.loadConfigFiles("Board.csv", "BadLegendTwoStrings.txt");
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void testBadConfigFormatException3() throws BadConfigFormatException {
		board.loadConfigFiles("BadBoardInvalidRoom.csv","Legend.txt");
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void testBadConfigFormatException4() throws BadConfigFormatException {
		board.loadConfigFiles("BadBoardRowsDontMatch.csv","Legend.txt");
	}

}
