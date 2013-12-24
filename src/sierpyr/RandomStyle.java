package sierpyr;

import java.awt.Graphics;


/**
 * Render style of a triangle.
 *
 * @author jure
**/
public class RandomStyle implements Style {
	String inside;			// color of inside triangles
	String last;			// last-level color

	RandomStyle(String settings) {
		String[] params = settings.split("[ \t]");
		this.inside = params[1];
		this.last = params[2];
	}

	@Override
	public boolean inside(Graphics g, int depth) {
		if ("none".equals(inside)) return false;
		else g.setColor(ColorFactory.randColor(inside));
		return true;
	}

	@Override
	public boolean last(Graphics g) {
		if ("none".equals(last)) return false;
		g.setColor(ColorFactory.randColor(last));
		return true;
	}
}