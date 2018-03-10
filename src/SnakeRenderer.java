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

	public final static int X_SIZE = 30;
	public final static int Y_SIZE = 30;

	public ArrayList<GameObject> renderList = new ArrayList<GameObject>();

	public SnakeRenderer() {

		timer.start();

		Snake snake = new Snake();
		renderList.add(snake);
		renderList.add(new Apple());
		renderList.add(new SquareWallEmpty(new Coord(0, 0), new Coord(X_SIZE - 1, Y_SIZE - 1)));

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

			switch (gameObject.type) {
			case SNAKE:
				Coord head = gameObject.coords.get(0);
				for (GameObject colidingObject : (ArrayList<GameObject>)renderList.clone()) {
					for (Coord colidingCoord : colidingObject.coords) {
						if(head.x == colidingCoord.x && head.y == colidingCoord.y) {
							switch(colidingObject.type) {
							case APPLE:
								((Snake)gameObject).length += 3;
								colidingObject.visible = false;
								removeList.add(colidingObject);
								addList.add(new Apple());
								break;
							default:
								if(colidingCoord != head) {
								timer.stop();
								JOptionPane.showMessageDialog(this, "Loser");
								}
								break;
							}
						}
					}
				}
				break;
			case APPLE:
				Coord apple = gameObject.coords.get(0);
				for (GameObject colidingObject : (ArrayList<GameObject>)renderList.clone()) {
					for (Coord colidingCoord : colidingObject.coords) {
						if(apple.x == colidingCoord.x && apple.y == colidingCoord.y) {
							switch(colidingObject.type) {
							case SNAKE:
							case APPLE:
								break;
							default:
								gameObject.visible = false;
								removeList.add(gameObject);
								addList.add(new Apple());
								break;
							}
						}
					}
				}
				break;
			default:
				break;
			}

			for (Coord coord : gameObject.coords) {
				if (gameObject.visible) {
					switch (gameObject.type) {
					case SNAKE:
						this.mainArray[coord.x][coord.y].setBackground(Color.GREEN);
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
