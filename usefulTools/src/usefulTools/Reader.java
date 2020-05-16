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
import java.util.Objects;

import javax.swing.KeyStroke;

import org.jnativehook.keyboard.NativeKeyEvent;

/**
 * @author warre
 *
 */
public class Reader {
	/**
	 * @param args
	 */
	public static void main(File file) throws FileNotFoundException, AWTException {
		init(file);
		ArrayList<String> ListenerArray = FilesAndClipboard.ReadFileToArray(file, true);
		System.out.println(ListenerArray);
		parseArrayList(ListenerArray);
		// cleanup(file);
		// ^^ deletes file at the end
	}

	public static void parseArrayList(ArrayList<String> StringArrayList) throws AWTException {
		Robot r = new Robot();
		r.delay(1500);
		for (int i = 0; i < StringArrayList.size(); i++) {
			String line = StringArrayList.get(i);
			System.out.println("\n");
			if (line.indexOf("Mouse Clicked:") != -1) {
				System.out.println("Mouse Click on line " + i + ". The line reads: \"" + line + "\"");
				int[] MouseCord = clickCord(line);
				System.out.println("The code is going to click this x cord " + MouseCord[0]);
				System.out.println("The code is going to click this x cord " + MouseCord[1]);
				MouseClick.click(MouseCord[0], MouseCord[1], true, true, 100);
			} else if (line.indexOf("Key Pressed:") != -1) {
				System.out.println("Key Pressed on line " + i);
				if (StringToButtonPress(parseLineForKey(line), 10) == false) {
					line = parseLineForKey(line);
					System.out.println("Specific key pressed is " + line);
					keyPress(Listener.keyCodeToChar(line), 10);
				}
			} else if (line.indexOf("Key Released:") != -1) {
				System.out.println("Key Released on line " + i);
				if (StringToButtonRelease(parseLineForKey(line), 10) == false) {
					line = parseLineForKey(line);
					System.out.println("Specific key released is " + line);
					keyRelease(Listener.keyCodeToChar(line), 10);
				}
			} else {
				System.out.println("unidentified line: " + i);
			}

		}
	}

	public static String parseKeyCodeStringRemoveSpaces(String line) {
		String KeyCodeString = "";
		// System.out.println("KeyCode is " + line);
		for (int a = 0; a < line.length(); a++) {
			char charAtIndex = line.charAt(a);
			if (Character.isDigit(charAtIndex) == true) {
				KeyCodeString = KeyCodeString + charAtIndex;
				// System.out.println(KeyCodeString);
			}
		}
		return KeyCodeString;
	}

	public static int[] clickCord(String cord) {
		int XCordPosStart = cord.indexOf("(");
		int comma = cord.indexOf(",");
		int YCordPosEnd = cord.indexOf(")");
		// System.out.println(
		// AT "XCordPosStart is " + XCordPosStart + ", comma is " + comma + ",
		// YCordPosEnd is " + YCordPosEnd);
		String xCord = cord.substring(XCordPosStart + 1, comma);
		String yCord = cord.substring(comma + 1, YCordPosEnd);
		// System.out.println("x cord is " + xCord);
		// System.out.println("y cord is " + yCord);
		int ClickArray[] = { Integer.parseInt(xCord), Integer.parseInt(yCord) };
		// System.out.println("This is the array of the coordinate clicked: " +
		// Arrays.toString(ClickArray));
		return ClickArray;
	}

	public static String parseLineForKey(String FullLine) {
		int leftSide = FullLine.indexOf("/") + 1;
		// System.out.println("leftSide is " + leftSide);
		int rightSide = FullLine.indexOf("/", leftSide);
		// System.out.println("rightSide is " + rightSide);
		String Key = FullLine.substring(leftSide, rightSide);
		System.out.println("Key is " + "\"" + Key + "\"");
		return Key;
	}

	public static String parseData(String NameOfData, String FullLine, String LeftArticle, String RightArticle) {
		int leftSide = FullLine.indexOf(LeftArticle) + 1;
		// System.out.println("leftSide is " + leftSide);
		int rightSide = FullLine.indexOf(LeftArticle, leftSide);
		// System.out.println("rightSide is " + rightSide);
		if (rightSide == -1 || leftSide == -1) {
			System.out.println("parseData Method was unable to find the text and has made an error");
			return null;
		} else {
			String data = FullLine.substring(leftSide, rightSide);
			System.out.println(NameOfData + " is " + "\"" + data + "\"");
			return data;
		}
	}

	public static void typeChar(String Key, int delay) {
		// not called right now
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

	static void keyPress(char KeyChar, int delay) {
		try {
			Robot r = new Robot();
			// if (Character.isUpperCase(c)) {
			// r.keyPress(KeyEvent.VK_SHIFT);
			// }
			r.delay(delay);
			r.keyPress(KeyChar);
			System.out.println("Key " + KeyChar + " was pressed.");
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	static void keyPress(int KeyCharCode, int delay) {
		try {
			Robot r = new Robot();
			r.delay(delay);
			r.keyPress(KeyCharCode);
			System.out.println("Key " + NativeKeyEvent.getKeyText(KeyCharCode) + " was pressed.");
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	static void keyRelease(char c, int delay) {
		try {
			Robot r = new Robot();
			r.delay(delay);
			r.keyRelease(c);
			// if (Character.isUpperCase(c)) {
			// r.keyRelease(KeyEvent.VK_SHIFT);
			// }
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	static void keyRelease(int KeyCharCode, int delay) {
		try {
			Robot r = new Robot();
			r.delay(delay);
			r.keyRelease(KeyCharCode);
			System.out.println("Key " + NativeKeyEvent.getKeyText(KeyCharCode) + " was pressed.");
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static boolean stringTypeIsText(String text) {
		if (Objects.equals(text, "Tab") || Objects.equals(text, "Alt")) {
			return false;
		} else {
			return true;
		}
	}

	public static synchronized Boolean StringToButtonPress(String KeyCodeString, int delay) throws AWTException {
		// char r.keyPress(KeyEvent); = 0;
		Robot r = new Robot();
		if (Objects.equals(KeyCodeString, "Space")) {
			r.keyPress(KeyEvent.VK_SPACE);
		} else if (Objects.equals(KeyCodeString, "Back Quote")) {
			r.keyPress(KeyEvent.VK_BACK_QUOTE);
		} else if (Objects.equals(KeyCodeString, "Minus")) {
			r.keyPress(KeyEvent.VK_MINUS);
		} else if (Objects.equals(KeyCodeString, "Equals")) {
			r.keyPress(KeyEvent.VK_EQUALS);
		} else if (Objects.equals(KeyCodeString, "Backspace")) {
			r.keyPress(KeyEvent.VK_BACK_SPACE);
		} else if (Objects.equals(KeyCodeString, "Tab")) {
			r.keyPress(KeyEvent.VK_TAB);
		} else if (Objects.equals(KeyCodeString, "Open Bracket")) {
			r.keyPress(KeyEvent.VK_OPEN_BRACKET);
		} else if (Objects.equals(KeyCodeString, "Close Bracket")) {
			r.keyPress(KeyEvent.VK_CLOSE_BRACKET);
		} else if (Objects.equals(KeyCodeString, "Back Slash")) {
			r.keyPress(KeyEvent.VK_BACK_SLASH);
		} else if (Objects.equals(KeyCodeString, "Caps Lock")) {
			r.keyPress(KeyEvent.VK_CAPS_LOCK);
		} else if (Objects.equals(KeyCodeString, "Shift")) {
			r.keyPress(KeyEvent.VK_SHIFT);
		} else if (Objects.equals(KeyCodeString, "Unknown keyCode: 0xe36")) {
			System.out.println("the program cannot identify and press" + "\"" + KeyCodeString + "\"");
		} else if (Objects.equals(KeyCodeString, "Ctrl")) {
			r.keyPress(KeyEvent.VK_CONTROL);
		} else if (Objects.equals(KeyCodeString, "Undefined")) {
			System.out.println("the program cannot identify and press" + "\"" + KeyCodeString + "\"");
		} else if (Objects.equals(KeyCodeString, "Meta")) {
			r.keyPress(KeyEvent.VK_WINDOWS);
		} else if (Objects.equals(KeyCodeString, "Alt")) {
			r.keyPress(KeyEvent.VK_ALT);
			System.out.println("alt pressed");
		} else if (Objects.equals(KeyCodeString, "Up")) {
			r.keyPress(KeyEvent.VK_KP_UP);
		} else if (Objects.equals(KeyCodeString, "Left")) {
			r.keyPress(KeyEvent.VK_LEFT);
		} else if (Objects.equals(KeyCodeString, "Down")) {
			r.keyPress(KeyEvent.VK_DOWN);
		} else if (Objects.equals(KeyCodeString, "Right")) {
			r.keyPress(KeyEvent.VK_RIGHT);
			// '\u0000' is the null code for chars
		} else if (Objects.equals(KeyCodeString, "Semicolon")) {
			r.keyPress(KeyEvent.VK_SEMICOLON);
		} else if (Objects.equals(KeyCodeString, "Quote")) {
			r.keyPress(KeyEvent.VK_QUOTE);
		} else if (Objects.equals(KeyCodeString, "Enter")) {
			r.keyPress(KeyEvent.VK_ENTER);
		} else if (Objects.equals(KeyCodeString, "Comma")) {
			r.keyPress(KeyEvent.VK_COMMA);
		} else if (Objects.equals(KeyCodeString, "Period")) {
			r.keyPress(KeyEvent.VK_PERIOD);
		} else if (Objects.equals(KeyCodeString, "Slash")) {
			r.keyPress(KeyEvent.VK_SLASH);
		} else {
			System.out.println("StringToButtonPress method cannot identify and press " + "\"" + KeyCodeString + ",\" checking char...");
			r.delay(delay);
			return false;
		}
		System.out.println("\"" + KeyCodeString + "\"" + " was pressed");
		r.delay(delay);
		return true;
	}

	public static synchronized Boolean StringToButtonRelease(String KeyCodeString, int delay) throws AWTException {
		// char r.keyRelease(KeyEvent); = 0;
		Robot r = new Robot();
		if (Objects.equals(KeyCodeString, "Space")) {
			r.keyRelease(KeyEvent.VK_SPACE);
		} else if (Objects.equals(KeyCodeString, "Back Quote")) {
			r.keyRelease(KeyEvent.VK_BACK_QUOTE);
		} else if (Objects.equals(KeyCodeString, "Minus")) {
			r.keyRelease(KeyEvent.VK_MINUS);
		} else if (Objects.equals(KeyCodeString, "Equals")) {
			r.keyRelease(KeyEvent.VK_EQUALS);
		} else if (Objects.equals(KeyCodeString, "Backspace")) {
			r.keyRelease(KeyEvent.VK_BACK_SPACE);
		} else if (Objects.equals(KeyCodeString, "Tab")) {
			r.keyRelease(KeyEvent.VK_TAB);
		} else if (Objects.equals(KeyCodeString, "Open Bracket")) {
			r.keyRelease(KeyEvent.VK_OPEN_BRACKET);
		} else if (Objects.equals(KeyCodeString, "Close Bracket")) {
			r.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
		} else if (Objects.equals(KeyCodeString, "Back Slash")) {
			r.keyRelease(KeyEvent.VK_BACK_SLASH);
		} else if (Objects.equals(KeyCodeString, "Caps Lock")) {
			r.keyRelease(KeyEvent.VK_CAPS_LOCK);
		} else if (Objects.equals(KeyCodeString, "Shift")) {
			r.keyRelease(KeyEvent.VK_SHIFT);
		} else if (Objects.equals(KeyCodeString, "Unknown keyCode: 0xe36")) {
			System.out.println("the program cannot identify and press " + "\"" + KeyCodeString + "\"");
		} else if (Objects.equals(KeyCodeString, "Ctrl")) {
			r.keyRelease(KeyEvent.VK_CONTROL);
		} else if (Objects.equals(KeyCodeString, "Undefined")) {
			System.out.println("the program cannot identify and press " + "\"" + KeyCodeString + "\"");
		} else if (Objects.equals(KeyCodeString, "Meta")) {
			r.keyRelease(KeyEvent.VK_WINDOWS);
		} else if (Objects.equals(KeyCodeString, "Alt")) {
			r.keyRelease(KeyEvent.VK_ALT);
		} else if (Objects.equals(KeyCodeString, "Up")) {
			r.keyRelease(KeyEvent.VK_KP_UP);
		} else if (Objects.equals(KeyCodeString, "Left")) {
			r.keyRelease(KeyEvent.VK_LEFT);
		} else if (Objects.equals(KeyCodeString, "Down")) {
			r.keyRelease(KeyEvent.VK_DOWN);
		} else if (Objects.equals(KeyCodeString, "Right")) {
			r.keyRelease(KeyEvent.VK_RIGHT);
			// '\u0000' is the null code for chars
		} else if (Objects.equals(KeyCodeString, "Semicolon")) {
			r.keyRelease(KeyEvent.VK_SEMICOLON);
		} else if (Objects.equals(KeyCodeString, "Quote")) {
			r.keyRelease(KeyEvent.VK_QUOTE);
		} else if (Objects.equals(KeyCodeString, "Enter")) {
			r.keyRelease(KeyEvent.VK_ENTER);
		} else if (Objects.equals(KeyCodeString, "Comma")) {
			r.keyRelease(KeyEvent.VK_COMMA);
		} else if (Objects.equals(KeyCodeString, "Period")) {
			r.keyRelease(KeyEvent.VK_PERIOD);
		} else if (Objects.equals(KeyCodeString, "Slash")) {
			r.keyRelease(KeyEvent.VK_SLASH);
		} else {
			System.out.println("StringToButtonRelease method cannot identify and release " + "\"" + KeyCodeString + ",\" checking Char...");
			r.delay(delay);
			return false;
		}
		System.out.println("\"" + KeyCodeString + "\"" + " was released");
		r.delay(delay);
		return true;
	}

	public static void init(File f) {
		FilesAndClipboard.GetFilePath(f, true);
		/*
		 * try { FilesAndClipboard.OpenFile(f, true); } catch (AWTException e) {
		 * e.printStackTrace(); }
		 */
	}

	public static void cleanup(File f) {
		FilesAndClipboard.DeleteFile(f, true);
	}

}
