package com.doit.net.gsm.udp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

/**
 * Created by wly on 2019/7/11.
 * gsm服务管理器
 */
public class GsmServerManager {
	private final static Logger log = LoggerFactory.getLogger(GsmServerManager.class);

	private static DatagramSocket datagramSocket;

	private static int PORT = 9001;
	private static final int TIME_OUT = 3*60*1000;

	public static boolean isStarted = false;

	public static void startListener(int port){
		try {
			PORT = port;
			datagramSocket = new DatagramSocket( PORT );
			datagramSocket.setSoTimeout( TIME_OUT );
			datagramSocket.setReceiveBufferSize( 1024*20000 );
			isStarted = true;
			new GsmReceiverThread().start();
			new GsmWorkThread().start();
			new GsmSenderThread().start();
			log.info( "【GSM-Server】 started on port:{}",PORT );
		}catch (Exception e){
			log.error( "【GSM-Server】 启动异常，端口:{},异常原因：",port,e.getMessage() );
		}
	}

	public static void startListener(){
		System.out.println("开始监听");
		startListener( PORT );
	}

	private static DatagramSocket clientSocket = null;

	public static DatagramSocket getDatagramSocket() {
		return datagramSocket;
	}

	public static void setDatagramSocket(DatagramSocket datagramSocket) {
		GsmServerManager.datagramSocket = datagramSocket;
	}

	/**
	 * 接收数据包 该方法会造成线程阻塞
	 * @param packet
	 * @return
	 */
	public static DatagramPacket receive(DatagramPacket packet){
		try {
			datagramSocket.receive( packet );
			return packet;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void stop(){
		try {
			isStarted = false;
			if(datagramSocket.isClosed()==false){
				datagramSocket.close();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 将响应数据发送给请求端
	 * @param packet
	 */
	public static void send(DatagramPacket packet){
		try {
			datagramSocket.send( packet );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
