package cn.edu.dufe.dufedata.context;

import cn.edu.dufe.dufedata.common.IModule;
import cn.edu.dufe.dufedata.plugin.IPlugin;

public class Context {
	


	public IModule getToInvoke() {
		return toInvoke;
	}

	public void setToInvoke(IModule toInvoke) {
		this.toInvoke = toInvoke;
	}

	public IModule getOriginalInvoker() {
		return originalInvoker;
	}

	public void setOriginalInvoker(IModule originalInvoker) {
		this.originalInvoker = originalInvoker;
	}

	public ContextContent getContextContent() {
		return contextContent;
	}

	public void setContextContent(ContextContent contextContent) {
		this.contextContent = contextContent;
	}

	private int contextType;
	private ContextContent contextContent;
	private IModule originalInvoker;
	private IModule toInvoke;
	public int getContextType() {
		return contextType;
	}

	public void setContextType(int contextType) {
		this.contextType = contextType;
	}
	
	
}
