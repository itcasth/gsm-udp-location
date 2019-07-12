package com.doit.net.gsm.udp.constants;

/**
 * Created by wly on 2019/7/11.
 */
public class GsmConstants {
	public static final String HEATBEAT = "101";//101 HeatBeat
	public static final String GET_CELL_PARA_RSP = "104";//104 GetCellParaRsp
	public static final String CMD_ACK = "109";//109 CmdAck
	public static final String VERSION_RSP = "110";//110 VersionRsp

	public static final String ONE_UEINFO_INDI = "103";//103 OneUeInfoIndi
	public static final String STATUS_RPT_INDI = "116";//116 StatusRptIndi

	public static final String SEND_SET_PARAM = "SetRfPara ";
	public static final String SEND_GET_PARAM = "GetRfPara";
	public static final String SEND_HEATBEAT = "HeatBeat";
	public static final String START_CELL = "StartCell";
	public static final String STOP_CELL = "StopCell";
	/**
	 * 名单字符串 15 个字符对应一个 IMSI 最大支持 100 个 IMSI
	 */
	public static final String SET_BLACK_LIST = "SetBlackList ";
	/**
	 0 ：上号模式
	 1：定位黑名单模式 首选
	 2: 定位黑名单模式 2 对于不连续上报手机 备选 仅用于 4G 模式
	 3：管控黑名单模式
	 4：管控所有模式
	 5：定位指定手机模式 仅用于 4G 模式
	 */
	public static final String WORK_TYPE = "SetWorkType ";


	public static final String REBOOT = "Reboot 0";
	public static final String SetTacUpdate = "SetTacUpdate ";//SetTacUpdate +分钟数
	public static final String StartReportMeasure = "StartReportMeasure ";
	public static final String StopReportMeasure = "StopReportMeasure ";
}
