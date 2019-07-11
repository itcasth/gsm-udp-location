package com.doit.net.gsm.udp.server;

import com.doit.net.gsm.udp.base.GsmMessage;
import com.doit.net.gsm.udp.service.GsmServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wly on 2019/7/11.
 * gsm工作线程
 */
public class GsmWorkThread extends Thread{

	private static final Logger log = LoggerFactory.getLogger(GsmWorkThread.class);

	private static BlockingQueue<GsmMessage> workQueue = new LinkedBlockingQueue<GsmMessage>();

	private static Map<String,GsmMessage> handlerMap = new HashMap<String,GsmMessage>();

	public GsmWorkThread(){
		setName( "GSM-Work" );
	}

	@Override
	public void run(){
		init();
	}

	private void init() {
		log.info( "gsm udp server work thread started" );
		while (GsmServerManager.isStarted){
			try {
				GsmMessage gsmMessage = workQueue.take();
				GsmServiceManager.handlerFinish( gsmMessage );
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public static void push(GsmMessage gsmMessage){
		try {
			log.info( "add gsm work queue header:{}",gsmMessage.getMessageHead() );
			workQueue.put( gsmMessage );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
