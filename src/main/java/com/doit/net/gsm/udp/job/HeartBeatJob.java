package com.doit.net.gsm.udp.job;

import com.doit.net.gsm.udp.base.GsmMessage;
import com.doit.net.gsm.udp.constants.GsmConstants;
import com.doit.net.gsm.udp.server.GsmReceiverThread;
import com.doit.net.gsm.udp.server.GsmSenderThread;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wly on 2019/7/11.
 * 心跳任务
 */
public class HeartBeatJob implements Job{

	private final static Logger log = LoggerFactory.getLogger(GsmReceiverThread.class);
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		//具体的业务逻辑
		GsmMessage gsmMessage = new GsmMessage(  );
		gsmMessage.setContent( GsmConstants.SEND_HEATBEAT );
		GsmSenderThread.put( gsmMessage );
		log.debug("发送HeartBeat");
	}
}
