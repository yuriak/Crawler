package cn.edu.dufe.dufedata.common;

import cn.edu.dufe.dufedata.controller.MainController;

public class Launcher {
	public static void main(String[] args) {
		MainController controller=new MainController();
		controller.init();
		if (args[0].equals("-plugin")) {
			controller.startCrawl(args[1]);
		}
	}
}
