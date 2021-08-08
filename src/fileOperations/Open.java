package fileOperations;

import java.io.File;

import javax.swing.JFileChooser;

public class Open {
	
	public static File openFile() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}
		return null;
	}

}
