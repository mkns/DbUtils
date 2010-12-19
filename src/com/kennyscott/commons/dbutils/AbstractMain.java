package com.kennyscott.commons.dbutils;

public abstract class AbstractMain {
	
	protected abstract void execute(String[] args);

	/**
	 * I get tired of typing System.out.println()
	 * 
	 * @param text
	 */
	static protected void log(Object text) {
		System.out.println(text);
	}

}
