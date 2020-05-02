/**
 * 
 */
package usefulTools;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.*;
import java.awt.List;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;

/**
 * @author warre
 *
 */

public class FilesAndClipboard {
	public static String FileName;
	public static File ClassFile;
	public static String KeysToPress;
	public static String FilePath;
	public static String OriginalClipboard;
	public static Scanner sc = new Scanner(System.in);
	public static int LinesOfFile = 0;

	/**
	 * @param args
	 */
	//test
	public static void InitialFilesAndClipboardMain() throws AWTException {
		System.out.println("enter: FileName, enter");
		FilesAndClipboard.FileName = sc.nextLine();
		FilesAndClipboard.ClassFile = CreateFile(FilesAndClipboard.FileName);

		WriteToFile(TestingWriteToFileMethod(), FilesAndClipboard.ClassFile, FilesAndClipboard.LinesOfFile, null);
		// testing ^^ using ArrayList to write to file
		GetFilePath(FilesAndClipboard.ClassFile);
		OpenFile(FilesAndClipboard.ClassFile);
		DeleteFile(FilesAndClipboard.ClassFile);
		FilesAndClipboard.sc.close();

	}

	public static File CreateFile(String FileName) {
		File file = null;
		try {
			File f = new File(FileName + ".txt");
			file = f;
			if (f.createNewFile()) {
				System.out.println("File created: " + f.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		System.out.println("CreateFile method ended");
		return file;
	}

	public static void WriteToFile(String Content, File File) {
		try {
			PrintStream fileStream = new PrintStream(File);
			fileStream.println(Content);
			System.out.println(
					"WriteToFile method wrote " + "\"" + Content + "\" to the file " + "\"" + File.getName() + "\"");
			fileStream.close();
		} catch (IOException e) {
			System.out.println("WriteToFile method arrived at an IOExecoption Error");
			e.printStackTrace();
		}
		System.out.println("WriteToFile method ended");
	}

	public static void WriteToFile(ArrayList<String> mouseSequenceArray, File File, int Limit, Boolean dumb) {
		try {
			PrintStream fileStream = new PrintStream(File);
			for (int i = 0; i < Limit; i++) {
				fileStream.println(mouseSequenceArray.get(i));
			}
			System.out.println("WriteToFile method wrote " + "\"" + mouseSequenceArray + "\" to the file " + "\""
					+ File.getName() + "\"");
			fileStream.close();
		} catch (IOException e) {
			System.out.println("WriteToFile method arrived at an IOExecoption Error");
			e.printStackTrace();
		}
		System.out.println("WriteToFile method ended");
	}

	public static void WriteToFile(ArrayList<Integer> Content, File File, int Limit) {
		try {
			PrintStream fileStream = new PrintStream(File);
			for (int i = 0; i < Limit; i++) {
				fileStream.println(Content.get(i));
			}
			System.out.println(
					"WriteToFile method wrote " + "\"" + Content + "\" to the file " + "\"" + File.getName() + "\"");
			fileStream.close();
		} catch (IOException e) {
			System.out.println("WriteToFile method arrived at an IOExecoption Error");
			e.printStackTrace();
		}
		System.out.println("WriteToFile method ended");
	}

	public static void WriteToFile(String[] Content, File File, int Limit) {
		try {
			PrintStream fileStream = new PrintStream(File);
			for (int i = 0; i < Limit; i++) {
				fileStream.println(Content[i]);
			}
			System.out.println(
					"WriteToFile method wrote " + "\"" + Content + "\" to the file " + "\"" + File.getName() + "\"");
			fileStream.close();
		} catch (IOException e) {
			System.out.println("WriteToFile method arrived at an IOExecoption Error");
			e.printStackTrace();
		}
		System.out.println("WriteToFile method ended");
	}

	public static void WriteToFile(int Content, File File) {
		try {
			PrintStream fileStream = new PrintStream(File);
			fileStream.println(Content);
			System.out.println(
					"WriteToFile method wrote " + "\"" + Content + "\" to the file " + "\"" + File.getName() + "\"");
			fileStream.close();
		} catch (IOException e) {
			System.out.println("WriteToFile method arrived at an IOExecoption Error");
			e.printStackTrace();
		}
		System.out.println("WriteToFile method ended");
	}

	public static String GetFilePath(String FileName) {
		String s = null;
		try {
			File f = new File(FileName);
			String FilePath = f.getAbsolutePath();
			FilePath = s;
			System.out.println("Original  path: " + f.getPath());
			System.out.println("Absolute  path: " + FilePath);
			CopyToClipBoard(FilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("GetFilePath method ended");
		return s;
	}

	public static String GetFilePath(File File) {
		String s = null;
		try {
			String FilePath = File.getAbsolutePath();
			s = FilePath;
			System.out.println("Original  path: " + File.getPath());
			System.out.println("Absolute  path: " + FilePath);
			CopyToClipBoard(FilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("GetFilePath method ended");
		return s;
	}

	public static void OpenFile(File File) throws AWTException {
		try {
			System.out.println("open file? Enter y/n");
			String yesorno = sc.next();
			if (Objects.equals(yesorno, "y")) {
				Desktop desktop = Desktop.getDesktop();
				desktop.open(File);
				System.out.println("OpenFile method ended");
				// return;
			} else if (Objects.equals(yesorno, "n")) {
				System.out.println("OpenFile method ended");
				// return;
			} else {
				System.out.println("Error, \"y\" or \"n\" not pressed");
				System.out.println("OpenFile method ended");
			}
		} catch (Exception e) {
			System.out.println("Error in OpenFile(); Method");
			e.printStackTrace();
		}
		System.out.println("OpenFile method ended");
	}

	public static void OpenFile(String FileName) throws AWTException {
		try {
			System.out.println("open file? Enter y/n");
			String yesorno = sc.next();
			if (Objects.equals(yesorno, "y")) {
				Desktop desktop = Desktop.getDesktop();
				File f = new File(FileName + ".txt");
				desktop.open(f);
				System.out.println("OpenFile method ended");
				// return;
			} else if (Objects.equals(yesorno, "n")) {
				System.out.println("OpenFile method ended");
				// return;
			} else {
				System.out.println("Error, \"y\" or\"n\" not pressed");
				System.out.println("OpenFile method ended");
			}
		} catch (Exception e) {
			System.out.println("Error in OpenFile(); Method");
			e.printStackTrace();
		}
		System.out.println("OpenFIle method ended");
	}

	public static void CopyToClipBoard(String ThingToCopy) {
		System.out.println("Copy " + "\"" + ThingToCopy + "\"" + "? Enter y/n");
		String yesorno = sc.next();
		if (Objects.equals(yesorno, "y")) {
			StringSelection s = new StringSelection(ThingToCopy);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(s, s);
		} else if (Objects.equals(yesorno, "n")) {
		} else {
			System.out.println("error");
		}
		System.out.println("CopyToClipBoard method ended");
	}

	public static void CopyAndPaste(String TextToCopyPaste) {
		System.out.println("Type the text you want to copy to clipboard, hit enter:");
		TextToCopyPaste = sc.nextLine();

		StringSelection stringSelection = new StringSelection(TextToCopyPaste);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
		System.out.println("\"" + TextToCopyPaste + "\" coppied to clipboard");

		System.out.println("Paste String? Enter y/n");
		String yesorno = sc.next();

		try {
			Robot r = new Robot();
			if (Objects.equals(yesorno, "y")) {
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_V);
				r.keyRelease(KeyEvent.VK_V);
				r.keyRelease(KeyEvent.VK_CONTROL);
				System.out.println("String coppied, CopyAndPaste method ended");
			} else if (Objects.equals(yesorno, "n")) {
				System.out.println("String not coppied, CopyAndPaste method ended");
			} else {
				System.out.println("Error, \"y\" or\"n\" not pressed");
				System.out.println("CopyAndPaste method ended");
			}

		} catch (AWTException e) {
			e.printStackTrace();
		}
		System.out.println("CopyAndPaste method ended");

	}

	public static void AltTab(Boolean DelayYesOrNo) {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ALT);
			r.delay(50);
			r.keyPress(KeyEvent.VK_TAB);
			r.delay(50);
			r.keyRelease(KeyEvent.VK_TAB);
			r.delay(50);
			r.keyRelease(KeyEvent.VK_ALT);
			r.delay(50);
			if (DelayYesOrNo == true) {
				r.delay(100);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void DeleteFile(File File) {
		System.out.println("Delete file? Enter y/n");
		String yesorno = sc.next();
		if (Objects.equals(yesorno, "y")) {
			File.delete();
			System.out.println("file deleted, DeleteFile method ended");
		} else {
			System.out.println("Y not entered, DeleteFile method ended");
		}
	}

	public static ArrayList<String> TestingWriteToFileMethod() {
		ArrayList<String> FileContentArray = new ArrayList<String>();
		for (int i = 0; i < 1; i++) {
			System.out.println("enter: FileContent, enter");
			String FileContent = sc.nextLine();
			FileContentArray.add(FileContent);
			FilesAndClipboard.LinesOfFile++;
			System.out.println("another line? y/n");
			String yesorno = sc.nextLine();
			if (Objects.equals(yesorno, "y")) {
				i--;
			}
		}
		return FileContentArray;
	}

	public static ArrayList<Integer> ReadFileToArray(File File, int limit) throws FileNotFoundException {
		ArrayList<Integer> ArrayOfLines = new ArrayList<Integer>();
		Scanner sc = new Scanner(File);
		while (sc.hasNextLine()) {
			// System.out.println(Integer.parseInt(sc.next()));
			try {
				ArrayOfLines.add(Integer.parseInt(sc.next()));
			} catch (Exception e) {
				System.out.println("ReadFileToArray Method caught an error and I dont know how to run this without");
			}
		}
		// System.out.println(ArrayOfLines);
		sc.close();
		return ArrayOfLines;
	}

	public static ArrayList<Integer> ReadFileToArray(File File, int limit, int skip) throws FileNotFoundException {
		ArrayList<Integer> ArrayOfLines = new ArrayList<Integer>();
		Scanner sc = new Scanner(File);
		int counter = 0;
		while (sc.hasNextLine()) {
			// System.out.println(Integer.parseInt(sc.next()));
			try {
				if (skip != 0) {
					System.out.println("skipped line: " + counter + "with text:" + Integer.parseInt(sc.next()));
					counter++;
					skip--;
				} else {
					ArrayOfLines.add(Integer.parseInt(sc.next()));
				}
			} catch (Exception e) {
				System.out.println("ReadFileToArray Method caught an error and I dont know how to run this without");
			}
		}
		// System.out.println(ArrayOfLines);
		sc.close();
		return ArrayOfLines;
	}

	public static int ReadFileLineOneToInt(File File) {
		int fileFirstLine = 0;
		try {
			Scanner sc = new Scanner(File);
			fileFirstLine = Integer.parseInt(sc.next());
			sc.close();
		} catch (Exception e) {
			System.out.println("ReadFileToArray Method caught an error and I dont know how to run this without");
		}
		return fileFirstLine;
	}
}