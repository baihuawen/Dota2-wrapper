package com.github.kaisle.data;

import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;

import com.github.kaisle.util.Defines;
import com.github.kaisle.util.ParameterFactory;
import com.github.kaisle.util.Query;

public class Match {

	private List<Player> players;
	private boolean radiant_win;
	private int duration;
	private int start_time;
	private int match_id;
	private int match_seq_num;
	private int tower_status_radiant;
	private int tower_status_dire;
	private int barracks_status_radiant;
	private int barracks_status_dire;
	private int cluster;
	private int first_blood_time;
	private int lobby_type;
	private int human_players;
	private int leagueid;
	private int positive_votes;
	private int negative_votes;
	private int game_mode;
	private TournamentDetails tournamentDetails;

	public Match(List<Player> players, boolean radiant_win, int duration,
			int start_time, int match_id, int match_seq_num,
			int tower_status_radiant, int tower_status_dire,
			int barracks_status_radiant, int barracks_status_dire, int cluster,
			int first_blood_time, int lobby_type, int human_players,
			int leagueid, int positive_votes, int negative_votes,
			int game_mode, TournamentDetails tournamentDetails) {
		super();
		this.players = players;
		this.radiant_win = radiant_win;
		this.duration = duration;
		this.start_time = start_time;
		this.match_id = match_id;
		this.match_seq_num = match_seq_num;
		this.tower_status_radiant = tower_status_radiant;
		this.tower_status_dire = tower_status_dire;
		this.barracks_status_radiant = barracks_status_radiant;
		this.barracks_status_dire = barracks_status_dire;
		this.cluster = cluster;
		this.first_blood_time = first_blood_time;
		this.lobby_type = lobby_type;
		this.human_players = human_players;
		this.leagueid = leagueid;
		this.positive_votes = positive_votes;
		this.negative_votes = negative_votes;
		this.game_mode = game_mode;
		this.tournamentDetails = tournamentDetails;
		getPlayerInfo();
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean isRadiant_win() {
		return radiant_win;
	}

	public void setRadiant_win(boolean radiant_win) {
		this.radiant_win = radiant_win;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getStart_time() {
		return start_time;
	}

	public void setStart_time(int start_time) {
		this.start_time = start_time;
	}

	public int getMatch_id() {
		return match_id;
	}

	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}

	public int getMatch_seq_num() {
		return match_seq_num;
	}

	public void setMatch_seq_num(int match_seq_num) {
		this.match_seq_num = match_seq_num;
	}

	public int getTower_status_radiant() {
		return tower_status_radiant;
	}

	public void setTower_status_radiant(int tower_status_radiant) {
		this.tower_status_radiant = tower_status_radiant;
	}

	public int getTower_status_dire() {
		return tower_status_dire;
	}

	public void setTower_status_dire(int tower_status_dire) {
		this.tower_status_dire = tower_status_dire;
	}

	public int getBarracks_status_radiant() {
		return barracks_status_radiant;
	}

	public void setBarracks_status_radiant(int barracks_status_radiant) {
		this.barracks_status_radiant = barracks_status_radiant;
	}

	public int getBarracks_status_dire() {
		return barracks_status_dire;
	}

	public void setBarracks_status_dire(int barracks_status_dire) {
		this.barracks_status_dire = barracks_status_dire;
	}

	public int getCluster() {
		return cluster;
	}

	public void setCluster(int cluster) {
		this.cluster = cluster;
	}

	public int getFirst_blood_time() {
		return first_blood_time;
	}

	public void setFirst_blood_time(int first_blood_time) {
		this.first_blood_time = first_blood_time;
	}

	public int getLobby_type() {
		return lobby_type;
	}

	public void setLobby_type(int lobby_type) {
		this.lobby_type = lobby_type;
	}

	public int getHuman_players() {
		return human_players;
	}

	public void setHuman_players(int human_players) {
		this.human_players = human_players;
	}

	public int getLeagueid() {
		return leagueid;
	}

	public void setLeagueid(int leagueid) {
		this.leagueid = leagueid;
	}

	public int getPositive_votes() {
		return positive_votes;
	}

	public void setPositive_votes(int positive_votes) {
		this.positive_votes = positive_votes;
	}

	public int getNegative_votes() {
		return negative_votes;
	}

	public void setNegative_votes(int negative_votes) {
		this.negative_votes = negative_votes;
	}

	public int getGame_mode() {
		return game_mode;
	}

	public void setGame_mode(int game_mode) {
		this.game_mode = game_mode;
	}

	public TournamentDetails getTournamentDetails() {
		return tournamentDetails;
	}

	public void setTournamentDetails(TournamentDetails tournamentDetails) {
		this.tournamentDetails = tournamentDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + barracks_status_dire;
		result = prime * result + barracks_status_radiant;
		result = prime * result + cluster;
		result = prime * result + duration;
		result = prime * result + first_blood_time;
		result = prime * result + game_mode;
		result = prime * result + human_players;
		result = prime * result + leagueid;
		result = prime * result + lobby_type;
		result = prime * result + match_id;
		result = prime * result + match_seq_num;
		result = prime * result + negative_votes;
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		result = prime * result + positive_votes;
		result = prime * result + (radiant_win ? 1231 : 1237);
		result = prime * result + start_time;
		result = prime
				* result
				+ ((tournamentDetails == null) ? 0 : tournamentDetails
						.hashCode());
		result = prime * result + tower_status_dire;
		result = prime * result + tower_status_radiant;
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
		Match other = (Match) obj;
		if (barracks_status_dire != other.barracks_status_dire)
			return false;
		if (barracks_status_radiant != other.barracks_status_radiant)
			return false;
		if (cluster != other.cluster)
			return false;
		if (duration != other.duration)
			return false;
		if (first_blood_time != other.first_blood_time)
			return false;
		if (game_mode != other.game_mode)
			return false;
		if (human_players != other.human_players)
			return false;
		if (leagueid != other.leagueid)
			return false;
		if (lobby_type != other.lobby_type)
			return false;
		if (match_id != other.match_id)
			return false;
		if (match_seq_num != other.match_seq_num)
			return false;
		if (negative_votes != other.negative_votes)
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		if (positive_votes != other.positive_votes)
			return false;
		if (radiant_win != other.radiant_win)
			return false;
		if (start_time != other.start_time)
			return false;
		if (tournamentDetails == null) {
			if (other.tournamentDetails != null)
				return false;
		} else if (!tournamentDetails.equals(other.tournamentDetails))
			return false;
		if (tower_status_dire != other.tower_status_dire)
			return false;
		if (tower_status_radiant != other.tower_status_radiant)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Match [players=" + players + ", radiant_win=" + radiant_win
				+ ", duration=" + duration + ", start_time=" + start_time
				+ ", match_id=" + match_id + ", match_seq_num=" + match_seq_num
				+ ", tower_status_radiant=" + tower_status_radiant
				+ ", tower_status_dire=" + tower_status_dire
				+ ", barracks_status_radiant=" + barracks_status_radiant
				+ ", barracks_status_dire=" + barracks_status_dire
				+ ", cluster=" + cluster + ", first_blood_time="
				+ first_blood_time + ", lobby_type=" + lobby_type
				+ ", human_players=" + human_players + ", leagueid=" + leagueid
				+ ", positive_votes=" + positive_votes + ", negative_votes="
				+ negative_votes + ", game_mode=" + game_mode
				+ ", tournamentDetails=" + tournamentDetails + "]";
	}

	public void getPlayerInfo() {
		HashMap<String, String> params = ParameterFactory.getParametersWithXML();
		String queryString = new String();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if (new Long(player.getAccount_id()).equals(new Long("4294967295"))) {
				player.setPlayerDetails(new PlayerDetails(0, 0,
						"Stats not enabled", "Stats not enabled",
						"Stats not enabled", "Stats not enabled",
						"Stats not enabled", "Stats not enabled", 0));

			} else {
				queryString = queryString
						+ new Long(player.getAccount_id()
								+ +new Long("76561197960265728")).toString()
						+ ",";
			}
		}
		params.put("steamids", queryString);
		Document details = new Query().queryAPI(Defines.playerSummaryURL,
				params);
		StopWatch playerInfoBuild = new LoggingStopWatch("Player Info Build");  
		for (Element player : details.getRootElement().getChild("players")
				.getChildren("player")) {
			Long playerSteamID = new Long(player.getChild("steamid").getText());
			long player32bitSteamID = playerSteamID
					- new Long("76561197960265728");
			for (Player regularPlayer : players) {
				if (regularPlayer.getAccount_id() == player32bitSteamID) {
					Integer playerVisibility = new Integer(player.getChild(
							"communityvisibilitystate").getText());
					String playerPersona = player.getChild("personaname")
							.getText();
					String playerLogOff = player.getChild("lastlogoff")
							.getText();
					String playerProfile = player.getChild("profileurl")
							.getText();
					String playerAvatar = player.getChild("avatar").getText();
					Integer playerPersonaState = new Integer(player.getChild(
							"personastate").getText());
					regularPlayer.setPlayerDetails(new PlayerDetails(
							playerSteamID, playerVisibility, playerPersona,
							playerLogOff, playerProfile, playerAvatar, null,
							null, playerPersonaState));
				}
			}
		}
		playerInfoBuild.stop(); 
	}

}
