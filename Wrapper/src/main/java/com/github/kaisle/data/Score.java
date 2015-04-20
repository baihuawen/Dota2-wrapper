package com.github.kaisle.data;

public class Score {

	private int kills;
	private int deaths;
	private int assists;

	public Score(int kills, int deaths, int assists) {
		super();
		this.kills = kills;
		this.deaths = deaths;
		this.assists = assists;
	}

	@Override
	public String toString() {
		return "Score [kills=" + kills + ", deaths=" + deaths + ", assists="
				+ assists + "]";   
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + assists;
		result = prime * result + deaths;
		result = prime * result + kills;
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
		Score other = (Score) obj;
		if (assists != other.assists)
			return false;
		if (deaths != other.deaths)
			return false;
		if (kills != other.kills)
			return false;
		return true;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}
	
	

}
