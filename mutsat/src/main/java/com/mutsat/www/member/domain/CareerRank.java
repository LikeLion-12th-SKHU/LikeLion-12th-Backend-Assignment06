package com.mutsat.www.member.domain;

public enum CareerRank {
	PRIVATE("하사"),
	CORPORAL("중사"),
	SERGEANT("상사"),
	LANCE_CORPORAL("소위"),
	SERGEANT_FIRST_CLASS("중위"),
	WARRANT_OFFICER("대위");

	private final String rankName;

	CareerRank(String rankName){
		this.rankName = rankName;
	}

	public String getRankName() { //상수이름에 의존할 수 있으므로 변경이 되더라도 가져올 수 있게
		return rankName;
	}

}
