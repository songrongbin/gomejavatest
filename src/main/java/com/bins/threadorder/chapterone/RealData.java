package com.bins.threadorder.chapterone;

import java.util.Random;

/**
 * 真实数据类
 * @author Administrator
 *
 */
public class RealData implements Data{

	//数据内容
	private String content;
	private Random random = new Random();
	
	/**
	 * 设置数据内容
	 * @param c
	 * @param count
	 */
	public void setContent(char c, int count){
		char[] buffer = new char[count];
		for (int i = 0; i < count; i++) {
			buffer[i] = c;
			try {
				Thread.sleep(random.nextInt(1000));//随机休息
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		content = new String(buffer);
	}
	
	/**
	 * 获取数据内容
	 */
	@Override
	public String getContent() {
		return content;
	}

}