import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class RenderTile extends JPanel {
	public RenderTile() {
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		this.setPreferredSize(new Dimension(25, 25));
		this.setBackground(Color.BLACK);
	}
}
