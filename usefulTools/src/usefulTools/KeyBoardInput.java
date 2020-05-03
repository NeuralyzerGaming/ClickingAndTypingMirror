/**
 * 
 */
package usefulTools;

import java.awt.AWTException;
//import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * @author warre
 *
 */

public class KeyBoardInput implements NativeKeyListener {

	public static File KeyboardSequenceFile = FilesAndClipboard.CreateFile("KeyboardSequence");
	public static ArrayList<String> KeyBoardArray = new ArrayList<String>();
	public static String FinalString = "";
	public static String FinalStringChar = "";
	public static Boolean KeyListenerOnBoolean = false;

	public static synchronized void main(String[] args) throws IOException {
		KeyListenerInit();
	}

	public static synchronized void KeyListenerInit() {
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

		try {
			GlobalScreen.registerNativeHook();
			KeyBoardInput.KeyListenerOnBoolean = true;
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			System.exit(1);
		}
		GlobalScreen.addNativeKeyListener(new KeyBoardInput());

	}

	public static synchronized void UpdateFile() {
		System.out.println("The array is this long: " + KeyBoardInput.KeyBoardArray.size());
		KeyBoardInput.KeyBoardArray.add(FinalString);
		FilesAndClipboard.WriteToFile(KeyBoardInput.KeyBoardArray, KeyBoardInput.KeyboardSequenceFile,
				KeyBoardInput.KeyBoardArray.size(), true);
		FilesAndClipboard.GetFilePath(KeyBoardInput.KeyboardSequenceFile, true);
		try {
			FilesAndClipboard.OpenFile(KeyBoardInput.KeyboardSequenceFile, true);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		System.out.println("NativeKeyListener class is ending");
		System.out.println("7hello");
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
		// return keyCodeChar;
		return keyCodeChar;
	}

	public synchronized void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				KeyBoardInput.KeyListenerOnBoolean = false;
				UpdateFile();
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e1) {
				e1.printStackTrace();
			}
		} else {
			KeyBoardInput.KeyBoardArray.add("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
			KeyBoardInput.FinalString = KeyBoardInput.FinalString
					+ String.valueOf(keyCodeToChar(NativeKeyEvent.getKeyText(e.getKeyCode())));
			KeyBoardInput.FinalStringChar = KeyBoardInput.FinalString + NativeKeyEvent.getKeyText(e.getKeyCode());

		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		KeyBoardInput.KeyBoardArray.add("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		// System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
	}

	/*
	 * public static void unregisterNativeHook() throws NativeHookException { if
	 * (org.jnativehook.GlobalScreen.isNativeHookRegistered()) {
	 * 
	 * synchronized (GlobalScreen.hookThread) { try {
	 * org.jnativehook.GlobalScreen.hookThread.disable(); hookThread.join(); } catch
	 * (Exception e) { throw new NativeHookException(e.getCause()); } }
	 * 
	 * // GlobalScreen.eventExecutor.shutdown(); } }
	 */

}
