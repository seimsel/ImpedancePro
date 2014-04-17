package ch.fhnw.ht.eit.p2.impedancepro.util;
/**
 * 
 */


import java.awt.Container;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

/**
 * @author Richard Gut
 *
 */
public class ImageUtil {
	private static Container p = new Container();
	
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

	public static Image loadResourceImage(String imageName) {
		MediaTracker tracker = new MediaTracker(p);
		Image img = (new ImageIcon(ImageUtil.class.getResource("..//images//" + imageName))).getImage();
		tracker.addImage(img, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException ex) {
			System.out.println("Could not load image: " + imageName);
		}
		return img;
	}
}