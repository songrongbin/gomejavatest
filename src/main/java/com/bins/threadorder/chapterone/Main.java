package com.bins.threadorder.chapterone;

/**
 * main类，想服务发出请求，获取请求数据
 * @author Administrator
 *
 */
public class Main {

	/**
	 * main方法
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("main start...");
		//创建服务
		Server server = new Server();
		//调用三个数据服务请求
		Data data1 = server.getData('a', 2);
		Data data2 = server.getData('b', 3);
		Data data3 = server.getData('c', 4);
		//main线程休息2秒
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		//输出数据内容
		System.out.println("data1="+data1.getContent());
		System.out.println("data2="+data2.getContent());
		System.out.println("data3="+data3.getContent());
		
		System.out.println("main end...");
		
	}

}