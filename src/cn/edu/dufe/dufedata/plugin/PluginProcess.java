package cn.edu.dufe.dufedata.plugin;

import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;

public class PluginProcess {
	IPluginProcess pluginProcess;
	
	public void setProcess(IPluginProcess pluginProcess){
		this.pluginProcess=pluginProcess;
	}
	
	public Links invokeProcess(Page page,Links links){
		if (this.pluginProcess!=null) {
			return this.pluginProcess.process(page, links);			
		}else {
			return null;
		}
		
	}
}
