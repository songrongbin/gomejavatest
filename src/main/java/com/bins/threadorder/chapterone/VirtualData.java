package com.bins.threadorder.chapterone;

/**
 * 虚拟数据类
 * @author Administrator
 *
 */
public class VirtualData implements Data{

	//真实数据
	private RealData realData;
	//数据准备标准，true表示数据准备完成，false表示数据尚未准备完成
	private boolean ready = false;
	
	/**
	 * 设置真实数据
	 * 判断虚拟数据是否已经设置真实数据
	 * 如果没有真实数据则执行真实数据设置
	 * 如果有真实数据，则推出设置
	 * @param rdata
	 */
	public synchronized void setRealData(RealData rdata){
		if(ready){
			return;
		}
		System.out.println(Thread.currentThread().getName()+" set RealData");
		this.realData = rdata;
		this.ready = true;
		notifyAll();
	}

	/**
	 * 获取数据内容
	 * 判断真实数据是否设置，
	 * 如果设置则返回真实数据内容
	 * 如果没有设置则线程等待其他线程设置数据
	 */
	@Override
	public synchronized String getContent() {
		while (!ready){
			try {
				System.out.println(Thread.currentThread().getName()+"{wait}");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData.getContent();
	}

}