package cn.edu.dufe.dufedata.pluginmanager;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;











import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.dufe.dufedata.common.CommonConfig;
import cn.edu.dufe.dufedata.plugin.IPlugin;

public class PluginClassLoader extends URLClassLoader {
	public static Logger logger=LoggerFactory.getLogger(PluginClassLoader.class);
	private File pluginDir;
	private List<File> pluginFiles = new ArrayList<File>();
	private ArrayList<IPlugin> plugins=new ArrayList<>();
	private ArrayList<String> pluginIDs=new ArrayList<>();
	public PluginClassLoader() {
		super(new URL[] {}, findParentClassLoader());
		pluginDir=new File(CommonConfig.PLUGIN_DIR);
	}

	public void addDirectory(File directory) {
		try {
			addURLFile(directory.toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void addURLFile(URL file) {
		try {
			URLConnection uc = file.openConnection();
			if (uc instanceof JarURLConnection) {
				uc.setUseCaches(true);
				((JarURLConnection) uc).getManifest();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		addURL(file);
	}

	private static ClassLoader findParentClassLoader() {

		return ClassLoader.getSystemClassLoader();
	}
	
	public ArrayList<IPlugin> loadPlugins(){
		File[] listFiles = pluginDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		for (int i = 0; i < listFiles.length; i++) {
			File pluginFile = listFiles[i];
			if(pluginFiles.contains(pluginFile)){
				continue;
			}
			try {
				Properties prop = new Properties();
				prop.load(new FileInputStream(new File(pluginFile,
						"conf.properties")));
				PluginClassLoader loader = new PluginClassLoader();
				File jarFile=listFiles[i].listFiles(new FileFilter() {
					
					@Override
					public boolean accept(File pathname) {
						return (pathname.isFile()&&pathname.getName().endsWith(".jar"));
					}
				})[0];
				loader.addURLFile(jarFile.toURI().toURL());
				Class<?> clazz=loader.loadClass(prop.getProperty("pluginClass"));
				IPlugin plugin=(IPlugin) clazz.newInstance();
				if (pluginIDs.contains(plugin.getPluginID())) {
					throw new Exception("Same pluginID existed");
				}
				pluginIDs.add(plugin.getPluginID());
				plugins.add(plugin);
			} catch (Exception e) {
				logger.error("Can not load plugin：" + pluginFile);
				e.printStackTrace();
				return null;
			}
		}
		return plugins;
	}
	
	public static void main(String[] args) {
		PluginClassLoader pluginClassLoader=new PluginClassLoader();
		pluginClassLoader.loadPlugins();
	}
}
