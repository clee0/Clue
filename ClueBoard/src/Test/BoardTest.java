package Test;

import org.junit.*;
import java.util.*;
import junit.framework.Assert;

import Board.*;

public class BoardTest {
	
	@Before
	public void setup() {
		Board board = new Board();
		board.loadConfigFiles();
	}

	@Test
	public void testRoomsMap() {
		Assert.assertEquals(board.getRooms().size(), 9);
	}

}
