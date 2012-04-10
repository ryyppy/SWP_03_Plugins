import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import plugin.AbstractPlugin;


public class PluginFactory {

	private static Set<String> activated = new TreeSet<String>();
	private static Set<String> available = new TreeSet<String>();
	
	private static URLClassLoader loader;
	
	
	public static void loadPlugins(String path){
		available = new TreeSet<String>();
		// scan plugin directory for Plugin.jar extension
		ArrayList<File> plugins = new ArrayList<File>();		
		File dir = new File(path);
		for(File file: dir.listFiles()){
			if(file.getName().endsWith("Plugin.jar")){
				plugins.add(file);
				// push plugin into available plugins list
				Pattern pattern = Pattern.compile("(.+)Plugin.jar$");
				Matcher matcher = pattern.matcher(file.getName());
				if(matcher.matches()){
					available.add(matcher.group(1).toLowerCase() + ".Plugin");
				}
			}
		}

        URL [] urls = new URL[plugins.size()];
        for(int i = 0; i < plugins.size(); i++){
            try{
                File f = plugins.get(i);
                URL u = f.toURI().toURL();
                urls[i] = u;
            }catch(MalformedURLException mue){
                mue.printStackTrace();
            }
        }

		loader = new URLClassLoader(urls);
		
	}
	
	public static AbstractPlugin getPluginInstance(String className) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException{
		return (AbstractPlugin) loader.loadClass(className).getConstructor().newInstance();
	}
	
	public static void activatePlugin(String className){
		activated.add(className);
	}
	
	public static void deactivatePlugin(String className){
		activated.remove(className);
	}

	public static String[] getAvailablePlugins(){
		return available.toArray(new String[available.size()]);
	}
	
	public static String[] getActivatedPlugins(){
		return activated.toArray(new String[available.size()]);
	}
}
