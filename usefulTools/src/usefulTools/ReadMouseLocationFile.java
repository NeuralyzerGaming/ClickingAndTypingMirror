package usefulTools;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
//import java.util.ArrayList;
import java.util.ArrayList;

public class ReadMouseLocationFile {

	public static File ClassFile = FilesAndClipboard.CreateFile("MouseSequence");

	public static void main(String[] args) throws FileNotFoundException, AWTException {
		init();
		ArrayList<Integer> MouseLocationsArray = FilesAndClipboard.ReadFileToArray(ReadMouseLocationFile.ClassFile,1);
		System.out.println(MouseLocationsArray);
		MouseClick.clickFromArray(MouseLocationsArray);
		cleanup();
	}

	public static void init() {
		FilesAndClipboard.GetFilePath(ReadMouseLocationFile.ClassFile);
		try {
			FilesAndClipboard.OpenFile(ReadMouseLocationFile.ClassFile);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void cleanup() {
		FilesAndClipboard.DeleteFile(ReadMouseLocationFile.ClassFile);
	}
}