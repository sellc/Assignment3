import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class RecentMenu extends Menu {

	private static final long serialVersionUID = 1L;

	//Constructor
	public RecentMenu(String menuName, ActionListener frameActionListener) {
		super(menuName, frameActionListener);
	}

	@Override
	public void addMenuItem(JMenuItem menuItem) {
		menuItems.add(menuItem);
		this.add(menuItem, 0);
	}

	//push the selected menu item to the top of the list
	public boolean pushMenuItemToTop(String absolutePath) {
		for (JMenuItem current : menuItems) {
			if (current.getText().equals(absolutePath)) {
				remove(current);
				add(current, 0);
				return true;
			}
		}
		return false;

	}

}
