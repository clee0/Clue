package Board;

public class RoomCell extends BoardCell {
	public enum DoorDirection {UP,DOWN,LEFT,RIGHT,NONE;}
	
	private DoorDirection doorDirection;
	private char room;
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public char getRoom() {
		return room;
	}

	@Override
	public boolean isRoom() {return true;}
}