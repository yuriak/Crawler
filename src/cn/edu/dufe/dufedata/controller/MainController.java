package cn.edu.dufe.dufedata.controller;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.dufe.dufedata.common.IModule;
import cn.edu.dufe.dufedata.context.Context;
import cn.edu.dufe.dufedata.context.ContextManager;
import cn.edu.dufe.dufedata.context.ContextType;
import cn.edu.dufe.dufedata.crawler.Crawler;
import cn.edu.dufe.dufedata.crawler.ICrawler;
import cn.edu.dufe.dufedata.crawler.WCDeepCrawler;
import cn.edu.dufe.dufedata.plugin.IPlugin;
import cn.edu.dufe.dufedata.pluginmanager.IPluginManager;
import cn.edu.dufe.dufedata.pluginmanager.PluginManager;

public class MainController implements IController {
	public ArrayList<IPlugin> getPlugins() {
		return plugins;
	}

	public void setPlugins(ArrayList<IPlugin> plugins) {
		this.plugins = plugins;
	}

	public static Logger logger=LoggerFactory.getLogger(MainController.class);
	private IPluginManager pluginManager;
	private ICrawler crawler;
	private ArrayList<IPlugin> plugins;
	@Override
	public void init() {
		//读取配置文件
		logger.info("MainController initiated");
		//实例化对象
		pluginManager=new PluginManager();
		crawler=new Crawler();
		//编写initContext
		Context initContext=new Context();
		initContext.setContextType(ContextType.INIT);
		initContext.setOriginalInvoker(this);
		//发送initContext给各个模块
		ContextManager.sendContext(this, initContext, pluginManager);
		ContextManager.sendContext(this, initContext, crawler);
		plugins=pluginManager.getPlugins();
	}
	
	private void crawl(IPlugin plugin){
		logger.info("Strat to crawl with plugin: "+plugin.getPluginID());
		Context crawlContext=new Context();
		crawlContext.setContextType(ContextType.CRAWL);
		crawlContext.setOriginalInvoker(this);
		crawlContext.setToInvoke(plugin);
		ContextManager.sendContext(this, crawlContext, pluginManager);
	}
	
	@Override
	public void reciveContext(IModule source, Context context, IModule target) {
		if (source==pluginManager) {
			if (context.getContextType()==ContextType.CRAWL) {
				ContextManager.sendContext(this, context, crawler);
			}
		}else if (source==crawler) {
			
		}
	}
	
	public static void main(String[] args) {
		MainController controller=new MainController();
		controller.init();
		IPlugin plugin=controller.getPlugins().get(0);
		System.out.println(plugin.getPluginID());
		controller.crawl(plugin);
	}
	
	private IPlugin checkPlugin(String pluginID){
		for (IPlugin plugin : plugins) {
			if (pluginID.equals(plugin.getPluginID())) {
				return plugin;
			}
		}
		return null;
	}
	
	public void startCrawl(String pluginID){
		if (checkPlugin(pluginID)==null) {
			logger.warn("No such plugin: "+pluginID);
			return;
		}
		crawl(checkPlugin(pluginID));
		
	}
}
