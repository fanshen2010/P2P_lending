package cn.com.p2p.framework.report.bean;

import java.io.OutputStream;
import java.util.Map;

import cn.com.p2p.framework.report.ExcleTablWorkBook;

public interface Writer {
	public void writer(OutputStream out,ExcleTablWorkBook tableWrokBook,Map<String,Object> dataSource);
}
