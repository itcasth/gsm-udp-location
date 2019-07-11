package com.doit.net.gsm.udp.service;

import com.doit.net.gsm.udp.base.GsmMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wly on 2019/7/11.
 * 数据回调管理
 */
public class GsmServiceManager {
	private final static Logger log = LoggerFactory.getLogger(GsmServiceManager.class);

	private static Map<String,List<IHandlerFinish>> callList = new HashMap<String,List<IHandlerFinish>>();

	public static synchronized void addCallBack(String header,IHandlerFinish iHandlerFinish){
		log.info( "register listener :{},class:{}",header,iHandlerFinish.getClass().getName() );
		if(callList.containsKey( header )){
			callList.get( header ).add( iHandlerFinish );
		}else {
			ArrayList<IHandlerFinish> list = new ArrayList<IHandlerFinish>();
			list.add( iHandlerFinish );
			callList.put( header,list );
		}
	}

	public static synchronized  void removeCallBack(String header){
		log.info( "remove listener header:{}",header );
		callList.remove( header );
	}

	public static void handlerFinish(GsmMessage gsmMessage){
		log.info( "call handler finish header:{}",gsmMessage.getMessageHead() );
		if(callList.containsKey( gsmMessage.getMessageHead() )){
			for (IHandlerFinish i: callList.get( gsmMessage.getMessageHead() )){
				if(i==null){
					continue;
				}
				i.workFinish( gsmMessage );
			}
		}
	}
}
