package com.doit.net.gsm.udp;

import com.doit.net.gsm.udp.job.GetParamJob;
import com.doit.net.gsm.udp.job.HeartBeatJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by wly on 2019/7/11.
 * 任务调度
 */
public class ApplicationScheduler {
	public static void main(String[] args) {
		try {
			sendHeartBeat();
			sendGetParam();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
 	private static StdSchedulerFactory factory = new StdSchedulerFactory();
	private static Scheduler scheduler = null;
	private static JobDetail HeartBeatjob = null;
	private static JobDetail GetParamjob = null;

	public static void sendHeartBeat() throws SchedulerException {
		HeartBeatjob = JobBuilder.newJob( HeartBeatJob.class).withIdentity( "HeartBeatJob" ).build();
		SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("heatBeatTrigger").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
		//创建schedule实例
		if(scheduler==null){
			scheduler = factory.getScheduler();
		}
		scheduler.start();
		scheduler.scheduleJob(HeartBeatjob,trigger);
	}

	public static void sendGetParam() throws SchedulerException {
		GetParamjob = JobBuilder.newJob( GetParamJob.class).withIdentity( "GetParamJob" ).build();
		SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("GetParamTrigger").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(6).repeatForever()).build();
		//创建schedule实例
		if(scheduler==null){
			scheduler = factory.getScheduler();
		}
		scheduler.start();
		scheduler.scheduleJob(GetParamjob,trigger);

	}

	public static void stopScheduler(){
		System.out.println("停止调度");
		try {
			if(scheduler!=null){
				scheduler.shutdown(true);
			}
		}catch (Exception e	){
			e.printStackTrace();
		}
	}


}
