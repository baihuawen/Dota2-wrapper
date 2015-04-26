package com.github.kaisle.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;

import com.github.kaisle.data.Ability;
import com.github.kaisle.data.AdditionalUnit;
import com.github.kaisle.data.League;
import com.github.kaisle.data.Match;
import com.github.kaisle.data.MatchResponseDetails;
import com.github.kaisle.data.Pick_Ban;
import com.github.kaisle.data.Player;
import com.github.kaisle.data.TournamentDetails;

/**
 * This class can extract details about matches.
 * 
 * @author Anders Borum
 */
public class MatchRetriever {

	/**
	 * Get a list of matches based on a number of parameters.
	 * 
	 * @param params
	 *            A mapped representation of parameters, eg. ["format", "xml"]
	 * @return A list of matches.
	 */
	public List<Match> getMatches(HashMap<String, String> params) {
		ParameterFactory.setFormatToXML(params); // format has to be XML for the JDOM-implementation to deal with it
		Document doc = DocumentFactory.createDocument("Matches"); // has to create a new document, see Query.getSequenceOfMatches()
		new Query().getSequenceOfMatches(doc, params, true); // iterate through player matches 
		List<String> ids = extractIds(doc); // collect id for each match  
		List<Match> matches = new ArrayList<Match>(); // store matches in array-list
		for (String id : ids) {
			matches.add(getMatchDetails(id)); // iterate through match id's, get details from each one, create object based on details and store object in list
		}
		return matches;
	}

	/**
	 * Retrieve all the matches of a given player.
	 * 
	 * @param accountID
	 *            The 32-bit or 64-bit ID of the player's steam-account.
	 * @return A list of matches for the given player.
	 */
	public List<Match> getAllMatchesForPlayer(String accountID) {

		HashMap<String, String> params = ParameterFactory
				.getParametersWithXML();
		params.put("account_id", accountID);
		Document mainDoc = DocumentFactory.createDocument("accountID",
				accountID); // Set account-ID as root
		List<Match> matches = new ArrayList<Match>();
		if (Utility.isProfilePrivate(accountID)) { // Test if user profile has enabled "Share match data" option inside client. If not, do not query the API. 
			System.out
					.println("User with account-id "
							+ accountID
							+ " is not sharing match data, returning no matches for this player.");  
			return matches;
		}
		try {
			new Query().getSequenceOfMatches(mainDoc, params, true); // iterate through player matches 
		} catch (NullPointerException e) { // Exception thrown if player is not sharing match data. 
			System.out
					.println("Player with account-id "
							+ accountID
							+ " does either not exist or is not sharing match data, returning no matches for this player");
			return matches;
		}
		List<String> ids = extractIds(mainDoc); // collect id for each match  
		for (String id : ids) {
			matches.add(getMatchDetails(id));
		}
		return matches;
	}

	/**
	 * Get all matches for an array of players. Spawns a thread and builds a
	 * document for each player, storing the matches of the document in a list.
	 * 
	 * @param accountID
	 *            The 32-bit or 64-bit steam-account ID's of the players stored
	 *            in an array.
	 * @return A list containing the matches.
	 */
	public List<Match> getAllMatchesForPlayers(String[] accountID) {
		final List<Match> matches = Collections
				.synchronizedList(new ArrayList<Match>());
		for (int i = 0; i < accountID.length; i++) {
			final String currentAccountID = accountID[i];
			new Thread(new Runnable() { // Spawn new thread for each player

						public void run() {
							List<Match> playerMatches = getAllMatchesForPlayer(currentAccountID);
							for (Match match : playerMatches) {
								matches.add(match);
							}
						}

					}).start();

		}

		return matches;
	}

	/**
	 * Get all matches from a league.
	 * 
	 * @param leagueID
	 *            The ID of the league.
	 * @return A list containing all the matches of the specified league.
	 */
	public List<Match> getAllMatchesForLeague(String leagueID) {

		HashMap<String, String> params = ParameterFactory
				.getParametersWithXML();
		params.put("league_id", leagueID);
		Document mainDoc = DocumentFactory.createDocument("leagueid", leagueID); // Set account-ID as root
		new Query().getSequenceOfMatches(mainDoc, params, true); // iterate through player matches 
		List<Match> matches = new ArrayList<Match>();
		for (String id : extractIds(mainDoc)) {
			matches.add(getMatchDetails(id));
		}
		return matches;
	}

	/**
	 * Get a specified set of league matches.
	 * 
	 * @param leagueIDs
	 *            A list of relevant league ID's.
	 * @return A list containing the matches.
	 */
	public List<Match> getSpecificLeagueMatches(List<String> leagueIDs) {
		final List<Match> matches = Collections
				.synchronizedList(new ArrayList<Match>());
		for (final League league : new LeagueRetriever().getAllLeagues()) {
			if (leagueIDs
					.contains(new Integer(league.getLeagueid()).toString())) {
				new Thread(new Runnable() {
					final String currentLeagueID = new Integer(
							league.getLeagueid()).toString();

					public void run() {
						// TODO Auto-generated method stub
						List<Match> leagueMatches = getAllMatchesForLeague(currentLeagueID);
						for (Match match : leagueMatches) {
							matches.add(match);
						}
					}

				});

			}

		}
		return matches;
	}

	/**
	 * Get all league matches.
	 * 
	 * @return All league matches stored in a list.
	 */
	public List<Match> getAllLeagueMatches() {
		final List<Match> matches = Collections
				.synchronizedList(new ArrayList<Match>());
		for (final League league : new LeagueRetriever().getAllLeagues()) {
			new Thread(new Runnable() {
				final String currentLeagueID = new Integer(league.getLeagueid())
						.toString();

				public void run() {
					// TODO Auto-generated method stub
					List<Match> leagueMatches = getAllMatchesForLeague(currentLeagueID);
					for (Match match : leagueMatches) {
						matches.add(match);
					}
				}

			});
		}
		return matches;
	}

	/**
	 * Get the details of a match with a specific ID.
	 * 
	 * @param matchId
	 *            The ID of the match.
	 * @return A Match-object containing all the details of the match.
	 */
	public Match getMatchDetails(String matchId) {
		HashMap<String, String> params = ParameterFactory
				.getParametersWithXML();
		params.put("match_id", matchId);
		StopWatch query = new LoggingStopWatch("Query");
		Document matchDetails = new Query().queryAPI(Defines.matchDetailsURL,
				params); // Query API
		query.stop();
		StopWatch build = new LoggingStopWatch("Build");
		List<Player> players = new ArrayList<Player>();
		if (matchDetails.hasRootElement()) { // Check for root
			Element root = matchDetails.getRootElement();
			List<Pick_Ban> picks_bans_list = MatchComponentFactory
					.getPicksAndBans(root);
			TournamentDetails tournamentDetails = MatchComponentFactory
					.getTournamentDetails(root, picks_bans_list);
			for (Element player : root.getChild("players")
					.getChildren("player")) { // Add players
				List<Ability> abilities = MatchComponentFactory
						.getAbilities(player);
				AdditionalUnit additionalUnit = MatchComponentFactory
						.getAdditionalUnits(player);
				players.add(MatchComponentFactory.getPlayer(player, abilities,
						additionalUnit));
			}
			Match match = MatchComponentFactory.getMatch(players, root,
					tournamentDetails);
			build.stop();
			return match;
		}
		return null;
	}
	
	/**
	 * Get light match data for a specific player, meaning some basic details about his last 500 matches. Check the MatchResponseDetails object for available data. The point of this method is that it only takes 5 queries to retrieve (100 matches per query), whereas
	 * it would take 500 queries to retrieve all the details about the matches, as one would have to query for each individual match.  
	 * @param accountID The 32-bit or 64-bit ID of the player's steam-account.
	 * @return A list of MatchResponseDetails objects, containing light match data about the player's last 500 matches. 
	 */
	public List<MatchResponseDetails> getLightMatchData(String accountID) {
		List<MatchResponseDetails> matches = new ArrayList<MatchResponseDetails>();
		if (Utility.isProfilePrivate(accountID)) {
			System.out
					.println("User with account-id "
							+ accountID
							+ " is not sharing match data, returning no matches for this player.");
			return matches;
		}
		HashMap<String, String> params = ParameterFactory
				.getParametersWithXML();
		params.put("account_id", accountID);
		Document mainDoc = DocumentFactory.createDocument("accountID",
				accountID); // Set account-ID as root
		try {
			new Query().getSequenceOfMatches(mainDoc, params, true); // iterate through player matches
		} catch (NullPointerException e) {
			System.out
					.println("Player with account-id "
							+ accountID
							+ " is not sharing match data, returning no matches for this player");
			return matches;
		}
		for (Element result : mainDoc.getRootElement().getChildren("result")) {
			for (Element match : result.getChild("matches")
					.getChildren("match")) {
				matches.add(MatchComponentFactory
						.getMatchResponseDetails(match));
			}
		}
		return matches;
	}

	/**
	 * Extract id's from a document of match results.
	 * 
	 * @param doc
	 *            The JDOM-document containing matches.
	 * @return A list of strings, representing the id's.
	 */
	public List<String> extractIds(Document doc) {
		List<String> matchIds = new ArrayList<String>();
		for (Element match : doc.getRootElement().getChild("result")
				.getChild("matches").getChildren("match")) {
			matchIds.add(match.getChild("match_id").getText());
		}
		return matchIds;
	}

	/**
	 * Retrieve all the matches of a given player in XML-format
	 * 
	 * @param accountID
	 *            The 32-bit or 64-bit ID of the player's steam-account.
	 * @param path
	 *            The path leading to the location where the XML-document should
	 *            be saved.
	 */
	public void getAllMatchesForPlayerInXML(String accountID, String path) {
		if (Utility.isProfilePrivate(accountID)) {
			System.out
					.println("User with account-id "
							+ accountID
							+ "is not sharing match data, returning no matches for this player.");
			return;
		}
		HashMap<String, String> params = ParameterFactory
				.getParametersWithXML(); // Set parameters
		params.put("account_id", accountID);
		Document mainDoc = DocumentFactory.createDocument("accountID",
				accountID); // Set account-ID as root
		try {
			new Query().getSequenceOfMatches(mainDoc, params, true); // iterate through player matches  
		} catch (NullPointerException e) {
			System.out
					.println("Player with account-id "
							+ accountID
							+ " does either not exist or is not sharing match data, returning no matches for this player");
			return;
		}
		Utility.saveDocumentToFile(mainDoc, path);
	}

	/**
	 * Get all methods for an array of players. Spawns a thread and builds a
	 * document for each player, storing the documents in a list.
	 * 
	 * @param accountID
	 *            The 32-bit or 64-bit steam-account ID's of the players stored
	 *            in an array.
	 * @return A JDOM-document containing the matches.
	 */
	public List<Document> getAllMatchesForPlayersInXML(String[] accountID) {
		final List<Document> documents = Collections
				.synchronizedList(new ArrayList<Document>());
		for (int i = 0; i < accountID.length; i++) {
			final String currentAccountID = accountID[i];
			new Thread(new Runnable() { // Spawn new thread for each player

						public void run() {

							documents
									.add(getAllMatchesForPlayerAsDocument(currentAccountID));
						}

					}).start();

		}

		return documents;
	}

	/**
	 * Get a document containing all the matches of a player.
	 * 
	 * @param accountID
	 *            The 32-bit or 64-bit ID of the player's steam-account.
	 * @return A JDOM-document containing the matches.
	 */
	public Document getAllMatchesForPlayerAsDocument(String accountID) {
		HashMap<String, String> params = ParameterFactory
				.getParametersWithXML(); // Set parameters
		params.put("account_id", accountID);
		Document mainDoc = DocumentFactory.createDocument("accountID",
				accountID); // Set account-ID as root
		if (Utility.isProfilePrivate(accountID)) {
			System.out
					.println("User with account-id "
							+ accountID
							+ " is not sharing match data, returning no matches for this player.");
			return mainDoc;
		}
		try {
			new Query().getSequenceOfMatches(mainDoc, params, true); // iterate through player matches  
		} catch (NullPointerException e) {
			System.out
					.println("Player with account-id "
							+ accountID
							+ "does either not exist or is not sharing match data, returning no matches for this player");
			return mainDoc; 
		}
		return mainDoc;
	}
}
