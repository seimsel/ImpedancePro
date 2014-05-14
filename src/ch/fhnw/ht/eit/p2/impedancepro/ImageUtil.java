package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.Container;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

/**
 * The <code>ImageUtil</code> class enables a simple way of loading images.
 * 
 * @author Richard Gut
 */
public class ImageUtil {
	private static Container p = new Container();

	/**
	 * Loads an image from an absolute path.
	 * 
	 * @param imagePath
	 *            Absolute path to the image
	 * @return The <code>Image</code> object
	 */
	public static Image loadImage(String imagePath) {
		MediaTracker tracker = new MediaTracker(p);
		Image img = (new ImageIcon(imagePath)).getImage();
		tracker.addImage(img, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException ex) {
			System.out.println("Could not load image: " + imagePath);
		}
		return img;
	}

	/**
	 * Loads an image which is inside the "images" resource folder.
	 * 
	 * @param imageName
	 *            Relative path to the image (normally image name e.g.
	 *            "picture.png")
	 * @return The <code>Image</code> object
	 */
	public static Image loadResourceImage(String imageName) {
		MediaTracker tracker = new MediaTracker(p);
		Image img = (new ImageIcon(ImageUtil.class.getResource("images/"
				+ imageName))).getImage();
		tracker.addImage(img, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException ex) {
			System.out.println("Could not load image: " + imageName);
		}
		return img;
	}
}