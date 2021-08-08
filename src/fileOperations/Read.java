package fileOperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Read {
	public static String readFile(File readFile) {
		String contents = "";
		Scanner fileReader;
		try {
			fileReader = new Scanner(readFile);
			while (fileReader.hasNextLine()) {
				contents += fileReader.nextLine() + "\n";
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return contents;
	}
}
