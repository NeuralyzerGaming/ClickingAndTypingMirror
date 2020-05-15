/**
 * 
 */
package usefulTools;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.KeyStroke;

/**
 * @author warre
 *
 */
public class Reader {
	// public static File ClassFile = FilesAndClipboard.CreateFile("MouseSequence");
	/**
	 * @param args
	 */
	public static void main(File file) throws FileNotFoundException, AWTException {
		init(file);
		ArrayList<String> ListenerArray = FilesAndClipboard.ReadFileToArray(file, true);
		System.out.println(ListenerArray);
		parseArrayList(ListenerArray);
		// MouseClick.clickFromArray(MouseLocationsArray);
		// cleanup(file);
	}

	public static void parseArrayList(ArrayList<String> StringArrayList) throws AWTException {
		for (int i = 0; i < StringArrayList.size(); i++) {
			String object = StringArrayList.get(i);
			if (object.indexOf("Mouse Clicked:") != -1) {
				System.out.println("Mouse Click on line " + i + "the line reads: " + object);
				int[] MouseCord = ClickCord(object);
				// MouseClick.click(MouseCord[0], MouseCord[1], true, false, 100);
			} else if (object.indexOf("Key Pressed:") != -1) {
				System.out.println("Key Pressed on line " + i);
				System.out.println("Specific key pressed is " + Typekey(object));
				keyPressed(Listener.keyCodeToChar(Typekey(object)), 100);
			} else if (object.indexOf("Key Released:") != -1) {
				System.out.println("Key Released on line " + i);
				System.out.println("Specific key released is " + Typekey(object));
				keyReleased(Listener.keyCodeToChar(Typekey(object)), 100);
			} else {
				System.out.println("unidentified line: " + i);
			}
		}
	}

	public static int[] ClickCord(String cord) {
		int XCordPosStart = cord.indexOf("(");
		int comma = cord.indexOf(",");
		int YCordPosEnd = cord.indexOf(")");
		System.out.println(
				"XCordPosStart is " + XCordPosStart + ", comma is " + comma + ", YCordPosEnd is " + YCordPosEnd);
		String xCord = cord.substring(XCordPosStart + 1, comma);
		String yCord = cord.substring(comma + 1, YCordPosEnd);
		System.out.println("x cord is " + xCord);
		System.out.println("y cord is " + yCord);
		int ClickArray[] = { Integer.parseInt(xCord), Integer.parseInt(yCord) };
		System.out.println("This is the array of the cordinate clicked: " + Arrays.toString(ClickArray));
		return ClickArray;
	}

	public static String Typekey(String FullLine) {
		int leftSide = FullLine.indexOf("/") + 1;
		System.out.println("leftSide is " + leftSide);
		int rightSide = FullLine.indexOf("/", leftSide);
		System.out.println("rightSide is " + rightSide);
		String Key = FullLine.substring(leftSide, rightSide);
		System.out.println("key is " + Key);
		return Key;
	}

	public static void typeChar(String Key, int delay) {
		try {
			Robot r = new Robot();
			KeyStroke KeyCode = KeyStroke.getKeyStroke(Key.charAt(0), 0);
			System.out.println("the keyCode is " + KeyCode);
			// r.keyPress(KeyCode);
			r.delay(delay);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	static void keyPressed(char c, int delay) {
		try {
			Robot r = new Robot();
			if (Character.isUpperCase(c)) {
				r.keyPress(KeyEvent.VK_SHIFT);
			}
			r.delay(delay);
			r.keyPress(Character.toUpperCase(c));
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	static void keyReleased(char c, int delay) {
		try {
			Robot r = new Robot();
			r.delay(delay);
			r.keyRelease(Character.toUpperCase(c));
			if (Character.isUpperCase(c)) {
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void init(File f) {
		FilesAndClipboard.GetFilePath(f, true);
		// try {
		// FilesAndClipboard.OpenFile(f, true);
		// } //catch (AWTException e) {
		// e.printStackTrace();
		// }
	}

	public static void cleanup(File f) {
		FilesAndClipboard.DeleteFile(f, true);
	}

}
