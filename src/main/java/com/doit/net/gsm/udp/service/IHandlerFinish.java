package com.doit.net.gsm.udp.service;

import com.doit.net.gsm.udp.base.GsmMessage;

/**
 * Created by wly on 2019/7/11.
 * 处理成功接口
 */
public interface IHandlerFinish<T extends GsmMessage> {
	void workFinish(T body);
}
