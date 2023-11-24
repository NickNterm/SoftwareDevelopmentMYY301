package app.gui;

import javax.swing.JFrame;

public class AppStarter {

	public static void main(String[] args) {
		//DesktopFrame rootFrame = new DesktopFrame();  
		JFrameLevel00RootFrame rootFrame = new JFrameLevel00RootFrame();
		rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rootFrame.setSize(1200, 800); 
		rootFrame.setVisible(true);     

		
	}

}
