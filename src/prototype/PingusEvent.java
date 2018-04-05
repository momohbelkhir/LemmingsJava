package prototype;

import java.util.List;

public class PingusEvent {

	public static enum ChangeType {
		ENTER, LEAVE
	}

	private List <Coordinate> listCoordinate;  //faire liste
	private ChangeType changeType;

	public PingusEvent(List <Coordinate> coordinate, ChangeType changeType) {
		this.listCoordinate=coordinate;
		this.changeType = changeType;
	}

	public ChangeType getChangeType() {
		return changeType;
	}


	
	@Override
	public String toString() {
		ChangeType type = changeType;
		String s= " ";
		String a;
		for(Coordinate c : listCoordinate)
			 s= s+  " (" + c.getX() + ", " + c.getY() + " )";
		
		if (type == ChangeType.ENTER)
			a = "Enters";
		else
			a = "Leaves";
		 return a+" "+s;
	}

	public List <Coordinate> getListCoordinate() {
		return listCoordinate;
	}

	
	
}
