package fileOperations;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JOptionPane;

public class Print {

	public static void printFile(String content) {
		try {
			PrinterJob pjob = PrinterJob.getPrinterJob();
			pjob.setJobName("Sample Command Pattern");
			pjob.setCopies(1);
			pjob.setPrintable(new Printable() {
				public int print(Graphics pg, PageFormat pf, int pageNum) {
					if (pageNum > 0)
						return Printable.NO_SUCH_PAGE;
					pg.drawString(content, 500, 500);
//					paint(pg);
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
}
