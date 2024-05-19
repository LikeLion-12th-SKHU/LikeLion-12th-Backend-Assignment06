package com.mutsat.www.member.domain;

import lombok.Getter;

@Getter
public enum Skills {


	PYTHON("Python"),
	JAVA("Java"),
	JAVASCRIPT("Java script"),
	NODE("Node.js"),
	DJANGO("Django"),
	SPRING("Spring");


	private final String skill;
	Skills(String skill) {
		this.skill = skill;
	}
}
