package edu.neu.csye6200.ww;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.util.StatusBar;
import gov.nasa.worldwindx.examples.LayerPanel;

/**
 * @author Chen.JL
 *
 */
public class WorldWindApp {
	// application frame
	private JFrame frame;
	private WorldWindowGLCanvas wwd = null;
	private StatusBar statusbar;
	private boolean hasStatusBar = true;
	// Layer Selection
	private LayerPanel layerPanel;
	private boolean hasLayerPanel = true;

	public WorldWindApp() {
		// A Swing App
		frame = new JFrame();
		frame.setVisible(false);
		// red buttons closes
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// we can resize
		frame.setResizable(true);
		frame.setLayout(new BorderLayout());
		
		// Our Global canvas
		wwd = new WorldWindowGLCanvas();
		wwd.setPreferredSize(new Dimension(1000,800));
		// Selection Model
		wwd.setModel(new BasicModel());
		
		// Add a globe
		frame.getContentPane().add(wwd, BorderLayout.CENTER);
		
		if (hasStatusBar) {
			// Create a WW status bar
			statusbar = new StatusBar();
			frame.getContentPane().add(statusbar, BorderLayout.SOUTH);
			// Hood the canvas as the data source
			statusbar.setEventSource(wwd);
		}
		
		if (hasLayerPanel) {
			layerPanel = new LayerPanel(wwd);
			frame.getContentPane().add(layerPanel, BorderLayout.WEST);
		}
		
		frame.pack();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public static void main(String[] args) {
		WorldWindApp wwApp = new WorldWindApp();
		
		// Anonymous inner class - running later on the GUI Thread
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				// Let the UI thread start
				wwApp.getFrame().setVisible(true);
			}		
		});
		wwApp.getFrame().setVisible(true);
	}
}