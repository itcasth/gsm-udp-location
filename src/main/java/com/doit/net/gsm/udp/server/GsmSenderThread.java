package com.doit.net.gsm.udp.server;

import com.doit.net.gsm.udp.base.GsmMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wly on 2019/7/11.
 * gsm发送线程
 */
public class GsmSenderThread extends Thread {
	private final static Logger log = LoggerFactory.getLogger(GsmSenderThread.class);

	private static BlockingQueue<GsmMessage> senderQueue = new LinkedBlockingQueue<GsmMessage>();

	public GsmSenderThread(){
		setName( "GSM-Sender" );
	}

	@Override
	public void run(){
		init();
	}

	private void init() {
		log.info( "gsm udp sender thread started " );
		while (true){
			try {
				GsmMessage gsmMessage = senderQueue.take();
				byte[] bytes = gsmMessage.getContent().getBytes();
				log.info( "send header:{},content:{}",gsmMessage.getMessageHead(),gsmMessage.getContent() );
				DatagramPacket packet = new DatagramPacket( bytes, bytes.length, gsmMessage.getSocketAddress() );
				DatagramSocket socket = getSocket();
				if(socket==null){
					log.warn( "Not found gsm socket:{}",gsmMessage.getInetSocketAddress().getPort() );
				}
				socket.send( packet );
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public static DatagramSocket getSocket(){
		return GsmServerManager.getDatagramSocket();
	}

	public static void put(GsmMessage gsmMessage){
		try {
			log.info( "添加消息 Header:{},Content:{}到发送队列",gsmMessage.getMessageHead(),gsmMessage.getContent() );
			senderQueue.put( gsmMessage );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
