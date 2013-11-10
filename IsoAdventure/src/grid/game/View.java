package grid.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class View extends JFrame implements KeyListener {

	MapCanvas canvas;
	JScrollPane scrollPane;
	JPanel statusBar;
	JPanel characterViewBar;

	public View(int givenMapHeight, int givenMapWidth) {
		setTitle("Test Draw Grid");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setupMapCanvas(givenMapHeight, givenMapWidth);
		setupStatusBar();
		setupCharacterViewBar();
		setSize(700, 700);

		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		addKeyListener(this);
		scrollPane = new JScrollPane(canvas,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane, BorderLayout.CENTER);
	}

	protected void setupMapCanvas(int givenMapHeight, int givenMapWidth) {
		canvas = new MapCanvas(givenMapHeight, givenMapWidth);
		canvas.setPreferredSize(new Dimension((givenMapWidth * canvas.getPolygonWidth()) + (canvas.getPolygonWidth()), 
				(givenMapHeight * canvas.getPolygonHeight()) + ((givenMapHeight * canvas.getPolygonHeight()) / 2)));
	}

	private void setupStatusBar(){
		statusBar = new JPanel();
		statusBar.setPreferredSize(new Dimension(100,100));
		add(statusBar, BorderLayout.SOUTH);
	}
	
	private void setupCharacterViewBar(){
		characterViewBar = new JPanel();
		characterViewBar.setPreferredSize(new Dimension(100,50));
		add(characterViewBar, BorderLayout.WEST);
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			canvas.moveActive(canvas.getActiveX() - 1, canvas.getActiveY(),
					canvas.getActiveX(), canvas.getActiveY());
		}
		if (event.getKeyCode() == KeyEvent.VK_DOWN
				|| event.getKeyCode() == KeyEvent.VK_KP_DOWN) {
			canvas.moveActive(canvas.getActiveX() + 1, canvas.getActiveY(),
					canvas.getActiveX(), canvas.getActiveY());
		}
		if (event.getKeyCode() == KeyEvent.VK_LEFT
				|| event.getKeyCode() == KeyEvent.VK_KP_LEFT) {
			canvas.moveActive(canvas.getActiveX(), canvas.getActiveY() - 1,
					canvas.getActiveX(), canvas.getActiveY());
		}
		if (event.getKeyCode() == KeyEvent.VK_RIGHT
				|| event.getKeyCode() == KeyEvent.VK_KP_RIGHT) {
			canvas.moveActive(canvas.getActiveX(), canvas.getActiveY() + 1,
					canvas.getActiveX(), canvas.getActiveY());
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {

	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

}
