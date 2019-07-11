package com.doit.net.gsm.udp.server;

import com.doit.net.gsm.udp.base.GsmMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;

/**
 * Created by wly on 2019/7/11.
 * gsm接收线程
 */
public class GsmReceiverThread extends Thread {
	private final static Logger log = LoggerFactory.getLogger(GsmReceiverThread.class);

	private static final int BUFFER_SIZE = 1024;

	public GsmReceiverThread(){
		setName( "GSM-Receiver" );
	}

	@Override
	public void run(){
		init();
	}

	private void init() {
		log.info( "GSM UDP Receiver thread started" );
		while (GsmServerManager.isStarted){
			try {
				byte[] bytes = new byte[BUFFER_SIZE];
				DatagramPacket datagramPacket = new DatagramPacket( bytes,bytes.length );
				if(datagramPacket==null){
					continue;
				}
				GsmServerManager.receive( datagramPacket );
				if(datagramPacket==null){
					continue;
				}
				//添加到工作线程队列
				GsmMessage gsmMessage = new GsmMessage(  );
				byte[] data = datagramPacket.getData();
				int length = datagramPacket.getLength();
				String content = new String( data,0,length );
				gsmMessage.setContent( content );
				gsmMessage.setSocketAddress( datagramPacket.getSocketAddress() );
				log.info( "Receive GSM message:{}",gsmMessage.toString() );
				GsmWorkThread.push( gsmMessage );
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
