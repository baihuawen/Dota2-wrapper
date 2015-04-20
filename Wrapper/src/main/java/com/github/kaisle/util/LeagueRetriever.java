package com.github.kaisle.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import com.github.kaisle.data.League;
/**
 * This class can extract details about leagues.
 * @author Anders Borum
 *
 */
public class LeagueRetriever {

	/**
	 * Get all leagues mapped as League-objects, each containing data about the specific league.
	 * @return leagues A list of leagues.
	 */
	public List<League> getAllLeagues() {
		HashMap<String, String> params = ParameterFactory.getParametersWithXML(); 
		Document response = new Query().queryAPI(Defines.leagueMatchesURL, params);
		List<League> leagues = new ArrayList<League>();
		for (Element league : response.getRootElement().getChild("leagues")
				.getChildren("league")) {
			String name = league.getChild("name").getText();
			int leagueid = new Integer(league.getChild("leagueid").getText());
			String description = league
					.getChild("description").getText();
			String tournament_url = league
					.getChild("tournament_url").getText();
			int itemdef = new Integer(
					league.getChild("itemdef").getText());
			leagues.add(new League(name, leagueid, description, tournament_url, itemdef));
		}
		return leagues;

	}
	
	/**
	 * Get a specified set of league matches stored in a JDOM-document.
	 * @param leagueIDs -
	 *            A list of relevant league ID's.
	 * @return A JDOM-document containing the matches. 
	 */
	public List<Document> getSpecificLeagueMatchesInXML(List<String> leagueIDs) {
		final List<Document> documents = Collections
				.synchronizedList(new ArrayList<Document>());
		for (final League league : getAllLeagues()) { // Spawn new thread for each league
			if (leagueIDs
					.contains(new Integer(league.getLeagueid()).toString())) {
				new Thread(new Runnable() {
					final String currentLeagueID = new Integer(
							league.getLeagueid()).toString();
					public void run() {
						// TODO Auto-generated method stub
						documents.add(getAllMatchesForLeagueInXML(currentLeagueID)); // Add league data to document
					}

				});

			}

		}
		return documents;
	}
	
	/**
	 * Get all matches from a league stored in a document.
	 * @param leagueID
	 *            The ID of the league.
	 * @return A JDOM-Document containing all the matches of the specified league.
	 */
	public Document getAllMatchesForLeagueInXML(String leagueID) {

		HashMap<String, String> params = ParameterFactory.getParametersWithXML(); 
		params.put("league_id", leagueID);
		Document mainDoc = DocumentFactory.createDocument("leagueid", leagueID); // Set account-ID as root
		new Query().getSequenceOfMatches(mainDoc, params, true); // iterate through player matches 
		return mainDoc;
	}

	
	
	
	/**
	 * Get a JDOM-Document containing all league matches.
	 * @return All league matches mapped in a JDOM-Document.
	 */
	public List<Document> getAllLeagueMatchesInXML() {
		final List<Document> documents = Collections
				.synchronizedList(new ArrayList<Document>());
		for (final League league : getAllLeagues()) {
			new Thread(new Runnable() {
				final String currentLeagueID = new Integer(league.getLeagueid())
						.toString();

				public void run() {
					// TODO Auto-generated method stub
					documents.add(getAllMatchesForLeagueInXML(currentLeagueID));
				}

			});
		}
		return documents;
	}

	
}
