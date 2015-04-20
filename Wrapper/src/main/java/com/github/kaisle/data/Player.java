package com.github.kaisle.data;

import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import com.github.kaisle.util.Defines;
import com.github.kaisle.util.ParameterFactory;
import com.github.kaisle.util.Query;

public class Player {

	private long account_id;
	private int player_slot;
	private String hero_id;
	private Inventory inventory;
	private Score score;
	private int leaver_status;
	private Statistics statistics;
	private List<Ability> abilities;
	private AdditionalUnit additionalUnit;
	private PlayerDetails playerDetails;

	public Player(long account_id, int player_slot, String hero_id,
			Inventory inventory, Score score, int leaver_status,
			Statistics statistics, List<Ability> abilities,
			AdditionalUnit additionalUnit) {
		super();
		this.account_id = account_id;
		this.player_slot = player_slot;
		this.hero_id = hero_id;
		this.inventory = inventory;
		this.score = score;
		this.leaver_status = leaver_status;
		this.statistics = statistics;
		this.abilities = abilities;
		this.additionalUnit = additionalUnit;
		//setDetails();
	}

	@Override
	public String toString() {
		return "Player [account_id=" + account_id + ", player_slot="
				+ player_slot + ", hero_id=" + hero_id + ", inventory="
				+ inventory + ", score=" + score + ", leaver_status="
				+ leaver_status + ", statistics=" + statistics + ", abilities="
				+ abilities + ", additionalUnit=" + additionalUnit + "]";
	}

	public long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}

	public int getPlayer_slot() {
		return player_slot;
	}

	public void setPlayer_slot(int player_slot) {
		this.player_slot = player_slot;
	}

	public String getHero_id() {
		return hero_id;
	}

	public void setHero_id(String hero_id) {
		this.hero_id = hero_id;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public int getLeaver_status() {
		return leaver_status;
	}

	public void setLeaver_status(int leaver_status) {
		this.leaver_status = leaver_status;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public List<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
	}

	public AdditionalUnit getAdditionalUnit() {
		return additionalUnit;
	}

	public void setAdditionalUnit(AdditionalUnit additionalUnit) {
		this.additionalUnit = additionalUnit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((abilities == null) ? 0 : abilities.hashCode());
		result = prime * result + (int) (account_id ^ (account_id >>> 32));
		result = prime * result
				+ ((additionalUnit == null) ? 0 : additionalUnit.hashCode());
		result = prime * result + ((hero_id == null) ? 0 : hero_id.hashCode());
		result = prime * result
				+ ((inventory == null) ? 0 : inventory.hashCode());
		result = prime * result + leaver_status;
		result = prime * result + player_slot;
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result
				+ ((statistics == null) ? 0 : statistics.hashCode());
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
		Player other = (Player) obj;
		if (abilities == null) {
			if (other.abilities != null)
				return false;
		} else if (!abilities.equals(other.abilities))
			return false;
		if (account_id != other.account_id)
			return false;
		if (additionalUnit == null) {
			if (other.additionalUnit != null)
				return false;
		} else if (!additionalUnit.equals(other.additionalUnit))
			return false;
		if (hero_id == null) {
			if (other.hero_id != null)
				return false;
		} else if (!hero_id.equals(other.hero_id))
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		if (leaver_status != other.leaver_status)
			return false;
		if (player_slot != other.player_slot)
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (statistics == null) {
			if (other.statistics != null)
				return false;
		} else if (!statistics.equals(other.statistics))
			return false;
		return true;
	}

	public void setDetails() {
		if (new Long(account_id).equals(new Long("4294967295"))) {
			playerDetails = new PlayerDetails(0, 0, "Stats not enabled",
					"Stats not enabled", "Stats not enabled",
					"Stats not enabled", "Stats not enabled",
					"Stats not enabled", 0);
		} else {
			HashMap<String, String> params = ParameterFactory.getParametersWithXML(); 
			Long SixtyFourBitSteamId = account_id
					+ new Long("76561197960265728");
			params.put("steamids", SixtyFourBitSteamId.toString());
			Document details = new Query().queryAPI(Defines.playerSummaryURL,
					params);
			Element player = details.getRootElement().getChild("players")
					.getChild("player");
			Long playerSteamID = new Long(player.getChild("steamid").getText());
			Integer playerVisibility = new Integer(player.getChild(
					"communityvisibilitystate").getText());
			String playerPersona = player.getChild("personaname").getText();
			String playerLogOff = player.getChild("lastlogoff").getText();
			String playerProfile = player.getChild("profileurl").getText();
			String playerAvatar = player.getChild("avatar").getText();
			//String playerAvatarMedium = player.getChild("avatarmedium")
			//		.getText();
			//String playerAvatarLarge = player.getChild("avatarlarge").getText();
			Integer playerPersonaState = new Integer(player.getChild(
					"personastate").getText());
			playerDetails = new PlayerDetails(playerSteamID, playerVisibility,
					playerPersona, playerLogOff, playerProfile, playerAvatar,
					null, null, playerPersonaState);
		}
	}

	public PlayerDetails getPlayerDetails() {
		return playerDetails;
	}

	public void setPlayerDetails(PlayerDetails playerDetails) {
		this.playerDetails = playerDetails;
	}

}
