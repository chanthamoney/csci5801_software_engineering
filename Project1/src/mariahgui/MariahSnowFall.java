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
    private Integer[] randomOpacities = new Integer[1000];
    private int curOpacity = 0;
    private Integer[] randomXPos = new Integer[10000];
    private int curXPos = 0;
    private Double[] randomXIncrement = new Double[100];
    private int curXIncrement = 0;
    private Double[] randomYIncrement = new Double[1000];
    private int curYIncrement = 0;
    private Integer[] randomLayerNum = new Integer[100];
    private int curLayerNum = 0;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2478139986153881780L;

    /** The layers. */
    java.util.List<Layer> layers = new java.util.ArrayList<>();

    /**
     * Instantiates a new Mariah snow fall gui panel.
     */
    public MariahSnowFall() {
	java.util.SplittableRandom random = new java.util.SplittableRandom();
	randomOpacities = Stream.generate(() -> random.nextInt(255)).limit(1000).toArray(Integer[]::new);
	randomXPos = Stream.generate(() -> random.nextInt(3000)).limit(10000).toArray(Integer[]::new);
	randomXIncrement = Stream.generate(() -> random.nextDouble(0.333)).limit(100).toArray(Double[]::new);
	randomYIncrement = Stream.generate(() -> random.nextDouble(2)).limit(1000).toArray(Double[]::new);
	randomLayerNum = Stream.generate(() -> random.nextInt(1)).limit(100).toArray(Integer[]::new);

	setOpaque(false);
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

    /**
     * Makes it snow in the background.
     */
    public void stopSnow() {

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
		snowFlake.yPos += randomYIncrement[curYIncrement++ % 1000];
		snowFlake.xPos += randomXIncrement[curXIncrement++ % 100];
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
	    snowFlakes = Stream.generate(() -> new SnowFlake()).limit(randomLayerNum[curLayerNum++ % 100] + 1)
		    .toArray(SnowFlake[]::new);
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
	    opacity = randomOpacities[curOpacity++ % 1000];
	    xPos = randomXPos[curXPos++ % 10000];
	    yPos = 50;
	}
    }
}