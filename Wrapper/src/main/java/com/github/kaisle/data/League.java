package com.github.kaisle.data;

public class League {
	
	private String name; 
	private int leagueid;
	private String description;
	private String tournament_url;
	private int itemdef;
	public League(String name, int leagueid, String description,
			String tournament_url, int itemdef) {
		super();
		this.name = name;
		this.leagueid = leagueid;
		this.description = description;
		this.tournament_url = tournament_url;
		this.itemdef = itemdef;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLeagueid() {
		return leagueid;
	}
	public void setLeagueid(int leagueid) {
		this.leagueid = leagueid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTournament_url() {
		return tournament_url;
	}
	public void setTournament_url(String tournament_url) {
		this.tournament_url = tournament_url;
	}
	public int getItemdef() {
		return itemdef;
	}
	public void setItemdef(int itemdef) {
		this.itemdef = itemdef;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + itemdef;
		result = prime * result + leagueid;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((tournament_url == null) ? 0 : tournament_url.hashCode());
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
		League other = (League) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (itemdef != other.itemdef)
			return false;
		if (leagueid != other.leagueid)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tournament_url == null) {
			if (other.tournament_url != null)
				return false;
		} else if (!tournament_url.equals(other.tournament_url))
			return false;
		return true;
	} 
	
	
	
	

}
