import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class SimpleNotePad extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenuBar mb;
	private JTextPane textPane;
	private Menu fileMenu;
	private Menu editMenu;
	private RecentMenu recentMenu;

	// Constructor
	public SimpleNotePad() {
		mb = new JMenuBar();
		textPane = new JTextPane();
		fileMenu = new Menu("File", this);
		editMenu = new Menu("Edit", this);
		recentMenu = new RecentMenu("Recent", this);
		addFileMenuItems();
		addEditMenuItems();
		mb.add(fileMenu);
		mb.add(editMenu);

		setJMenuBar(mb);
		setTitle("A Simple Notepad Tool");
		add(new JScrollPane(textPane));
		setPreferredSize(new Dimension(600, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}

	// add all file menu items in the correct order
	public void addFileMenuItems() {
		fileMenu.addMenuItem("New File");
		fileMenu.addMenuItem("Save File");
		fileMenu.addMenuItem("Print File");
		fileMenu.addMenuItem("Open File");
		fileMenu.addMenuItem(recentMenu);
	}

	// add all edit menu items in the correct order
	public void addEditMenuItems() {
		editMenu.addMenuItem("Copy");
		editMenu.addMenuItem("Paste");
		editMenu.addMenuItem("Replace");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "New File":
			textPane.setText("");
			break;
		case "Save File":
			saveFile();
			break;
		case "Print File":
			printFile();
			break;
		case "Open File":
			openFile();
			break;
		case "Copy":
			textPane.copy();
			break;
		case "Paste":
			textPane.paste();
			break;
		case "Replace":
			textPane.replaceSelection(JOptionPane.showInputDialog(null, "Replace or insert with:"));
		default:
			// if recents contains the action then read the found file
			if (recentMenu.hasMenuItem(e.getActionCommand())) {
				readFile(new File(e.getActionCommand()));
			}
		}
	}

	// open the file
	private void openFile() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			readFile(fileChooser.getSelectedFile());
		}
	}

	// read the specified file
	private void readFile(File readFile) {
		addNewMenuItem(readFile.getAbsolutePath());
		String contents = "";
		Scanner fileReader;
		try {
			fileReader = new Scanner(readFile);
			while (fileReader.hasNextLine()) {
				contents += fileReader.nextLine() + "\n";
			}
			textPane.setText(contents);
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	// save the file
	private void saveFile() {
		File fileToWrite = null;
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileToWrite = fileChooser.getSelectedFile();
			try {
				FileWriter fileWriter = new FileWriter(fileToWrite);
				PrintWriter out = new PrintWriter(fileWriter);
				out.println(textPane.getText());
				JOptionPane.showMessageDialog(null, "File is saved successfully...");
				out.close();
				fileWriter.close();
				addNewMenuItem(fileToWrite.getAbsolutePath());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	// add the menu item if it doesn't exist
	private void addNewMenuItem(String filePath) {
		if (!recentMenu.pushMenuItemToTop(filePath)) {
			recentMenu.addMenuItem(filePath);
		}
	}

	// print the open file
	private void printFile() {
		try {
			PrinterJob pjob = PrinterJob.getPrinterJob();
			pjob.setJobName("Sample Command Pattern");
			pjob.setCopies(1);
			pjob.setPrintable(new Printable() {
				public int print(Graphics pg, PageFormat pf, int pageNum) {
					if (pageNum > 0)
						return Printable.NO_SUCH_PAGE;
					pg.drawString(textPane.getText(), 500, 500);
					paint(pg);
					return Printable.PAGE_EXISTS;
				}
			});

			if (pjob.printDialog() == true) {
				pjob.print();
			}
		} catch (PrinterException pe) {
			JOptionPane.showMessageDialog(null, "Printer error" + pe, "Printing error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new SimpleNotePad();
	}

}