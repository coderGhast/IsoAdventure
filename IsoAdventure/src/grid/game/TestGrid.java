package grid.game;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class TestGrid {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		createNewFrame();
	}

	public static void createNewFrame() {
		try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					build();
				}
			});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Exception encountered on attempt to build application",
					"Error - Not able to build application",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void build(){
		Controller application = new Controller();
	}
		
	

}
