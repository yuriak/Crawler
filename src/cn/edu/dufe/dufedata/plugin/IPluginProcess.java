package cn.edu.dufe.dufedata.plugin;

import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;

public interface IPluginProcess {
	public Links process(Page page,Links links);
}
