package com.github.kaisle.data;

public class Ability {
	private int abilityId;
	private int time;
	private int level;
	

	public Ability(int abilityId, int time, int level) {
		super();
		this.abilityId = abilityId;
		this.time = time;
		this.level = level;
	}

	@Override
	public String toString() {
		return "Ability [abilityId=" + abilityId + ", time=" + time
				+ ", level=" + level + "]";
	}

	public int getAbilityId() {
		return abilityId;
	}

	public void setAbilityId(int abilityId) {
		this.abilityId = abilityId;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
}
