package com.lpras.schedulingtasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.testUtil.RandomUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@EnableAsync
@Slf4j
public class ScheduledTasks {
    
	@Autowired
	@Qualifier("rockDbRepo")
	private RocksDBRepository rockDbCache;

	@Autowired
	@Qualifier("rockDbRepo")
	private RocksDBRepository rockDbInprogCache;
	
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
	public List<Integer> randomIds;
	public List<String> randomNames;
	public List<String> randomEmails;
	
	@PostConstruct
	public void init() {
		randomIds = new ArrayList<>();
		randomNames = new ArrayList<>();
		randomEmails = new ArrayList<>();
	}
	
	@Async
	@Scheduled(cron="*/5 * * * * ?")
	public void doSomethingCron5() {
		log.info("---------------------5-----------------");
		for(String randomName:randomNames) {
			if(rockDbInprogCache.find(randomName).isEmpty()) {				
				rockDbCache.delete(randomName);
			}
		}
		log.info("Method doSomethingCron5 executed at every 5 seconds. Current time is :: {}", dateFormat.format(new Date()));
	}
	
	@Async
	@Scheduled(cron="*/10 * * * * ?")
	public void doSomethingCron10() {
		log.info("---------------------10-----------------");
		int randomId = RandomUtil.getPositiveInt();
		randomIds.add(randomId);
		String randomName = RandomStringUtils.randomAlphabetic(12);
		randomNames.add(randomName);
		String randomEmail = randomId + "@gmail.com";
		randomEmails.add(randomEmail);
		
		Customer customer = new Customer(randomId, randomName, randomEmail);
		
		rockDbCache.save(randomName, customer);
		rockDbInprogCache.save(randomName, customer);
		
		log.info("Method doSomethingCron10 executed at every 10 seconds. Current time is :: {}", dateFormat.format(new Date()));
	}
	
	//@Async
	//@Scheduled(cron="*/15 * * * * ?")
	public void doSomethingCron15() {
		log.info("Method executed at every 15 seconds. Current time is :: {}", dateFormat.format(new Date()));
	}
	
	//@Async
	//@Scheduled(cron="*/20 * * * * ?")
	public void doSomethingCron20() {
		log.info("Method executed at every 20 seconds. Current time is :: {}", dateFormat.format(new Date()));
	}
	
	@Async
	@Scheduled(cron="*/25 * * * * ?")
	public void doSomethingCron25() {
		log.info("---------------------25-----------------");
		for(String randomName:randomNames) {
			rockDbInprogCache.delete(randomName);
		}
		log.info("Method doSomethingCron25 executed at every 25 seconds. Current time is :: {}", dateFormat.format(new Date()));
	}
}