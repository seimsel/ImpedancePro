package ch.fhnw.ht.eit.p2.impedancepro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jfree.io.IOUtils;

/**
 * <pre>
 * The <code>DocumentUtil</code> class enables a simple way of loading files
 * like PDF or similar.
 * </pre>
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
	 * <pre>
	 * Loads a PDF which is inside the "documents" resource folder.
	 * </pre>
	 * 
	 * @param pdfName
	 *            Relative path to the pdf (normally image name e.g. "file.pdf")
	 * @return The <code>Image</code> object
	 * @throws IOException 
	 */
	public static File loadResourcePDF(String pdfName) throws IOException {
		InputStream in = DocumentUtil.class.getResourceAsStream("documents/" + pdfName);
		File pdf = File.createTempFile("temp", ".pdf");
        pdf.deleteOnExit();
        FileOutputStream out = new FileOutputStream(pdf);
        IOUtils.getInstance().copyStreams(in, out);
        in.close();
        out.close();
        return pdf;
	}
}