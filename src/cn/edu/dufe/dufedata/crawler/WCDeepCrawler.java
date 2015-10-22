package cn.edu.dufe.dufedata.crawler;

import org.apache.commons.io.FileUtils;

import cn.edu.dufe.dufedata.common.IModule;
import cn.edu.dufe.dufedata.context.Context;
import cn.edu.dufe.dufedata.context.ContextManager;
import cn.edu.dufe.dufedata.context.ContextType;
import cn.edu.dufe.dufedata.controller.IController;
import cn.edu.dufe.dufedata.controller.MainController;
import cn.edu.dufe.dufedata.plugin.IPluginProcess;
import cn.edu.dufe.dufedata.plugin.PluginProcess;
import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;

public class WCDeepCrawler extends DeepCrawler{
	private PluginProcess process;
	public PluginProcess getProcess() {
		return process;
	}


	public void setProcess(PluginProcess process) {
		this.process = process;
	}


	
	
	public WCDeepCrawler(String crawlPath) {
		super(crawlPath);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public Links visitAndGetNextLinks(Page page) {
		// TODO Auto-generated method stub
		return process.invokeProcess(page,null);
	}
	
}
