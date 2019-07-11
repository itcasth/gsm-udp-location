package com.doit.net.gsm.udp.base;

/**
 * Created by Administrator on 2019/7/11.
 */
public interface GsmHandler {
	/**
	 * 处理gsm消息
	 * @param gsmMessage
	 */
	void handler(GsmMessage gsmMessage);
}
