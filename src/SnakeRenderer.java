import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeRenderer extends JPanel {

	private RenderTile[][] mainArray;

	private Timer timer = new Timer(200, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			update();
		}
	});

	public final static int X_SIZE = 100;
	public final static int Y_SIZE = 100;

	public ArrayList<GameObject> renderList = new ArrayList<GameObject>();

	public SnakeRenderer() {

		timer.start();

		Snake snake = new Snake();
		renderList.add(snake);
		renderList.add(new Apple());
		renderList.add(new SquareWallEmpty(new Coord(0, 0), new Coord(X_SIZE-1, Y_SIZE-1)));

		this.setFocusable(true);
		this.requestFocus();

		this.setLayout(new GridLayout(Y_SIZE, X_SIZE));

		this.addKeyListener(snake);
		this.mainArray = new RenderTile[X_SIZE][Y_SIZE];
		for (int j = 0; j < mainArray[0].length; j++) {
			for (int i = 0; i < mainArray.length; i++) {
				mainArray[i][j] = new RenderTile();
				this.add(mainArray[i][j]);
			}
		}

		update();

	}

	public void update() {
		for (int j = 0; j < mainArray[0].length; j++) {
			for (int i = 0; i < mainArray.length; i++) {
				this.mainArray[i][j].setBackground(Color.BLACK);
			}
		}
		ArrayList<GameObject> addList = new ArrayList<GameObject>();
		ArrayList<GameObject> removeList = new ArrayList<GameObject>();
		for (GameObject gameObject : renderList) {
			gameObject.update();
			for (Coord coord : gameObject.coords) {
				switch(gameObject.type) {
				case SNAKE:

					for (GameObject colidingObject : (ArrayList<GameObject>) renderList.clone()) {
						switch (colidingObject.type) {
						case APPLE:
							if(colidingObject.visible) {
							if (colidingObject.coords.get(0).x == coord.x
									&& colidingObject.coords.get(0).y == coord.y) {
								colidingObject.visible = false;
								removeList.remove(colidingObject);
								((Snake) gameObject).length += 2;
								addList.add(new Apple());
							}
							}
							break;
						case SNAKE:
							if (gameObject.coords.get(0) != coord) {
								if (gameObject.coords.get(0).x == coord.x
										&& gameObject.coords.get(0).y == coord.y) {
									JOptionPane.showMessageDialog(this, "Loser");
									timer.stop();
								}
							}
							break;

						case WALL:
							for (Coord colidingCoord : colidingObject.coords) {
								if (colidingCoord.x == coord.x
										&& colidingCoord.y == coord.y) {
									JOptionPane.showMessageDialog(this, "Loser");
									timer.stop();
								}
							}
							break;
						default:
							break;
						}
					}
					break;
					default:break;
					
				}
				if (gameObject.visible) {
					switch (gameObject.type) {
					case SNAKE:
						if (gameObject.coords.indexOf(coord) == 0) {
							this.mainArray[coord.x][coord.y].setBackground(Color.GREEN);
						} else {
							this.mainArray[coord.x][coord.y].setBackground(Color.BLUE);
						}
						break;
					case APPLE:
						this.mainArray[coord.x][coord.y].setBackground(Color.RED);
						break;

					case WALL:
						this.mainArray[coord.x][coord.y].setBackground(Color.YELLOW);
						break;

					default:
						break;
					}
				}
			}
		}
		renderList.addAll(addList);
		renderList.removeAll(removeList);

	}

}
