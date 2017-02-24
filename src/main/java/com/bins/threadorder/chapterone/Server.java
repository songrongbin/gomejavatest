package com.bins.threadorder.chapterone;

/**
 * 服务类，处理服务请求
 * @author Administrator
 *
 */
public class Server {

	/**
	 * 返回虚拟数据结果，启动线程处理数据内容。
	 * @param c
	 * @param count
	 * @return
	 */
	public Data getData(final char c, final int count){
		
		final VirtualData vdata = new VirtualData();
		System.out.println("start getData:"+c);
		//启动新线程处理数据
		new Thread(){
			public  void run(){
				System.out.println(Thread.currentThread().getName()+".start."+c);
				RealData rdata = new RealData();
				rdata.setContent(c, count);//设置真实数据内容
				vdata.setRealData(rdata);//设置真实数据与虚拟数据关联
				System.out.println(Thread.currentThread().getName()+".end."+c);
			}
		}.start();
		System.out.println("end getData:"+c);
		return vdata;
	}
	
}