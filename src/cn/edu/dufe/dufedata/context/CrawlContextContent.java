package cn.edu.dufe.dufedata.context;

import java.util.ArrayList;

import cn.edu.dufe.dufedata.plugin.IPlugin;
import cn.edu.dufe.dufedata.plugin.IPluginProcess;
import cn.edu.dufe.dufedata.plugin.PluginProcess;
import cn.edu.hfut.dmic.webcollector.extract.Extractor;
import cn.edu.hfut.dmic.webcollector.net.HttpRequester;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;
import cn.edu.hfut.dmic.webcollector.util.RegexRule;

public class CrawlContextContent extends ContextContent {
	public static int getDepth() {
		return depth;
	}
	public static void setDepth(int depth) {
		CrawlContextContent.depth = depth;
	}
	public static int getCrawlerType() {
		return crawlerType;
	}
	public static void setCrawlerType(int crawlerType) {
		CrawlContextContent.crawlerType = crawlerType;
	}
	public PluginProcess getProcess() {
		return process;
	}
	public void setProcess(PluginProcess process) {
		this.process = process;
	}
	public static int getRetry() {
		return retry;
	}
	public static void setRetry(int retry) {
		CrawlContextContent.retry = retry;
	}
	public static int getMaxRetry() {
		return maxRetry;
	}
	public static void setMaxRetry(int maxRetry) {
		CrawlContextContent.maxRetry = maxRetry;
	}
	public static HttpRequester getHttpRequester() {
		return httpRequester;
	}
	public static void setHttpRequester(HttpRequester httpRequester) {
		CrawlContextContent.httpRequester = httpRequester;
	}
	public static boolean isResumable() {
		return resumable;
	}
	public static void setResumable(boolean resumable) {
		CrawlContextContent.resumable = resumable;
	}
	public static int getThreads() {
		return threads;
	}
	public static void setThreads(int threads) {
		CrawlContextContent.threads = threads;
	}
	public static Integer getTopN() {
		return topN;
	}
	public static void setTopN(Integer topN) {
		CrawlContextContent.topN = topN;
	}
	public static ArrayList<String> getSeeds() {
		return seeds;
	}
	public static void setSeeds(ArrayList<String> seeds) {
		CrawlContextContent.seeds = seeds;
	}
	public static ArrayList<String> getForcedSeeds() {
		return forcedSeeds;
	}
	public static void setForcedSeeds(ArrayList<String> forcedSeeds) {
		CrawlContextContent.forcedSeeds = forcedSeeds;
	}
	public static boolean isAutoParse() {
		return autoParse;
	}
	public static void setAutoParse(boolean autoParse) {
		CrawlContextContent.autoParse = autoParse;
	}
	public static RegexRule getRegexRule() {
		return regexRule;
	}
	public static void setRegexRule(RegexRule regexRule) {
		CrawlContextContent.regexRule = regexRule;
	}
	
	//Context的属性
	//Crawler种类: 0:deep,1:breadth,2:multi_extractor,-1:none
	private static int crawlerType=-1;
	private PluginProcess process;
	
	//B and D and M共有属性
	private static int retry=3;
	private static int maxRetry=20;
	private static HttpRequester httpRequester = new HttpRequesterImpl();
	private static boolean resumable = false;
	private static int threads = 50;
	private static Integer topN=1;
	private static ArrayList<String> seeds = new ArrayList<String>();
	private static ArrayList<String> forcedSeeds = new ArrayList<String>();
    private static int depth=0;
    //B的属性
	private static boolean autoParse=true;
	private static RegexRule regexRule = new RegexRule();
    
    //M的属性
}
