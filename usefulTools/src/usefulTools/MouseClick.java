/**
 * 
 */
package usefulTools;

/**
 * @author warre
 *
 */
import java.awt.MouseInfo;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class MouseClick {
	public static Scanner sc = new Scanner(System.in);

	/**
	 * @param args
	 * @throws AWTException
	 */
	public static void InitialMouseCLickMain() throws AWTException {
		System.out.println("enter: xPos, enter, yPos, enter, click?");
		int x = sc.nextInt();
		int y = sc.nextInt();
		boolean clicktrueorfalse = sc.nextBoolean();
		click(x, y, clicktrueorfalse, true, 100);
		sc.close();
	}

	public static void click(int x, int y, Boolean clicktrueorfalse, Boolean returnToOriginalMouseLocation, int delay)
			throws AWTException {
		int[] point = getMouseLocation();
		int xKnot = point[0];
		int yKnot = point[1];
		System.out.println("x pos. is: " + xKnot);
		System.out.println("y pos. is: " + yKnot);
		try {
			Robot r = new Robot();
			r = new Robot();
			r.delay(delay);
			r.mouseMove(x, y);
			if (clicktrueorfalse == true) {
				System.out.println("Clicked!");
				r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			}
			r.delay(delay);
			if (returnToOriginalMouseLocation == true) {
				r.mouseMove(xKnot, yKnot);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("click method arrived at a AWTException");
		}
	}

	public static int[] getMouseLocation() {
		int[] point = { 0, 0 };
		double xDouble = MouseInfo.getPointerInfo().getLocation().getX();
		point[0] = (int) xDouble;
		double yDouble = MouseInfo.getPointerInfo().getLocation().getY();
		point[1] = (int) yDouble;
		return point;
	}

	public static void clickFromArray(ArrayList<Integer> array) throws AWTException {
		for (int i = 0; i < array.size(); i=i+2) {
			MouseClick.click(array.get(i),array.get(i+1),true,true, 200);
		}
	}
}
