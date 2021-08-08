package fileOperations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Save {
	
	public static String saveFile(String content) {
		File fileToWrite = null;
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileToWrite = fileChooser.getSelectedFile();
			try {
				PrintWriter out = new PrintWriter(new FileWriter(fileToWrite));
				out.println(content);
				JOptionPane.showMessageDialog(null, "File is saved successfully...");
				out.close();
			} catch (IOException ex) {
				return "";
			}
			return fileToWrite.getAbsolutePath();
		}
		return "";
	}

}
