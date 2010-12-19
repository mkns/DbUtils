package com.kennyscott.commons.dbutils;

public abstract class AbstractMain {
	
	protected abstract void execute(String[] args);

	/**
	 * I get tired of typing System.out.println()
	 * 
	 * @param text
	 */
	protected void log(Object text) {
		System.out.println(text);
	}

	protected void sleep() {
		sleep(5000);
	}
	
	protected void sleep(long length) {
		try {
			Thread.sleep(length);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
