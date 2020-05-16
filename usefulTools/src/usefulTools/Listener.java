/**
 * 
 */
package usefulTools;

import java.awt.AWTException;
//import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.mouse.NativeMouseMotionListener;

/**
 * @author warre
 *
 */

public class Listener implements NativeKeyListener, NativeMouseListener, NativeMouseMotionListener {

	public static File ListenerSequenceFile = FilesAndClipboard.CreateFile("ListenerSequence");
	public static ArrayList<String> ListenerArray = new ArrayList<String>();
	public static ArrayList<String> testArray = new ArrayList<String>();
	public static String FinalString = "";
	public static String FinalStringChar = "";

	public static synchronized void main(String[] args) throws IOException {
		ListenerInit();
	}

	public static synchronized void ListenerInit() {
		logerinit();
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			System.exit(1);
		}
		Listener UserInputListener = new Listener();

		// Add the appropriate listeners.
		GlobalScreen.addNativeMouseListener(UserInputListener);
		GlobalScreen.addNativeMouseMotionListener(UserInputListener);

		GlobalScreen.addNativeKeyListener(new Listener());
	}

	public static void logerinit() {
		// Get the logger for "org.jnativehook" and set the level to warning.
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.WARNING);

		// Don't forget to disable the parent handlers.
		logger.setUseParentHandlers(false);

		// Change the level for all handlers attached to the default logger.
		Handler[] handlers = Logger.getLogger("").getHandlers();
		for (int i = 0; i < handlers.length; i++) {
			handlers[i].setLevel(Level.OFF);
		}
	}

	public static synchronized void UpdateFile() {
		System.out.println("The array of events is this long: " + Listener.ListenerArray.size());
		Listener.ListenerArray.add(FinalString);
		System.out.println("final string is " + FinalString);
		FilesAndClipboard.WriteToFile(Listener.ListenerArray, Listener.ListenerSequenceFile,
				Listener.ListenerArray.size(), true);
		FilesAndClipboard.GetFilePath(Listener.ListenerSequenceFile, true);
		try {
			FilesAndClipboard.OpenFile(Listener.ListenerSequenceFile, true);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		System.out.println("NativeKeyListener class is ending");
	}

	public static synchronized char keyCodeToChar(String KeyCodeString) {
		char keyCodeChar = 0;
		if (KeyCodeString == "Space") {
			keyCodeChar = ' ';
		} else if (KeyCodeString == "Back Quote") {
			keyCodeChar = '\'';
		} else if (KeyCodeString == "Minus") {
			keyCodeChar = '-';
		} else if (KeyCodeString == "Equals") {
			keyCodeChar = '=';
		} else if (KeyCodeString == "Backspace") {
			keyCodeChar = '\b';
		} else if (KeyCodeString == "Tab") {
			keyCodeChar = '\t';
			System.out.println("TAB HAS BEEN PRESSED!!!!");
		} else if (KeyCodeString == "Open Bracket") {
			keyCodeChar = '[';
		} else if (KeyCodeString == "Close Bracket") {
			keyCodeChar = ']';
		} else if (KeyCodeString == "Back Slash") {
			keyCodeChar = '\\';
		} else if (KeyCodeString == "Caps Lock" || KeyCodeString == "Shift" || KeyCodeString == "Unknown keyCode: 0xe36"
				|| KeyCodeString == "Ctrl" || KeyCodeString == "Undefined" || KeyCodeString == "Meta"
				|| KeyCodeString == "Alt" || KeyCodeString == "Up" || KeyCodeString == "Left" || KeyCodeString == "Down"
				|| KeyCodeString == "Right") {
			keyCodeChar = '\u0000';
		} else if (KeyCodeString == "Semicolon") {
			keyCodeChar = ';';
		} else if (KeyCodeString == "Quote") {
			keyCodeChar = '"';
		} else if (KeyCodeString == "Enter") {
			keyCodeChar = '\n';
		} else if (KeyCodeString == "Comma") {
			keyCodeChar = ',';
		} else if (KeyCodeString == "Period") {
			keyCodeChar = '.';
		} else if (KeyCodeString == "Slash") {
			keyCodeChar = '/';
		} else {
			keyCodeChar = KeyCodeString.charAt(0);
		}
		return keyCodeChar;
	}

	public synchronized void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				UpdateFile();
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e1) {
				e1.printStackTrace();
			}
		} else {
			Listener.ListenerArray.add("Key Pressed: " + "/" + NativeKeyEvent.getKeyText(e.getKeyCode()) + "/"
					+ " KeyCode is - " + e.getKeyCode() + " -");
			Listener.FinalString = Listener.FinalString
					+ String.valueOf(keyCodeToChar(NativeKeyEvent.getKeyText(e.getKeyCode())));

		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()) + " KeyCode is - "
				+ e.getKeyCode() + " -");
		Listener.ListenerArray.add("Key Released: " + "/" + NativeKeyEvent.getKeyText(e.getKeyCode()) + "/"
				+ " KeyCode is - " + e.getKeyCode() + " -");
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		// System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		// System.out.println("Mouse moves: " + e.getClickCount());

	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		// System.out.println("Mouse dragged with " + e.getButton() + " to "+ e.getX() +
		// ", " + e.getY());
	}

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		// System.out.println("Mouse Released: " + e.getButton());
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent e) {
		// System.out.println("Mouse Clicked: " + e.getX() + ", " + e.getY());
		// built into package but wrong... doubles the true value...
		int x = MouseClick.getMouseLocation()[0];
		int y = MouseClick.getMouseLocation()[1];
		System.out.println("Mouse Clicked at " + "(" + x + "," + y + ")");
		Listener.ListenerArray.add("Mouse Clicked: " + "(" + x + "," + y + ")");
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		// System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
	}
}
