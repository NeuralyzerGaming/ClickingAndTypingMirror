/**
 * 
 */
package usefulTools;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	 * @throws FileNotFoundExceptionatat
	 */
	public static void main(String[] args) throws AWTException, FileNotFoundException, IOException {
		// MouseClick.click(2851, 610, true, false, 100);
		Reader.main(FilesAndClipboard.CreateFile("ListenerSequence"));
		//Listener.ListenerInit();
		//System.exit(0);
		// Reader.type("");
		// Reader.keyPressed("A", 2500);
	}
}
