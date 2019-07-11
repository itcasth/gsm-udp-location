package com.doit.net.gsm.udp.base;

import com.doit.net.gsm.udp.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wly on 2019/7/11.
 * gsm消息
 */
public class GsmMessage extends BaseHeader{
	private final static Logger log = LoggerFactory.getLogger(GsmMessage.class);
	public String messageHead;//消息头
	public String content;//消息内容

	public GsmMessage() {
		this.messageHead = messageHead;
	}
	public GsmMessage(String messageHead) {
		this.messageHead = messageHead;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		if(StringUtils.isNotBlank( content ) && content.contains( " " )){
			String[] arr = content.split( " " );
			if(arr!=null && arr.length>0){
				messageHead = arr[0];
			}
		}
	}

	public String getMessageHead() {
		return messageHead;
	}

	@Override
	public String toString() {
		return "GsmMessage{" +
				"messageHead='" + messageHead + '\'' +
				'}';
	}
}
