
public class SquareWall extends GameObject{
	public SquareWall(Coord start, Coord end) {
		type = Type.WALL;
		for(int x = start.x;x<=end.x;x++) {
			for(int y = start.y;y<=end.y;y++) {
				coords.add(new Coord(x,y));
			}
		}
			
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
