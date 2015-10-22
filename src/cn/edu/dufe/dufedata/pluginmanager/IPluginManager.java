package cn.edu.dufe.dufedata.pluginmanager;

import java.util.ArrayList;

import cn.edu.dufe.dufedata.common.IModule;
import cn.edu.dufe.dufedata.controller.IController;
import cn.edu.dufe.dufedata.plugin.IPlugin;

public interface IPluginManager extends IModule {
	public void addPlugin(IPlugin plugin);
	public void removePlugin(IPlugin plugin);
	public ArrayList<IPlugin> getPlugins();
}
