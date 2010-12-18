package com.kennyscott.commons.dbutils;

import java.io.Serializable;

/**
 * A simple JavaBean which is used to show the BeanListHandler functionality. Go
 * look in Simple.java, you'll see how it's used in there.
 * 
 * @author mkns
 * 
 */
public class PersonBean implements Serializable {

	private static final long serialVersionUID = -3633974524847808013L;
	private int age;
	private String name;

	/**
	 * Default constructor
	 */
	public PersonBean() {

	}

	/**
	 * Sets the age
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets the age
	 * @return
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name
	 * @return
	 */
	public String getName() {
		return name;
	}

}
