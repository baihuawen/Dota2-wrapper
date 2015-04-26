package com.github.kaisle.util;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import com.github.kaisle.data.Ability;
import com.github.kaisle.data.AdditionalUnit;
import com.github.kaisle.data.Inventory;
import com.github.kaisle.data.Match;
import com.github.kaisle.data.MatchResponseDetails;
import com.github.kaisle.data.MatchResponsePlayer;
import com.github.kaisle.data.Pick_Ban;
import com.github.kaisle.data.Player;
import com.github.kaisle.data.Score;
import com.github.kaisle.data.Statistics;
import com.github.kaisle.data.TournamentDetails;
/**
 * This class is responsible for deserializing XML through the use of the JDOM-library. 
 * @author Anders Borum
 *
 */
public class MatchComponentFactory {


	/**
	 * Create a new AdditionalUnit object.
	 * @param player - The player which has the unit. 
	 * @return The additional units. 
	 */
	public static AdditionalUnit getAdditionalUnits(Element player) {

		if (player.getChild("additional_units") != null) {
			Element additionalUnitElement = player.getChild("additional_units")
					.getChild("unit");
			String unitname = additionalUnitElement.getChild("unitname")
					.getText();
			Integer item_0 = new Integer(additionalUnitElement.getChild(
					"item_0").getText());
			Integer item_1 = new Integer(additionalUnitElement.getChild(
					"item_1").getText());
			Integer item_2 = new Integer(additionalUnitElement.getChild(
					"item_2").getText());
			Integer item_3 = new Integer(additionalUnitElement.getChild(
					"item_3").getText());
			Integer item_4 = new Integer(additionalUnitElement.getChild(
					"item_4").getText());
			Integer item_5 = new Integer(additionalUnitElement.getChild(
					"item_5").getText());
			return new AdditionalUnit(unitname, item_0, item_1, item_2, item_3,
					item_4, item_5);
		}
		return null;

	}

	/**
	 * Create a new Pick_Ban object.
	 * @param root - The match.
	 * @return The picks and bans.
	 */
	public static List<Pick_Ban> getPicksAndBans(Element root) {

		if (root.getChild("picks_bans") != null) { // pick/ban data present (not standard for all league matches)
			Element picks_bans_root = root.getChild("picks_bans");
			ArrayList<Pick_Ban> picks_bans_list = new ArrayList<Pick_Ban>();
			for (Element pick_ban : picks_bans_root.getChildren("pick_ban")) {
				boolean is_pick = Boolean.valueOf(pick_ban.getChild("is_pick")
						.getText());
				String hero_id = pick_ban.getChild("hero_id").getText();
				int team = new Integer(pick_ban.getChild("team").getText());
				int order = new Integer(pick_ban.getChild("order").getText());
				picks_bans_list
						.add(new Pick_Ban(is_pick, hero_id, team, order));
			}
			return picks_bans_list;
		}
		return null;

	}

	/**
	 * Create a new TournamentDetails object.
	 * @param root - The match.
	 * @param picks_bans_list - The picks and bans.
	 * @return The tournament details.
	 */
	public static TournamentDetails getTournamentDetails(Element root,
			List<Pick_Ban> picks_bans_list) {

		if (root.getChild("radiant_team_id") != null
				&& root.getChild("dire_team_id") != null) // tournament details present (not standard for all league matches)
		{
			int radiant_team_id = new Integer(root.getChild("radiant_team_id")
					.getText());
			String radiant_name = root.getChild("radiant_name").getText();
			long radiant_logo = new Long(root.getChild("radiant_logo")
					.getText());
			int radiant_team_complete = new Integer(root.getChild(
					"radiant_team_complete").getText());
			int dire_team_id = new Integer(root.getChild("dire_team_id")
					.getText());
			String dire_name = root.getChild("dire_name").getText();
			long dire_logo = new Long((root.getChild("dire_logo").getText()));
			int dire_team_complete = new Integer(root.getChild(
					"dire_team_complete").getText());
			return new TournamentDetails(radiant_team_id, radiant_name,
					radiant_logo, radiant_team_complete, dire_team_id,
					dire_name, dire_logo, dire_team_complete, picks_bans_list);
		}

		return null;
	}

	/**
	 * Create a new list of Ability objects.
	 * @param player - The player, which has learned the abilities.
	 * @return A list of abilities. 
	 */
	public static List<Ability> getAbilities(Element player) {

		if (player.getChild("ability_upgrades") != null) {
			List<Ability> abilities = new ArrayList<Ability>();
			for (Element ability : player.getChild("ability_upgrades")
					.getChildren("ability")) {
				int abilityNumber = new Integer(ability.getChild("ability")
						.getText());
				int time = new Integer(ability.getChild("time").getText());
				int level = new Integer(ability.getChild("level").getText());
				abilities.add(new Ability(abilityNumber, time, level));
			}
			return abilities;
		}
		return null;
	}

	/**
	 * Create a new Player object.
	 * @param player - The player.
	 * @param abilities - The abilities.
	 * @param additionalUnit - Any additional unit (Lone Druid Spirit Bear). 
	 * @return The player.
	 */
	public static Player getPlayer(Element player, List<Ability> abilities,
			AdditionalUnit additionalUnit) {

		long account_id = new Long(player.getChild("account_id").getText());
		int player_slot = new Integer(player.getChild("player_slot").getText());
		String hero_id = player.getChild("hero_id").getText();
		int item_0 = new Integer(player.getChild("item_0").getText());
		int item_1 = new Integer(player.getChild("item_1").getText());
		int item_2 = new Integer(player.getChild("item_2").getText());
		int item_3 = new Integer(player.getChild("item_3").getText());
		int item_4 = new Integer(player.getChild("item_4").getText());
		int item_5 = new Integer(player.getChild("item_5").getText());
		Inventory inventory = new Inventory(item_0, item_1, item_2, item_3,
				item_4, item_5);
		int kills = new Integer(player.getChild("kills").getText());
		int deaths = new Integer(player.getChild("deaths").getText());
		int assists = new Integer(player.getChild("assists").getText());
		Score score = new Score(kills, deaths, assists);
		int leaver_status = new Integer(player.getChild("leaver_status")
				.getText());
		int gold = new Integer(player.getChild("gold").getText());
		int last_hits = new Integer(player.getChild("last_hits").getText());
		int denies = new Integer(player.getChild("denies").getText());
		int gold_per_min = new Integer(player.getChild("gold_per_min")
				.getText());
		int xp_per_min = new Integer(player.getChild("xp_per_min").getText());
		int gold_spent = new Integer(player.getChild("gold_spent").getText());
		int hero_damage = new Integer(player.getChild("hero_damage").getText());
		int tower_damage = new Integer(player.getChild("tower_damage")
				.getText());
		int hero_healing = new Integer(player.getChild("hero_healing")
				.getText());
		int level = new Integer(player.getChild("level").getText());
		Statistics statistics = new Statistics(gold, last_hits, denies,
				gold_per_min, xp_per_min, gold_spent, hero_damage,
				tower_damage, hero_healing, level);
		return new Player(account_id, player_slot, hero_id, inventory, score,
				leaver_status, statistics, abilities, additionalUnit);
	}
	
	/**
	 * Create a new Match object.
	 * @param players - The players in the match. 
	 * @param root - The match root. 
	 * @param tournamentDetails - The tournament details, if the match is part of a tournament.  
	 * @return The match.
	 */
	public static Match getMatch(List<Player> players, Element root,
			TournamentDetails tournamentDetails) {
		boolean radiant_win = Boolean.valueOf(root.getChild("radiant_win")
				.getText());
		int duration = new Integer(root.getChild("duration").getText());
		int start_time = new Integer(root.getChild("start_time").getText());
		int match_id = new Integer(root.getChild("match_id").getText());
		int match_seq_num = new Integer(root.getChild("match_seq_num")
				.getText());
		int tower_status_radiant = new Integer(root.getChild(
				"tower_status_radiant").getText());
		int tower_status_dire = new Integer(root.getChild("tower_status_dire")
				.getText());
		int barracks_status_radiant = new Integer(root.getChild(
				"barracks_status_radiant").getText());
		int barracks_status_dire = new Integer(root.getChild(
				"barracks_status_dire").getText());
		int cluster = new Integer(root.getChild("cluster").getText());
		int first_blood_time = new Integer(root.getChild("first_blood_time")
				.getText());
		int lobby_type = new Integer(root.getChild("lobby_type").getText());
		int human_players = new Integer(root.getChild("human_players")
				.getText());
		int leagueid = new Integer(root.getChild("leagueid").getText());
		int positive_votes = new Integer(root.getChild("positive_votes")
				.getText());
		int negative_votes = new Integer(root.getChild("negative_votes")
				.getText());
		int game_mode = new Integer(root.getChild("game_mode").getText());
		return new Match(players, radiant_win, duration, start_time, match_id,
				match_seq_num, tower_status_radiant, tower_status_dire,
				barracks_status_radiant, barracks_status_dire, cluster,
				first_blood_time, lobby_type, human_players, leagueid,
				positive_votes, negative_votes, game_mode, tournamentDetails);
	}

	/**
	 * Create a new MatchResponseDetails object.
	 * @param match - The match root.
	 * @return The match.
	 */
	public static MatchResponseDetails getMatchResponseDetails(Element match) {

		String match_id = match.getChild("match_id").getText();
		String match_seq_num = match.getChild("match_seq_num").getText();
		String start_time = match.getChild("start_time").getText();
		String lobby_type = match.getChild("lobby_type").getText();
		List<MatchResponsePlayer> players = new ArrayList<MatchResponsePlayer>();
		for (Element player : match.getChild("players").getChildren("player")) {
			String account_id = null;
			if (player.getChild("account_id") != null) {
				account_id = player.getChild("account_id").getText();
			}
			String player_slot = player.getChild("player_slot").getText();
			String hero_id = player.getChild("hero_id").getText();
			players.add(new MatchResponsePlayer(account_id, player_slot,
					hero_id));
		}

		return new MatchResponseDetails(match_id, match_seq_num, start_time,
				lobby_type, players);

	}

}
