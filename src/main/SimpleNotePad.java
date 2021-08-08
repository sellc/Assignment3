package main;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import gui.Menu;
import gui.RecentMenu;

public class SimpleNotePad extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenuBar mb;
	private JTextPane textPane;
	private Menu fileMenu;
	private Menu editMenu;
	private RecentMenu recentMenu;
	private File currentFile;

	public SimpleNotePad() {
		mb = gui.MenuBar.buildMenuBar();
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

	public void addFileMenuItems() {
		fileMenu.addMenuItem("New File");
		fileMenu.addMenuItem("Save File");
		fileMenu.addMenuItem("Print File");
		fileMenu.addMenuItem("Open File");
		fileMenu.addMenuItem(recentMenu);
	}

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
			addNewMenuItem(fileOperations.Save.saveFile(textPane.getText()));
			break;
		case "Print File":
			fileOperations.Print.printFile(textPane.getText());
			break;
		case "Open File":
			currentFile = fileOperations.Open.openFile();
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
			if (recentMenu.hasMenuItem(e.getActionCommand())) {
				fileOperations.Read.readFile(new File(e.getActionCommand()));
//				addNewMenuItem(readFile.getAbsolutePath());
//				textPane.setText(contents);
			}
		}
	}

	private void addNewMenuItem(String filePath) {
		if (!recentMenu.pushMenuItemToTop(filePath)) {
			recentMenu.addMenuItem(filePath);
		}
	}

	public static void main(String[] args) {
		new SimpleNotePad();
	}

}