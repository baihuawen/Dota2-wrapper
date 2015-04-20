package com.github.kaisle.data;

import java.util.List;

public class MatchResponseDetails {

	private String match_id; 
	private String match_seq_num; 
	private String start_time; 
	private String lobby_type;  
	private List<MatchResponsePlayer> players;
	public MatchResponseDetails(String match_id, String match_seq_num,
			String start_time, String lobby_type,
			List<MatchResponsePlayer> players) {
		super();
		this.match_id = match_id;
		this.match_seq_num = match_seq_num;
		this.start_time = start_time;
		this.lobby_type = lobby_type;
		this.players = players;
	}
	public String getMatch_id() {
		return match_id;
	}
	public void setMatch_id(String match_id) {
		this.match_id = match_id;
	}
	public String getMatch_seq_num() {
		return match_seq_num;
	}
	public void setMatch_seq_num(String match_seq_num) {
		this.match_seq_num = match_seq_num;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getLobby_type() {
		return lobby_type;
	}
	public void setLobby_type(String lobby_type) {
		this.lobby_type = lobby_type;
	}
	public List<MatchResponsePlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<MatchResponsePlayer> players) {
		this.players = players;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lobby_type == null) ? 0 : lobby_type.hashCode());
		result = prime * result
				+ ((match_id == null) ? 0 : match_id.hashCode());
		result = prime * result
				+ ((match_seq_num == null) ? 0 : match_seq_num.hashCode());
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		result = prime * result
				+ ((start_time == null) ? 0 : start_time.hashCode());
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
		MatchResponseDetails other = (MatchResponseDetails) obj;
		if (lobby_type == null) {
			if (other.lobby_type != null)
				return false;
		} else if (!lobby_type.equals(other.lobby_type))
			return false;
		if (match_id == null) {
			if (other.match_id != null)
				return false;
		} else if (!match_id.equals(other.match_id))
			return false;
		if (match_seq_num == null) {
			if (other.match_seq_num != null)
				return false;
		} else if (!match_seq_num.equals(other.match_seq_num))
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		if (start_time == null) {
			if (other.start_time != null)
				return false;
		} else if (!start_time.equals(other.start_time))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MatchResponseDetails [match_id=" + match_id
				+ ", match_seq_num=" + match_seq_num + ", start_time="
				+ start_time + ", lobby_type=" + lobby_type + ", players="
				+ players + "]";
	} 
	
	
	
}
