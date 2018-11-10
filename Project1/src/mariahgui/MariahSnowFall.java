package mariahgui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

class MariahSnowFall extends JPanel {
    private static final long serialVersionUID = 2478139986153881780L;
    java.util.List<Layer> layers = new java.util.ArrayList<>();

    public MariahSnowFall() {
	setOpaque(false);
	snow();
    }

    public void snow() {
	ActionListener al = (ActionEvent ae) -> {
	    layers.add(new Layer());
	    repaint();
	    if (layers.size() > 9) {
		layers.remove(0);
		layers.add(new Layer());
	    }
	};
	new javax.swing.Timer(10, al).start();
    }

    @Override
    public void paintComponent(Graphics g) {
	java.util.Random r = new java.util.Random();
	super.paintComponent(g);
	g.setColor(Color.WHITE);
	for (int x = 0, y = layers.size(); x < y; x++) {
	    Layer layer = (Layer) layers.get(x);
	    for (int z = 0, zz = layer.snowFlakes.length; z < zz; z++) {
		SnowFlake snowFlake = layer.snowFlakes[z];
		g.fillOval(snowFlake.x, snowFlake.y, 8, 8);
		snowFlake.y += 2 * r.nextFloat();
		snowFlake.x += 0.3 * r.nextFloat();
	    }
	}
    }
}

class Layer {
    static java.util.Random r = new java.util.Random();

    SnowFlake[] snowFlakes;

    public Layer() {
	snowFlakes = new SnowFlake[r.nextInt(1) + 1];
	for (int x = 0, y = snowFlakes.length; x < y; x++) {
	    snowFlakes[x] = new SnowFlake();
	}
    }
}

class SnowFlake {
    static java.util.Random r1 = new java.util.Random();
    int x;
    int y;

    public SnowFlake() {
	x = r1.nextInt(3000);
	y = 0;
    }
}