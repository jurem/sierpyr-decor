package sierpyr;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;


/**
 * A helper class for drawing Sierpinski triangle.
 *
 * @author jure
**/
class Draw {
	static int getAspectHeight(int width) {
		return (int) Math.round(width * Math.sqrt(3.0) / 2.0);
	}

	static int getAspectWidth(int height) {
		return (int) Math.round(2 * height / Math.sqrt(3.0));
	}

    static Point midpoint(Point a, Point b) {
        return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
    }

	static void drawTriangle(Graphics g, Point a, Point b, Point c) {
		//g.drawLine(a.x, a.y, b.x, b.y);
		//g.drawLine(a.x, a.y, c.x, c.y);
		//g.drawLine(b.x, b.y, c.x, c.y);
		int[] xs = { a.x, b.x, c.x };
		int[] ys = { a.y, b.y, c.y };
		g.fillPolygon(xs, ys, 3);
	}

	static void draw(Graphics g, Style style, int depth, Point a, Point b, Point c) {
		if (depth <= 0) {
			// final triangle
			if (style.last(g)) drawTriangle(g, a, b, c);
		} else {
			Point ab = midpoint(a, b);
			Point bc = midpoint(b, c);
			Point ac = midpoint(a, c);
			// inside triangle
			if (style.inside(g, depth)) drawTriangle(g, ab, bc, ac);
			// recursion
			draw(g, style, depth-1,  a, ab, ac);
			draw(g, style, depth-1, ab,  b, bc);
			draw(g, style, depth-1, ac, bc, c);
		}	
	}
}


/**
 * Draws one to six triangles.
 *
 * @author jure
**/
public class Sierpinski {
	int length;			// side length of a triangle
	int alt;			// height of a triangle
	int maxDepth;		// depth of recursion
	int count;			// number of triangles
	int width;			// width of canvas
	int height;			// height of canvas
	Point[] points;		// precalculated points
	Style[] styles;		// styles of the triangles
	// glue
	static int GLUE = 60;
	boolean glue;		// leave space for glue
	Point[] gluePoints;

	Sierpinski(int length, int maxDepth, int count, boolean glue) {
		init(length, maxDepth, count, glue);
	}

	void init(int length, int maxDepth, int count, boolean glue) {
		this.length = length;
		this.alt = Draw.getAspectHeight(length);
		this.maxDepth = maxDepth;
		this.count = count;
		switch (count) {
			case 1: this.width = length; break;
			case 2: this.width = 3*length/2; break;
			default: this.width = 2*length; break;
		}
		this.height = count <= 3 ? alt : 2*alt;
		initPoints();
		initStyles();
		initGlue(glue);
	}

	void initPoints() {
		points = new Point[8];
		int x = length, y = alt;
		points[0] = new Point(x, y);
		points[1] = new Point(x - length, y);
		points[2] = new Point(x - length/2, y - alt);
		points[3] = new Point(x + length/2, y - alt);
		points[4] = new Point(x + length, y);
		points[5] = new Point(x + length/2, y + alt);
		points[6] = new Point(x - length/2, y + alt);
		points[7] = points[1];
	}

	void initStyles() {
		styles = new Style[count];
		Scanner sc = new Scanner(System.in);
		Style style = new RandomStyle("depth none yellow");
		int i = 0;
		// load styles from stdin
		while (sc.hasNextLine() && i < count) {
			String line = sc.nextLine();
			if (line.startsWith("random")) style = new RandomStyle(line);
			else if (line.startsWith("depth")) style = new DepthStyle(maxDepth, line);
			else if (line.startsWith("evenodd")) style = new EvenOddStyle(maxDepth, line);
			styles[i++] = style;
		}
		// copy last style till the end
		while (i < count) styles[i++] = style;
	}

	void initGlue(boolean glue) {
		this.glue = glue && count > 2 && count != 6;
		int g = GLUE;
		gluePoints = new Point[4];
		gluePoints[0] = new Point(points[0]);
		gluePoints[0].translate(-5, 0);
		gluePoints[1] = new Point(points[0]);
		gluePoints[1].translate(-g, g);
		gluePoints[2] = new Point(points[1]);
		gluePoints[2].translate(g, g);
		gluePoints[3] = new Point(points[1]);
		gluePoints[3].translate(5, 0);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height + (glue ? GLUE : 0);
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 1; i <= count; i++)
			Draw.draw(g, styles[i-1], maxDepth, points[0], points[i], points[i+1]);
		if (glue) {
			g.setColor(Color.GRAY);
			g.drawLine(gluePoints[0].x, gluePoints[0].y, gluePoints[1].x, gluePoints[1].y);
			g.drawLine(gluePoints[1].x, gluePoints[1].y, gluePoints[2].x, gluePoints[2].y);
			g.drawLine(gluePoints[2].x, gluePoints[2].y, gluePoints[3].x, gluePoints[3].y);
		}
	}

}
