package com.lpras.schedulingtasks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

/*	@Async
	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}
	
	@Async
	@Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
	public void doSomething() throws InterruptedException {
		// something that should run periodically
		log.info("doSomething fixedDelay 10 sec - The time is now {}", dateFormat.format(new Date()));
		TimeUnit.SECONDS.sleep(20);
	}
	*/
	@Async
	@Scheduled(cron="*/5 * * * * ?")
	public void doSomethingCron5() {
		log.info("Method executed at every 5 seconds. Current time is :: {}", dateFormat.format(new Date()));
	}
	
	@Async
	@Scheduled(cron="*/10 * * * * ?")
	public void doSomethingCron10() {
		log.info("Method executed at every 10 seconds. Current time is :: {}", dateFormat.format(new Date()));
	}
	
	@Async
	@Scheduled(cron="*/15 * * * * ?")
	public void doSomethingCron15() {
		log.info("Method executed at every 15 seconds. Current time is :: {}", dateFormat.format(new Date()));
	}
	
	@Async
	@Scheduled(cron="*/20 * * * * ?")
	public void doSomethingCron20() {
		log.info("Method executed at every 20 seconds. Current time is :: {}", dateFormat.format(new Date()));
	}
	
	@Async
	@Scheduled(cron="*/25 * * * * ?")
	public void doSomethingCron25() {
		log.info("Method executed at every 25 seconds. Current time is :: {}", dateFormat.format(new Date()));
	}
}