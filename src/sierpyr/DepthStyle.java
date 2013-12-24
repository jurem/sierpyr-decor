package sierpyr;

import java.awt.Graphics;


/**
 * Render style of a triangle.
 *
 * @author jure
**/
public class DepthStyle implements Style {
	int maxDepth;
	String inside;			// color of inside triangles
	String last;			// last-level triangles color

	DepthStyle(int maxDepth, String settings) {
		this.maxDepth = maxDepth;
		String[] params = settings.split("[ \t]");
		this.inside = params[1];
		this.last = params[2];
	}

	@Override
	public boolean inside(Graphics g, int depth) {
		if ("none".equals(inside)) return false;
		g.setColor(ColorFactory.depthColor(maxDepth, maxDepth - depth, inside));
		return true;
	}

	@Override
	public boolean last(Graphics g) {
		if ("none".equals(last)) return false;
		g.setColor(ColorFactory.randColor(last));
		return true;
	}
}