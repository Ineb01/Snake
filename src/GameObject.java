import java.util.ArrayList;

import javax.swing.JComponent;

abstract public class GameObject{
	
	public ArrayList<Coord> coords = new ArrayList<Coord>();
	
	public Type type;
	
	public boolean visible = true;
	
	abstract public void update();
	
}
