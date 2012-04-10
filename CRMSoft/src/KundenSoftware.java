import javax.swing.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class KundenSoftware {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) {
		String path = "CRMSoft/plugins/";
		PluginFactory.loadPlugins(path);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TabbedPanePanel tp = new TabbedPanePanel();

                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                tp.createAndShowGUI();
            }
        });
	}

}
