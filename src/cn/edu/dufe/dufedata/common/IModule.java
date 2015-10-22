package cn.edu.dufe.dufedata.common;

import cn.edu.dufe.dufedata.context.Context;

public interface IModule{
	public void init();
	public void reciveContext(IModule source,Context context,IModule target);
}
