package cn.edu.dufe.dufedata.pluginmanager;

import java.util.ArrayList;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.dufe.dufedata.common.IModule;
import cn.edu.dufe.dufedata.context.Context;
import cn.edu.dufe.dufedata.context.ContextManager;
import cn.edu.dufe.dufedata.context.ContextType;
import cn.edu.dufe.dufedata.controller.IController;
import cn.edu.dufe.dufedata.plugin.IPlugin;
import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;

public class PluginManager implements IPluginManager {
	public static Logger logger = LoggerFactory.getLogger(PluginManager.class);
	private IModule invoker;
	private ArrayList<IPlugin> plugins;
	private PluginClassLoader loader;
	@Override
	public void init() {
		plugins=new ArrayList<>();
		loader=new PluginClassLoader();
		plugins=loader.loadPlugins();
		logger.info("PluginManager initiated, plugin number: "+plugins.size());
		for (IPlugin plugin : plugins) {
			System.out.println("pluginID: "+plugin.getPluginID());
		}
	}
	
	@Override
	public void reciveContext(IModule source, Context context, IModule target) {
		if (source instanceof IController) {
			if (invoker==null) {
				this.invoker= source;
			}
			if (context.getContextType()==ContextType.INIT) {
				init();
				for (IPlugin plugin : plugins) {
					ContextManager.sendContext(this, context, plugin);
				}
			}
			if (context.getContextType()==ContextType.CRAWL) {
				ContextManager.sendContext(this, context, context.getToInvoke());
			}
		}else if (source instanceof IPlugin) {
			if (!plugins.contains(source)) {
				plugins.add((IPlugin) source);
			}
			if (context.getContextType()==ContextType.CRAWL) {
				ContextManager.sendContext(this, context, invoker);
			}
		}
	}

	@Override
	public void addPlugin(IPlugin plugin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePlugin(IPlugin plugin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<IPlugin> getPlugins() {
		// TODO Auto-generated method stub
		return this.plugins;
	}
	
}
