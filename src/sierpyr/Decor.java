package sierpyr;

import java.io.File;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.imageio.ImageIO;


/**
 * Main application for generating Sierpinski decorations.
 *
 * @author jure
**/
public class Decor {

	static void help() {
		System.out.println("Decor - Christmas tree decorations based on Sierpinski triangle");
		System.out.println("Usage: Decor dest length depth count");
		System.out.println("  dest:\tfilename or - for screen");
		System.out.println("  length:\tside length of a triangle");
		System.out.println("  depth:\trecursion depth of a triangle");
		System.out.println("  count:\tnumber of triangles (1--6)");
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 4) {
			help();
			return;
		}
		boolean screen = args[0].startsWith("-");
		int length = Integer.parseInt(args[1]);
		int maxDepth = Integer.parseInt(args[2]);
		int count = Integer.parseInt(args[3]);

		final Sierpinski s = new Sierpinski(length, maxDepth, count, !screen);

		if (screen) {
			JFrame f = new JFrame() {
				@Override
				public void paint(Graphics g) {
					super.paint(g);
					s.render(g);
				}
			};
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Insets in = f.getInsets();
			f.setSize(s.getWidth() + in.left + in.right, s.getHeight() + in.top + in.bottom);
			f.setVisible(true);
		} else {
			BufferedImage img = new BufferedImage(s.getWidth(), s.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = img.getGraphics();
			s.render(g);
		    ImageIO.write(img, "png", new File(args[0].substring(0)));
		}
	}

}