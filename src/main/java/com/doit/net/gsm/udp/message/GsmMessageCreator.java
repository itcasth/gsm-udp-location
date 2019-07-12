package com.doit.net.gsm.udp.message;

import com.doit.net.gsm.udp.base.GsmMessage;
import com.doit.net.gsm.udp.constants.GsmConstants;
import com.doit.net.gsm.udp.server.GsmSenderThread;

/**
 * Created by wly on 2019/7/12.
 * GSM消息处理器
 */
public class GsmMessageCreator {
	/**
	 * 开启射频
	 * @param ip
	 * @param port
	 */
	public static void openRf(String ip,int port){
		GsmMessage message = new GsmMessage();
		message.setSocketAddress(ip,port);
		message.setContent( GsmConstants.START_CELL );
		GsmSenderThread.put(message);
	}

	/**
	 * 关闭射频
	 * @param ip
	 * @param port
	 */
	public static void closeRf(String ip,int port){
		GsmMessage message = new GsmMessage();
		message.setSocketAddress(ip,port);
		message.setContent( GsmConstants.STOP_CELL );
		GsmSenderThread.put(message);
	}


	/**
	 * 心跳
	 * @param ip
	 * @param port
	 */
	public static void heartBeat(String ip,int port){
		GsmMessage message = new GsmMessage();
		message.setSocketAddress(ip,port);
		message.setContent( GsmConstants.SEND_HEATBEAT );
		GsmSenderThread.put(message);
	}

	/**
	 * 工作模式
	 * @param ip
	 * @param port
	 */
	public static void setWorkType(String ip,int port,int mode){
		GsmMessage message = new GsmMessage();
		message.setSocketAddress(ip,port);
		message.setContent( GsmConstants.WORK_TYPE+mode );
		GsmSenderThread.put(message);
	}


	/**
	 * 开始定位
	 * @param ip
	 * @param port
	 */
	public static void startLocate(String ip,int port,String imsi){
		setWorkType( ip,port,1 );
		setBlackList( ip,port,imsi );
	}

	/**
	 * 停止定位
	 * @param ip
	 * @param port
	 */
	public static void stopLocate(String ip,int port){
		setWorkType( ip,port,0 );
	}

	/**
	 * 设置黑名单
	 * @param ip
	 * @param port
	 */
	public static void setBlackList(String ip,int port,String imsi){
		GsmMessage message = new GsmMessage();
		message.setSocketAddress(ip,port);
		message.setContent( GsmConstants.SET_BLACK_LIST+imsi );
		GsmSenderThread.put(message);
	}


	/**
	 * 获取参数
	 * @param ip
	 * @param port
	 */
	public static void getRfPara(String ip,int port){
		GsmMessage message = new GsmMessage();
		message.setSocketAddress(ip,port);
		message.setContent( GsmConstants.SEND_GET_PARAM );
		GsmSenderThread.put(message);
	}

	/**
	 * 设置参数
	 * @param ip
	 * @param port
	 */
	public static void setRfPara(String ip,int port,String mcc,String mnc,String arfcn,String bsic,String lac,String ci,String power ){
		GsmMessage message = new GsmMessage();
		message.setSocketAddress(ip,port);
		message.setContent( GsmConstants.SEND_SET_PARAM+mcc+" "+mnc+" "+ arfcn +" "+ bsic+" "+lac+" "+ci+" "+power+" 30 0" );
		GsmSenderThread.put(message);
	}

	/**
	 * 开始测量上报（用于4G）
	 * @param ip
	 * @param port
	 */
	public static void startReportMeasure(String ip,int port,String imsi){
		GsmMessage message = new GsmMessage();
		message.setSocketAddress(ip,port);
		message.setContent( GsmConstants.StartReportMeasure+imsi );
		GsmSenderThread.put(message);
	}

	/**
	 * 停止测量上报（用于4G）
	 * @param ip
	 * @param port
	 */
	public static void stopReportMeasure(String ip,int port,String imsi){
		GsmMessage message = new GsmMessage();
		message.setSocketAddress(ip,port);
		message.setContent( GsmConstants.StopReportMeasure+imsi );
		GsmSenderThread.put(message);
	}

	/**
	 * 重启设备
	 * @param ip
	 * @param port
	 */
	public static void stopReportMeasure(String ip,int port){
		GsmMessage message = new GsmMessage();
		message.setSocketAddress(ip,port);
		message.setContent( GsmConstants.REBOOT );
		GsmSenderThread.put(message);
	}

	/**
	 * 设置TAC周期
	 * @param ip
	 * @param port
	 */
	public static void setTacUpdate(String ip,int port,String minute){
		GsmMessage message = new GsmMessage();
		message.setSocketAddress(ip,port);
		message.setContent( GsmConstants.SetTacUpdate+minute );
		GsmSenderThread.put(message);
	}
}
