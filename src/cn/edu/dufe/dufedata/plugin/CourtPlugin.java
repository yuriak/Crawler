package cn.edu.dufe.dufedata.plugin;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.dufe.dufedata.common.IModule;
import cn.edu.dufe.dufedata.context.Context;
import cn.edu.dufe.dufedata.context.ContextManager;
import cn.edu.dufe.dufedata.context.ContextType;
import cn.edu.dufe.dufedata.context.CrawlContextContent;
import cn.edu.dufe.dufedata.crawler.Crawler;
import cn.edu.dufe.dufedata.pluginmanager.IPluginManager;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.util.RegexRule;

public class CourtPlugin implements IPlugin {
	private static Logger logger=LoggerFactory.getLogger(CourtPlugin.class);
	private ArrayList<String> seeds;
	private IModule invoker;
	private Context crawlContext;
	private CrawlContextContent content;
	public static String pluginID="court";
	private PluginProcess pluginProcess;
	private JSONArray array;
	
	@Override
	public void init() {
		array=new JSONArray();
		crawlContext=new Context();
		crawlContext.setContextType(ContextType.CRAWL);
		crawlContext.setOriginalInvoker(this);
		content=new CrawlContextContent();
		content.setAutoParse(true);
		RegexRule regexRule=new RegexRule();
		regexRule.addNegative("^(file|ftp|mailto):");
		regexRule.addNegative("\\.(gif|GIF|jpg|JPG|png|PNG|ico|ICO|css|CSS|sit|SIT|eps|EPS|wmf|WMF|zip|ZIP|ppt|PPT|mpg|MPG|xls|XLS|gz|GZ|rpm|RPM|tgz|TGZ|mov|MOV|exe|EXE|jpeg|JPEG|bmp|BMP|js|JS)$");
		regexRule.addNegative("[?*!@=]");
		regexRule.addPositive("http://www.court.gov.cn/zgcpwsw/[a-z]*/.*");
		seeds=new ArrayList<>();
		seeds.add("http://www.court.gov.cn/zgcpwsw/");
		content.setTopN(1000);
		content.setRegexRule(regexRule);
		content.setDepth(4);
		content.setSeeds(seeds);
		pluginProcess=new PluginProcess();
		pluginProcess.setProcess(new IPluginProcess() {
			@Override
			public Links process(Page page, Links links) {
				if (page.getUrl().split("/")[page.getUrl().split("/").length-1].matches("t\\d*_\\d*\\.htm")) {
					String string=page.getDoc().select("#wenshu").text();
					JSONObject object=new JSONObject();
					object.put("content", string.trim());
					array.put(object);
				}
				return links;
			}
		});
		content.setProcess(pluginProcess);
		content.setCrawlerType(Crawler.BREADTH);
		crawlContext.setContextContent(content);
		logger.info("court initiated");
	}

	@Override
	public void reciveContext(IModule source, Context context, IModule target) {
		if (source instanceof IPluginManager) {
			if (invoker==null) {
				invoker=source;
			}
			if (context.getContextType()==ContextType.INIT) {
				init();
			}
			if (context.getContextType()==ContextType.CRAWL) {
				crawl();
			}
			if (context.getContextType()==ContextType.REPORT) {
				System.out.println("done");
			}
		}
	}

	@Override
	public void crawl() {
		ContextManager.sendContext(this, crawlContext, invoker);
		String time=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Timestamp(System.currentTimeMillis()));
		try {
			FileUtils.writeStringToFile(new File("data/court"+time+".txt"), array.toString().trim(), "UTF-8",false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void stop() {
		
	}

	@Override
	public String getPluginID() {
		return this.pluginID;
	}

	@Override
	public void setPluginID(String pluginID) {
		// TODO Auto-generated method stub
		this.pluginID=pluginID;
	}

	@Override
	public void setPluginProcess(IPluginProcess pluginProcess) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPluginProcess getPluginProcess() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
