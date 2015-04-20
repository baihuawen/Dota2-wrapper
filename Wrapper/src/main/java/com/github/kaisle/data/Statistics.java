package com.github.kaisle.data;

public class Statistics {

	private int gold;
	private int last_hits;
	private int denies;
	private int gold_per_min;
	private int xp_per_min;
	private int gold_spent;
	private int hero_damage;
	private int tower_damage;
	private int hero_healing;
	private int level;

	public Statistics(int gold, int last_hits, int denies, int gold_per_min,
			int xp_per_min, int gold_spent, int hero_damage, int tower_damage,
			int hero_healing, int level) {
		super();
		this.gold = gold;
		this.last_hits = last_hits;
		this.denies = denies;
		this.gold_per_min = gold_per_min;
		this.xp_per_min = xp_per_min;
		this.gold_spent = gold_spent;
		this.hero_damage = hero_damage;
		this.tower_damage = tower_damage;
		this.hero_healing = hero_healing;
		this.level = level;
	}

	@Override
	public String toString() {
		return "Statistics [gold=" + gold + ", last_hits=" + last_hits
				+ ", denies=" + denies + ", gold_per_min=" + gold_per_min
				+ ", xp_per_min=" + xp_per_min + ", gold_spent=" + gold_spent
				+ ", hero_damage=" + hero_damage + ", tower_damage="
				+ tower_damage + ", hero_healing=" + hero_healing + ", level="
				+ level + "]";
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getLast_hits() {
		return last_hits;
	}

	public void setLast_hits(int last_hits) {
		this.last_hits = last_hits;
	}

	public int getDenies() {
		return denies;
	}

	public void setDenies(int denies) {
		this.denies = denies;
	}

	public int getGold_per_min() {
		return gold_per_min;
	}

	public void setGold_per_min(int gold_per_min) {
		this.gold_per_min = gold_per_min;
	}

	public int getXp_per_min() {
		return xp_per_min;
	}

	public void setXp_per_min(int xp_per_min) {
		this.xp_per_min = xp_per_min;
	}

	public int getGold_spent() {
		return gold_spent;
	}

	public void setGold_spent(int gold_spent) {
		this.gold_spent = gold_spent;
	}

	public int getHero_damage() {
		return hero_damage;
	}

	public void setHero_damage(int hero_damage) {
		this.hero_damage = hero_damage;
	}

	public int getTower_damage() {
		return tower_damage;
	}

	public void setTower_damage(int tower_damage) {
		this.tower_damage = tower_damage;
	}

	public int getHero_healing() {
		return hero_healing;
	}

	public void setHero_healing(int hero_healing) {
		this.hero_healing = hero_healing;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + denies;
		result = prime * result + gold;
		result = prime * result + gold_per_min;
		result = prime * result + gold_spent;
		result = prime * result + hero_damage;
		result = prime * result + hero_healing;
		result = prime * result + last_hits;
		result = prime * result + level;
		result = prime * result + tower_damage;
		result = prime * result + xp_per_min;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Statistics other = (Statistics) obj;
		if (denies != other.denies)
			return false;
		if (gold != other.gold)
			return false;
		if (gold_per_min != other.gold_per_min)
			return false;
		if (gold_spent != other.gold_spent)
			return false;
		if (hero_damage != other.hero_damage)
			return false;
		if (hero_healing != other.hero_healing)
			return false;
		if (last_hits != other.last_hits)
			return false;
		if (level != other.level)
			return false;
		if (tower_damage != other.tower_damage)
			return false;
		if (xp_per_min != other.xp_per_min)
			return false;
		return true;
	}
	
	

}
