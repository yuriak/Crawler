package cn.edu.dufe.dufedata.crawler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.dufe.dufedata.common.CommonConfig;
import cn.edu.dufe.dufedata.common.IModule;
import cn.edu.dufe.dufedata.context.Context;
import cn.edu.dufe.dufedata.context.ContextContent;
import cn.edu.dufe.dufedata.context.ContextType;
import cn.edu.dufe.dufedata.context.CrawlContextContent;
import cn.edu.dufe.dufedata.controller.IController;

public class Crawler implements ICrawler {
	public static Logger logger=LoggerFactory.getLogger(Crawler.class);
	private IModule invoker;
	public static final int DEEP=0;
	public static final int BREADTH=1;
	public static final int MULTI_EXTRACTOR=2;
	public static final String BREADTH_CRAWL_DB_PATH=CommonConfig.DATA_DIR+File.separator+"breadth_crawldb";
	public static final String DEEP_CRAWL_DB_PATH=CommonConfig.DATA_DIR+File.separator+"deep_crawldb";
	public static final String ME_CRAWL_DB_PATH=CommonConfig.DATA_DIR+File.separator+"me_crawldb";
	@Override
	public void init() {
		logger.info("Crawler initiated");
	}

	@Override
	public void reciveContext(IModule source, Context context, IModule target) {
		if (source instanceof IController) {
			if (invoker==null) {
				invoker=source;
			}
			if (context.getContextType()==ContextType.INIT) {
				init();
			}
			if (context.getContextType()==ContextType.CRAWL) {
				crawl(context);
			}
		}
	}

	@Override
	public void crawl(Context context) {
		CrawlContextContent infoContent=(CrawlContextContent) context.getContextContent();
		if (infoContent.getCrawlerType()==Crawler.DEEP) {
			try {
				WCDeepCrawler deepCrawler=new WCDeepCrawler(Crawler.DEEP_CRAWL_DB_PATH);
				deepCrawler.setForcedSeeds(infoContent.getForcedSeeds());
				deepCrawler.setHttpRequester(infoContent.getHttpRequester());
				deepCrawler.setMaxRetry(infoContent.getMaxRetry());
				deepCrawler.setProcess(infoContent.getProcess());
				deepCrawler.setResumable(infoContent.isResumable());
				deepCrawler.setRetry(infoContent.getRetry());
				deepCrawler.setSeeds(infoContent.getSeeds());
				deepCrawler.setThreads(infoContent.getThreads());
				deepCrawler.setTopN(infoContent.getTopN());
				deepCrawler.start(infoContent.getDepth());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (infoContent.getCrawlerType()==Crawler.BREADTH) {
			try {
				WCBreadthCrawler breadthCrawler=new WCBreadthCrawler(Crawler.BREADTH_CRAWL_DB_PATH, infoContent.isAutoParse());
				breadthCrawler.setAutoParse(infoContent.isAutoParse());
				breadthCrawler.setForcedSeeds(infoContent.getForcedSeeds());
				breadthCrawler.setHttpRequester(infoContent.getHttpRequester());
				breadthCrawler.setMaxRetry(infoContent.getMaxRetry());
				breadthCrawler.setProcess(infoContent.getProcess());
				breadthCrawler.setRegexRule(infoContent.getRegexRule());
				breadthCrawler.setResumable(infoContent.isResumable());
				breadthCrawler.setRetry(infoContent.getRetry());
				breadthCrawler.setSeeds(infoContent.getSeeds());
				breadthCrawler.setThreads(infoContent.getThreads());
				breadthCrawler.setTopN(infoContent.getTopN());
				breadthCrawler.start(infoContent.getDepth());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (infoContent.getCrawlerType()==Crawler.MULTI_EXTRACTOR) {
			try {
				WCMultiExtractorCrawler multiExtractorCrawler=new WCMultiExtractorCrawler(Crawler.ME_CRAWL_DB_PATH, infoContent.isAutoParse());
				multiExtractorCrawler.setAutoParse(infoContent.isAutoParse());
				multiExtractorCrawler.setForcedSeeds(infoContent.getForcedSeeds());
				multiExtractorCrawler.setHttpRequester(infoContent.getHttpRequester());
				multiExtractorCrawler.setMaxRetry(infoContent.getMaxRetry());
				multiExtractorCrawler.setRegexRule(infoContent.getRegexRule());
				multiExtractorCrawler.setResumable(infoContent.isResumable());
				multiExtractorCrawler.setRetry(infoContent.getRetry());
				multiExtractorCrawler.setSeeds(infoContent.getSeeds());
				multiExtractorCrawler.setThreads(infoContent.getThreads());
				multiExtractorCrawler.setTopN(infoContent.getTopN());
				multiExtractorCrawler.start(infoContent.getDepth());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			return;
		}
	}
	
}
