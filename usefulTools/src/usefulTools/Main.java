/**
 * 
 */
package usefulTools;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.KeyStroke;


/**
 * @author warre
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws AWTException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws AWTException, FileNotFoundException {
		Reader.main(FilesAndClipboard.CreateFile("ListenerSequence"));
		// Reader.type("");
		//Reader.keyPressed("A", 2500);
	}
}
