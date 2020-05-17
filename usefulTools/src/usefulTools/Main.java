/**
 * 
 */
package usefulTools;

import java.awt.AWTException;
import java.awt.Robot;
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
	public static int numbOfRepetitions = 1;
	public static Scanner sc = new Scanner(System.in);
	public static boolean exit = false;
	public static Robot r;

	public static synchronized void main(String[] args) throws AWTException, FileNotFoundException, IOException {
		r = new Robot();
		//while (Objects.equals(exit, false)) {
			if (Objects.equals(continueYesOrNo("The Reader Method").toLowerCase(), "y")) {
				r.delay(100);
				numbOfRepetitions("The Reader Method");
				r.delay(100);
				int delay = numbOfRepetitionsReturn("the delay between repeates");
				r.delay(1000);
				while (numbOfRepetitions > 0) {
					r.delay(30);
					System.out.print("\nrepeating in 3...");
					r.delay(30);
					System.out.print("2...");
					r.delay(30);
					System.out.print("1...");
					Reader.main(FilesAndClipboard.CreateFile("ListenerSequence"), 400, delay);
					numbOfRepetitions--;
				}
				System.out.println("Reading is finished.");
			} else if (Objects.equals(continueYesOrNo("The Listener Method").toLowerCase(), "y")) {
				r.delay(400);
				Listener.ListenerInit();
			} else if (Objects.equals(continueYesOrNo("exit").toLowerCase(), "y")) {
				r.delay(100);
				System.out.println("code complete, exiting...");
				exit = true;
				sc.close();
				System.exit(0);
			} else {
				r.delay(100);
				System.out.println("Program looping...");

				// System.out.println("error, \"y\" or \"n\" not pressed, system exiting...");
				// System.exit(0);
			}
		//}
	}

	public static String continueYesOrNo(String thingToAsk) {
		String yesOrNo = "";
		System.out.println("Continue with the " + thingToAsk + " y/n?");
		yesOrNo = sc.next();
		return yesOrNo;
	}

	public static void numbOfRepetitions(String thingToRepeat) {
		System.out.println("How many times do you want to repeat " + thingToRepeat + " code?");
		Main.numbOfRepetitions = sc.nextInt();
	}
	public static int numbOfRepetitionsReturn(String thingToRepeat) {
		int numb;
		System.out.println("How many times do you want to repeat " + thingToRepeat + " code?");
		numb = sc.nextInt();
		return numb;
	}

}
