package mayuso.LoLPing;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main {

	public static void main(String[] args) {
		try {

			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
			/*
			 * try { UIManager.setLookAndFeel(UIManager.
			 * getSystemLookAndFeelClassName()); } catch (Exception e) {
			 * e.printStackTrace(); }
			 */
		} catch (Exception e) {
			// If Nimbus is not available, fall back to cross-platform
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {

			}
		}

		new UI();
	}

}
