// File:         MariahSnowFall.java
// Created:      2018/11/09
// Last Changed: $Date: 2018/11/09 8:20:09 $
// Author:       <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package mariahgui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Stream;

import javax.swing.JPanel;

/**
 * Extends the GUI JPanel to have a snow theme background.
 */
class MariahSnowFall extends JPanel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2478139986153881780L;

    /** The layers. */
    java.util.List<Layer> layers = new java.util.ArrayList<>();

    /** The random generator for realism. */
    private java.util.Random random = new java.util.Random();

    /**
     * Instantiates a new Mariah snow fall gui panel.
     */
    public MariahSnowFall() {
	setOpaque(false);
	snow();
    }

    /**
     * Makes it snow in the background.
     */
    public void snow() {
	ActionListener al = (ActionEvent ae) -> {
	    layers.add(new Layer());
	    repaint();
	    if (layers.size() > 9) {
		// Remove pointer to old layer for garbage collection
		layers.remove(0);
		layers.add(new Layer());
	    }
	};
	// Refresh often to prevent sporadic behavior at 60fps
	new javax.swing.Timer(16, al).start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	for (int x = 0, y = layers.size(); x < y; x++) {
	    Layer layer = (Layer) layers.get(x);
	    for (int z = 0, zz = layer.snowFlakes.length; z < zz; z++) {
		SnowFlake snowFlake = layer.snowFlakes[z];
		g.setColor(new Color(255, 255, 255, snowFlake.opacity));
		g.fillOval(snowFlake.xPos, snowFlake.yPos, 8, 8);
		snowFlake.yPos += 2 * random.nextFloat();
		snowFlake.xPos += 0.3 * random.nextFloat();
	    }
	}
    }

    /**
     * Represents a layer of snow flakes in the background.
     */
    private class Layer {
	/** The snow flakes inside this layer. */
	private SnowFlake[] snowFlakes;

	/**
	 * Instantiates a new layer of snow flakes.
	 */
	public Layer() {
	    snowFlakes = Stream.generate(() -> new SnowFlake()).limit(random.nextInt(1) + 1).toArray(SnowFlake[]::new);
	}
    }

    /**
     * Represents a single snow flake in the background.
     */
    private class SnowFlake {
	int opacity;

	/**
	 * The position of the snow flake as x-y coordinates. Initially start at the top
	 * of the screen.
	 */
	int xPos;

	/** The y. */
	int yPos;

	/**
	 * Instantiates a new snow flake.
	 */
	public SnowFlake() {
	    opacity = random.nextInt(255);
	    xPos = random.nextInt(3000);
	    yPos = 0;
	}
    }
}