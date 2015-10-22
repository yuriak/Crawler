package test;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

import cn.edu.dufe.dufedata.bean.PageBean;
import cn.edu.dufe.dufedata.util.TimeUtil;

public class Jsontest {
	public static void main(String[] args) throws Exception {
		PageBean bean=new PageBean();
		bean.setUrl("aa");
		bean.setHtml("bb");
		bean.setDate(TimeUtil.convertStringToTimeStamp("2015-10-01 00:00:00"));
		
		JSONObject object=new JSONObject(bean);
		System.out.println(object.toString());
		
	}
}
