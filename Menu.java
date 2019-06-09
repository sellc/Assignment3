import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu extends JMenu {

	private static final long serialVersionUID = 1L;
	protected ActionListener frameActionListener;
	protected ArrayList<JMenuItem> menuItems;

	//Constructor
	public Menu(String menuName, ActionListener frameActionListener) {
		menuItems = new ArrayList<JMenuItem>();
		this.setText(menuName);
		this.frameActionListener = frameActionListener;
	}

	//add a menu item with the provided name
	public void addMenuItem(String menuItemName) {
		JMenuItem item = new JMenuItem(menuItemName);
		item.addActionListener(frameActionListener);
		item.setActionCommand(menuItemName);
		addMenuItem(item);
	}

	//add a JMenuItem to this menu
	public void addMenuItem(JMenuItem menuItem) {
		menuItems.add(menuItem);
		if (menuItems.size() > 1) {
			this.addSeparator();
		}
		this.add(menuItem);
	}

	//check if the menu has the item
	public boolean hasMenuItem(String itemName) {
		for (JMenuItem current : menuItems) {
			if (current.getText().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
}
