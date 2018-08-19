package com.a3.javase.chapter9.thread;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Component;

@Component
public class ThreadDemo9_4_Tickets implements Runnable {
	private Integer tickets = 20;
	private Integer i = 1;
	public static Logger logger = Logger.getLogger(ThreadDemo9_4_Tickets.class);

	
	
	public ThreadDemo9_4_Tickets() {
		PropertyConfigurator.configure("log4j.properties");//如果不加这一句，就会报下面的错
		
		//log4j:WARN No appenders could be found for logger (org.springframework.test.context.BootstrapUtils).
		//log4j:WARN Please initialize the log4j system properly.
		
	}


	public void run() {
		
		while(tickets>0){
			
			logger.info(Thread.currentThread().getName()+" get the ticket"+i);
			tickets--;
			i++;
			if(i%3==0){
				logger.info("===sleep 3 seconds");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		

	}
	
	
	public static void main(String [] args){
		ThreadDemo9_4_Tickets thread = new ThreadDemo9_4_Tickets();
		
		new Thread(thread).start();
		new Thread(thread).start();
	}
	

}
