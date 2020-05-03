/**
 * 
 */
package usefulTools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

/**
 * @author warre
 *
 */

public class GettingMouseLocationFile implements KeyListener {
	public static int xPos = 0;
	public static int yPos = 0;
	public static int NumbOfClicks = 0;
	public static ArrayList<Integer> MouseSequenceArray = new ArrayList<Integer>();
	public static File MouseSequenceFile = FilesAndClipboard.CreateFile("MouseSequence");

	public static void main(String args[]) throws AWTException {
		Frame f = new Frame();
		f.setSize(500, 500);
		f.setVisible(true);
		f.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			public void keyPressed(KeyEvent e) {
				AWTKeyStroke ak = AWTKeyStroke.getAWTKeyStrokeForEvent(e);
				if (ak.equals(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK))) {
					System.out.println(GettingMouseLocationFile.MouseSequenceArray);
					// to see what order of clicks was pressed and what was written to the fill
					System.out.println("the array is this long: "+ GettingMouseLocationFile.MouseSequenceArray.size());
					GettingMouseLocationFile.MouseSequenceArray.add(0,GettingMouseLocationFile.MouseSequenceArray.size());
					FilesAndClipboard.WriteToFile(GettingMouseLocationFile.MouseSequenceArray,
							GettingMouseLocationFile.MouseSequenceFile,
							GettingMouseLocationFile.MouseSequenceArray.size());
					try {
						FilesAndClipboard.OpenFile(GettingMouseLocationFile.MouseSequenceFile);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
					FilesAndClipboard.GetFilePath(GettingMouseLocationFile.MouseSequenceFile);
					FilesAndClipboard.DeleteFile(GettingMouseLocationFile.MouseSequenceFile);
					System.out.println("MouseSequence Program has written resources to file and is now ended");
					System.exit(0);
				} else if (e.getKeyCode() == 16) {
					int[] point = MouseClick.getMouseLocation();
					int MouseX = point[0];
					int MouseY = point[1];
					GettingMouseLocationFile.MouseSequenceArray.add(MouseX);
					GettingMouseLocationFile.MouseSequenceArray.add(MouseY);
					GettingMouseLocationFile.NumbOfClicks++;
					System.out.println("The " + GettingMouseLocationFile.NumbOfClicks + "th possition is: " + MouseX + " is the x pos. and " + MouseY
							+ " is the y pos.");
				} else if (e.getKeyCode() == 27) {
					System.exit(0);
				}
				//System.out.println(e.getKeyCode());
				// to figure out what code I typed
				e.consume();
				// gets rid of weird shit i think - I don't know how important
			}
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		e.consume();
	}
}
