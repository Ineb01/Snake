public class Apple extends GameObject {

	public Apple() {
		coords.add(new Coord((int)(Math.random()*30), (int)(Math.random()*30)));
		type = Type.APPLE;
	}
	
	@Override
	public void update() {
		
	}

}
