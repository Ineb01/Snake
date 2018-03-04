
public class SquareWallEmpty extends GameObject {

	public SquareWallEmpty(Coord start, Coord end) {
		type = Type.WALL;
		int x,y;
		
		y = start.y;
		for(x = start.x;x<=end.x;x++) {
			coords.add(new Coord(x, y));
		}
		
		y = end.y;
		for(x = start.x;x<=end.x;x++) {
			coords.add(new Coord(x, y));
		}
		
		x = start.x;
		for(y = start.y+1;y<end.y;y++) {
			coords.add(new Coord(x, y));
		}
		
		x = end.x;
		for(y = start.y+1;y<end.y;y++) {
			coords.add(new Coord(x, y));
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
