package sierpyr;

import java.awt.Graphics;


/**
 * Render style of a triangle.
 *
 * @author jure
**/
public class EvenOddStyle implements Style {
	int maxDepth;
	String even;			// color of inside triangles on even depth
	String odd;				// color of inside triangles on odd depth
	String last;			// last-level triangles color

	EvenOddStyle(int maxDepth, String settings) {
		this.maxDepth = maxDepth;
		String[] params = settings.split("[ \t]");
		this.even = params[1];
		this.odd = params[2];
		this.last = params[3];
	}

	@Override
	public boolean inside(Graphics g, int depth) {
		String pal = depth % 2 == 0 ? even : odd;
		if ("none".equals(pal)) return false;
		g.setColor(ColorFactory.depthColor(maxDepth, maxDepth - depth, pal));
		return true;
	}

	@Override
	public boolean last(Graphics g) {
		if ("none".equals(last)) return false;
		g.setColor(ColorFactory.randColor(last));
		return true;
	}
}