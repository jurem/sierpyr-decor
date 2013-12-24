package sierpyr;

import java.awt.Color;
import java.util.Random;

/**
 * A helper class for generating colors.
 *
 * @author jure
**/
public class ColorFactory {
	static Random rand = new Random();

	static int rndi(int min, int max) {
		int col = min + rand.nextInt(max - min + 1);
		if (col < 0) col = -col;
		return col % 256;
	}

	static int rndc(int center, int range) {
		return rndi(center - range, center + range);
	}

	public static Color randColor(String palette) {
		int r, g, b;
		if ("red".equals(palette)) {
			r = rndi(130, 255); g = rndi(20, 2*r/3); b = rndc(g, 30);
		} else if ("green".equals(palette)) {
			g = rndi(130, 255); r = rndi(20, 2*g/3); b = rndc(r, 30);
		} else if ("blue".equals(palette)) {
			b = rndi(130, 255); r = rndi(20, 2*b/3); g = rndc(r, 30);
		} else if ("yellow".equals(palette)) {
			r = rndi(130, 255); g = r; b = rndi(20, 2*r/3);
		} else if ("cyan".equals(palette)) {
			g = rndi(130, 255); b = g; r = rndi(20, 2*g/3);
		} else if ("magenta".equals(palette)) {
			r = rndi(130, 255); b = r; g = rndi(20, 2*r/3);
		} else {
			r = rndi(50, 255); g = rndi(50, 255); b = rndi(50, 255);
		}
		return new Color(r, g, b);
	}

	public static Color depthColor(int maxDepth, int depth, String palette) {
		int wr = 200 / maxDepth, wg = 100 / maxDepth;
		int r = 255 - depth * wr, g = 150 - depth*wg, b = g;
		if ("red".equals(palette)) {}
		else if ("green".equals(palette)) { g = r; r = b; }
		else if ("blue".equals(palette)) { b = r; r = g; }
		else if ("yellow".equals(palette)) { g = r; }
		else if ("cyan".equals(palette)) { g = r;  r = b; b = g; }
		else if ("magenta".equals(palette)) { b = r; }
		else { g = r; }
		return new Color(r, g, b);
	}
}