package com.github.kaisle.data;

public class Pick_Ban {
	private boolean is_pick;
	private String hero_id;
	private int team;
	private int order;
	public boolean isIs_pick() {
		return is_pick;
	}
	public void setIs_pick(boolean is_pick) {
		this.is_pick = is_pick;
	}
	public String getHero_id() {
		return hero_id;
	}
	public void setHero_id(String hero_id) {
		this.hero_id = hero_id;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public Pick_Ban(boolean is_pick, String hero_id, int team, int order) {
		super();
		this.is_pick = is_pick;
		this.hero_id = hero_id;
		this.team = team;
		this.order = order;
	}
	@Override
	public String toString() {
		return "Pick_Ban [is_pick=" + is_pick + ", hero_id=" + hero_id
				+ ", team=" + team + ", order=" + order + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hero_id == null) ? 0 : hero_id.hashCode());
		result = prime * result + (is_pick ? 1231 : 1237);
		result = prime * result + order;
		result = prime * result + team;
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
		Pick_Ban other = (Pick_Ban) obj;
		if (hero_id == null) {
			if (other.hero_id != null)
				return false;
		} else if (!hero_id.equals(other.hero_id))
			return false;
		if (is_pick != other.is_pick)
			return false;
		if (order != other.order)
			return false;
		if (team != other.team)
			return false;
		return true;
	}
	
	
	
	
	
}
