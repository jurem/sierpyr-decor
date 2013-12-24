package sierpyr;

import java.awt.Graphics;


/**
 * Drawing style of a Sierpinski triangle.
 *
 * @author jure
**/
public interface Style {
	boolean inside(Graphics g, int depth);
	boolean last(Graphics g);
}