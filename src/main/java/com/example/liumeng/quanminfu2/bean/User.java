package com.example.liumeng.quanminfu2.bean;

public class User {
	public String name;
	public int age;

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}

	public User() {
	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
