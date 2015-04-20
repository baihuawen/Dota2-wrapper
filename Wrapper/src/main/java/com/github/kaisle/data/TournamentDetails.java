package com.github.kaisle.data;

import java.util.List;

public class TournamentDetails {

	private int radiant_team_id;
	private String radiant_name;
	private long radiant_logo;
	private int radiant_team_complete;
	private int dire_team_id;
	private String dire_name;
	private long dire_logo;
	private int dire_team_complete;
	private List<Pick_Ban> picks_Bans;
	public TournamentDetails(int radiant_team_id, String radiant_name,
			long radiant_logo, int radiant_team_complete, int dire_team_id,
			String dire_name, long dire_logo, int dire_team_complete,
			List<Pick_Ban> picks_Bans) {
		super();
		this.radiant_team_id = radiant_team_id;
		this.radiant_name = radiant_name;
		this.radiant_logo = radiant_logo;
		this.radiant_team_complete = radiant_team_complete;
		this.dire_team_id = dire_team_id;
		this.dire_name = dire_name;
		this.dire_logo = dire_logo;
		this.dire_team_complete = dire_team_complete;
		this.picks_Bans = picks_Bans;
	}
	public int getRadiant_team_id() {
		return radiant_team_id;
	}
	public void setRadiant_team_id(int radiant_team_id) {
		this.radiant_team_id = radiant_team_id;
	}
	public String getRadiant_name() {
		return radiant_name;
	}
	public void setRadiant_name(String radiant_name) {
		this.radiant_name = radiant_name;
	}
	public long getRadiant_logo() {
		return radiant_logo;
	}
	public void setRadiant_logo(int radiant_logo) {
		this.radiant_logo = radiant_logo;
	}
	public int getRadiant_team_complete() {
		return radiant_team_complete;
	}
	public void setRadiant_team_complete(int radiant_team_complete) {
		this.radiant_team_complete = radiant_team_complete;
	}
	public int getDire_team_id() {
		return dire_team_id;
	}
	public void setDire_team_id(int dire_team_id) {
		this.dire_team_id = dire_team_id;
	}
	public String getDire_name() {
		return dire_name;
	}
	public void setDire_name(String dire_name) {
		this.dire_name = dire_name;
	}
	public long getDire_logo() {
		return dire_logo;
	}
	public void setDire_logo(int dire_logo) {
		this.dire_logo = dire_logo;
	}
	public int getDire_team_complete() {
		return dire_team_complete;
	}
	public void setDire_team_complete(int dire_team_complete) {
		this.dire_team_complete = dire_team_complete;
	}
	public List<Pick_Ban> getPicks_Bans() {
		return picks_Bans;
	}
	public void setPicks_Bans(List<Pick_Ban> picks_Bans) {
		this.picks_Bans = picks_Bans;
	}
	@Override
	public String toString() {
		return "TournamentDetails [radiant_team_id=" + radiant_team_id
				+ ", radiant_name=" + radiant_name + ", radiant_logo="
				+ radiant_logo + ", radiant_team_complete="
				+ radiant_team_complete + ", dire_team_id=" + dire_team_id
				+ ", dire_name=" + dire_name + ", dire_logo=" + dire_logo
				+ ", dire_team_complete=" + dire_team_complete
				+ ", picks_Bans=" + picks_Bans + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (dire_logo ^ (dire_logo >>> 32));
		result = prime * result
				+ ((dire_name == null) ? 0 : dire_name.hashCode());
		result = prime * result + dire_team_complete;
		result = prime * result + dire_team_id;
		result = prime * result
				+ ((picks_Bans == null) ? 0 : picks_Bans.hashCode());
		result = prime * result + (int) (radiant_logo ^ (radiant_logo >>> 32));
		result = prime * result
				+ ((radiant_name == null) ? 0 : radiant_name.hashCode());
		result = prime * result + radiant_team_complete;
		result = prime * result + radiant_team_id;
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
		TournamentDetails other = (TournamentDetails) obj;
		if (dire_logo != other.dire_logo)
			return false;
		if (dire_name == null) {
			if (other.dire_name != null)
				return false;
		} else if (!dire_name.equals(other.dire_name))
			return false;
		if (dire_team_complete != other.dire_team_complete)
			return false;
		if (dire_team_id != other.dire_team_id)
			return false;
		if (picks_Bans == null) {
			if (other.picks_Bans != null)
				return false;
		} else if (!picks_Bans.equals(other.picks_Bans))
			return false;
		if (radiant_logo != other.radiant_logo)
			return false;
		if (radiant_name == null) {
			if (other.radiant_name != null)
				return false;
		} else if (!radiant_name.equals(other.radiant_name))
			return false;
		if (radiant_team_complete != other.radiant_team_complete)
			return false;
		if (radiant_team_id != other.radiant_team_id)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}
