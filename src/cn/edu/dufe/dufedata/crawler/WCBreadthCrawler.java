package cn.edu.dufe.dufedata.crawler;

import cn.edu.dufe.dufedata.common.IModule;
import cn.edu.dufe.dufedata.context.Context;
import cn.edu.dufe.dufedata.plugin.IPluginProcess;
import cn.edu.dufe.dufedata.plugin.PluginProcess;
import cn.edu.dufe.dufedata.pluginmanager.IPluginManager;
import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.util.RegexRule;

public class WCBreadthCrawler extends BreadthCrawler {
	public PluginProcess getProcess() {
		return process;
	}

	public void setProcess(PluginProcess process) {
		this.process = process;
	}

	private PluginProcess process;
	
	public WCBreadthCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visit(Page page, Links links) {
		// TODO Auto-generated method stub
		process.invokeProcess(page, links);
		
	}
	
}
