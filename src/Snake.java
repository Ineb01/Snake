import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake extends GameObject implements KeyListener{
	
	Direction direction = Direction.IDLE;
	
	Coord pos = new Coord(3,3);
	
	int length = 1;
	
	public Snake() {
		coords.add(pos);
		type = Type.SNAKE;
	}

	@Override
	public void update() {
		switch (direction) {
		case UP:
			pos.y--;
			break;
			
		case DOWN:
			pos.y++;
			break;
			
		case LEFT:
			pos.x--;
			break;
			
		case RIGHT:
			pos.x++;
			break;

		default:
			break;
		}
		
		coords.add(0, new Coord(pos.x, pos.y));
		
		if(coords.size() > length)
			coords.remove(coords.size() - 1);
			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				direction = Direction.UP;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				direction = Direction.DOWN;
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				direction = Direction.LEFT;
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				direction = Direction.RIGHT;
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
