package com.doit.net.gsm.udp;

import com.doit.net.gsm.udp.job.GetParamJob;
import com.doit.net.gsm.udp.job.HeartBeatJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.impl.SimpleLogger;

/**
 * Created by wly on 2019/7/11.
 * 任务调度
 */
public class ApplicationScheduler {
	public static void main(String[] args) {
		try {
			System.setProperty( SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "trace");
			System.setProperty(SimpleLogger.SHOW_SHORT_LOG_NAME_KEY, "true");
			System.setProperty(SimpleLogger.LOG_FILE_KEY, "System.out");
			sendHeartBeat();
			sendGetParam();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void sendHeartBeat() throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob( HeartBeatJob.class).withIdentity( "HeartBeatJob" ).build();
		SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("heatBeatTrigger").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
		//创建schedule实例
		StdSchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobDetail,trigger);
	}

	public static void sendGetParam() throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob( GetParamJob.class).withIdentity( "GetParamJob" ).build();
		SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("GetParamTrigger").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(6).repeatForever()).build();
		//创建schedule实例
		StdSchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobDetail,trigger);
	}
}
