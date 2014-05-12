package ch.fhnw.ht.eit.p2.impedancepro.util;

import java.io.File;

/**
 * The <code>DocumentUtil</code> class enables a simple way of loading files.
 * 
 * @author Simon Zumbrunnen
 */
public class DocumentUtil {
	/**
	 * Loads a PDF from an absolute path.
	 * 
	 * @param pdfPath
	 *            Absolute path to the image
	 * @return The <code>Image</code> object
	 */
	public static File loadPDF(String pdfPath) {
		File pdf = (new File(pdfPath));
		return pdf;
	}

	/**
	 * Loads a PDF which is inside the "documents" resource folder.
	 * 
	 * @param pdfName
	 *            Relative path to the pdf (normally image name e.g.
	 *            "file.pdf")
	 * @return The <code>Image</code> object
	 */
	public static File loadResourcePDF(String pdfName) {
		File pdf = new File(DocumentUtil.class.getResource(".." + File.separator + "documents/"
				+ pdfName).getPath());
		return pdf;
	}
}