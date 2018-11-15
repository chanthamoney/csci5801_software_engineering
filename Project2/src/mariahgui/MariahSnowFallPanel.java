/**
 * File: MariahSnowFallPanel.java
 * Date Created: 11/08/2018
 * Last Update: Nov 13, 2018 6:47:37 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

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
class MariahSnowFallPanel extends JPanel {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2478139986153881780L;

    /** Randomly generates opacities for snowflakes. */
    private Integer[] randomOpacities = new Integer[1024];

    /** The current index to grab from the random opacities. */
    private int curOpacity = 0;

    /** Randomly generates x positions for snowflakes. */
    private Integer[] randomXPos = new Integer[8192];

    /** The current index to grab from the random x positions. */
    private int curXPos = 0;

    /** Randomly generates x velocities for snowflakes. */
    private Double[] randomXVel = new Double[1024];

    /** The current index to grab from the random x velocities. */
    private int curXVel = 0;

    /** Randomly generates y velocities for snowflakes. */
    private Double[] randomYVel = new Double[1024];

    /** The current index to grab from the random y velocities. */
    private int curYVel = 0;

    /** Randomly generates maximum number of snow layers. */
    private Integer[] randomLayerNum = new Integer[128];

    /** The current index to grab from the random layer numbers. */
    private int curLayerNum = 0;

    /** The layers of snow on the panel. */
    private java.util.List<Layer> layers = new java.util.ArrayList<>();

    /**
     * Instantiates a new Mariah snow fall GUI panel.
     */
    public MariahSnowFallPanel() {
	java.util.SplittableRandom random = new java.util.SplittableRandom();
	randomOpacities = Stream.generate(() -> random.nextInt(255)).limit(1024).toArray(Integer[]::new);
	randomXPos = Stream.generate(() -> random.nextInt(-100, 3000)).limit(8192).toArray(Integer[]::new);
	randomXVel = Stream.generate(() -> random.nextDouble(2)).limit(1024).toArray(Double[]::new);
	randomYVel = Stream.generate(() -> random.nextDouble(1.5) + 0.5).limit(1024).toArray(Double[]::new);
	randomLayerNum = Stream.generate(() -> random.nextInt(1)).limit(128).toArray(Integer[]::new);

	setOpaque(false);
    }

    /**
     * Makes it snow in the background.
     */
    public void snow() {
	ActionListener al = (ActionEvent ae) -> {
	    layers.add(new Layer());
	    repaint();
	    if (layers.size() > 50) {
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
	    Layer layer = layers.get(x);
	    for (int z = 0, zz = layer.snowFlakes.length; z < zz; z++) {
		SnowFlake snowFlake = layer.snowFlakes[z];
		g.setColor(new Color(255, 255, 255, snowFlake.opacity));
		g.fillOval(snowFlake.xPos, snowFlake.yPos, 8, 8);
		snowFlake.yPos = (int) (snowFlake.yPos + snowFlake.yVel);
		snowFlake.xPos = (int) (snowFlake.xPos + snowFlake.xVel);
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
	    snowFlakes = Stream.generate(SnowFlake::new).limit(randomLayerNum[curLayerNum++ & 127] + 1)
		    .toArray(SnowFlake[]::new);
	}
    }

    /**
     * Represents a single snow flake in the background.
     */
    private class SnowFlake {

	/** The opacity. */
	int opacity;

	/** The x coordinate of the snow flake on the panel. */
	int xPos;

	/** The y coordinate of the snow flake on the panel. */
	int yPos;

	/** The x coordinate of the snow flake on the panel. */
	double xVel;

	/** The y coordinate of the snow flake on the panel. */
	double yVel;

	/**
	 * Instantiates a new snow flake.
	 */
	public SnowFlake() {
	    opacity = randomOpacities[curOpacity++ & 1023];
	    xPos = randomXPos[curXPos++ % 8191];
	    yPos = 40;
	    xVel = randomXVel[curXVel++ & 1023];
	    yVel = randomYVel[curYVel++ & 1023];
	}
    }
}