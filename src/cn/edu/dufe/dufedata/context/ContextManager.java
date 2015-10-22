package cn.edu.dufe.dufedata.context;

import java.util.Collection;
import java.util.HashSet;

import cn.edu.dufe.dufedata.common.IModule;
import cn.edu.dufe.dufedata.controller.MainController;

public class ContextManager {	
	public static void sendContext(IModule source,Context context,IModule target){
		target.reciveContext(source, context, target);
	}
}
