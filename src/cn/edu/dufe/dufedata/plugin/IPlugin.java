package cn.edu.dufe.dufedata.plugin;

import cn.edu.dufe.dufedata.common.IModule;
import cn.edu.dufe.dufedata.context.Context;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;

public interface IPlugin extends IModule {
	public void crawl();
	public void stop();
	public String getPluginID();
	public void setPluginID(String pluginID);
	public void setPluginProcess(IPluginProcess pluginProcess);
	public IPluginProcess getPluginProcess();
}
