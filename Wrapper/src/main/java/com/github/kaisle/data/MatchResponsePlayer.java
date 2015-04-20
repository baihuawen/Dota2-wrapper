package com.github.kaisle.data;

public class MatchResponsePlayer {
	
	private String account_id; 
	private String player_slot; 
	private String hero_id;
	public MatchResponsePlayer(String account_id, String player_slot,
			String hero_id) {
		super();
		this.account_id = account_id;
		this.player_slot = player_slot;
		this.hero_id = hero_id;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getPlayer_slot() {
		return player_slot;
	}
	public void setPlayer_slot(String player_slot) {
		this.player_slot = player_slot;
	}
	public String getHero_id() {
		return hero_id;
	}
	public void setHero_id(String hero_id) {
		this.hero_id = hero_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((account_id == null) ? 0 : account_id.hashCode());
		result = prime * result + ((hero_id == null) ? 0 : hero_id.hashCode());
		result = prime * result
				+ ((player_slot == null) ? 0 : player_slot.hashCode());
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
		MatchResponsePlayer other = (MatchResponsePlayer) obj;
		if (account_id == null) {
			if (other.account_id != null)
				return false;
		} else if (!account_id.equals(other.account_id))
			return false;
		if (hero_id == null) {
			if (other.hero_id != null)
				return false;
		} else if (!hero_id.equals(other.hero_id))
			return false;
		if (player_slot == null) {
			if (other.player_slot != null)
				return false;
		} else if (!player_slot.equals(other.player_slot))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MatchResponsePlayer [account_id=" + account_id
				+ ", player_slot=" + player_slot + ", hero_id=" + hero_id + "]";
	} 
	
	

}
