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
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

/**
 * @author warre
 *
 */

public class MouseInput implements NativeMouseInputListener {

	public static File MouseSequenceFile = FilesAndClipboard.CreateFile("MouseSequence");
	public static ArrayList<String> MouseArray = new ArrayList<String>();
	public static String FinalString = "";
	public static String FinalStringChar = "";
	public static Boolean MouseListenerOnBoolean = false;

	//public static synchronized void main(String[] args) throws IOException {
	//	MouseListenerInit();
	//}

	public static synchronized void MouseListenerInit() {
		setLoggerLevel();

		try {
			GlobalScreen.registerNativeHook();
			KeyBoardInput.KeyListenerOnBoolean = true;
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			System.exit(1);
		}
		// Construct the example object.
		MouseInput MouseListener = new MouseInput();

		// Add the appropriate listeners.
		GlobalScreen.addNativeMouseListener(MouseListener);
		GlobalScreen.addNativeMouseMotionListener(MouseListener);

	}

	public static void setLoggerLevel() {
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

	@Override
	public void nativeMouseClicked(NativeMouseEvent nativeEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeMousePressed(NativeMouseEvent nativeEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent nativeEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent nativeEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent nativeEvent) {
		// TODO Auto-generated method stub

	}

}
