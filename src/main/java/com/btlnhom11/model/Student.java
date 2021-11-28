package com.btlnhom11.model;

public class Student {
	
	private Integer id;
	private String name;
	private Float cpa;
	
	public Student() {}
	
	public Student(Integer id, String name, Float cpa) {
		this.id = id;
		this.name = name;
		this.cpa = cpa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getCpa() {
		return cpa;
	}

	public void setCpa(Float cpa) {
		this.cpa = cpa;
	}
	
}
