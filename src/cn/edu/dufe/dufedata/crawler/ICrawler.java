package cn.edu.dufe.dufedata.crawler;

import cn.edu.dufe.dufedata.common.IModule;
import cn.edu.dufe.dufedata.context.Context;

public interface ICrawler extends IModule {
	public void crawl(Context context);
}
