package com.github.kaisle.util;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;

import com.github.kaisle.data.MatchResponseDetails;
import com.github.kaisle.data.Player;
import com.github.kaisle.data.PlayerDetails;

/**
 * This class has some useful utility methods, for instance a method for saving
 * a JDOM-Document to a file.
 * 
 * @author Anders Borum
 *
 */
public class Utility {

	/**
	 * Save a JDOM document to a file given by the specified path.
	 * 
	 * @param document
	 *            The document that should be saved.
	 * @param path
	 *            The path that the document should be saved to.
	 */
	public static void saveDocumentToFile(Document document, String path) {
		try {
			new XMLOutputter().output(document, new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Get a steam-account-id from a vanity name. Only some players have vanity names. The vanity name will be the name included in their profile-link at steamcommunity.com. For instance, the player with the steam-profile-link 
	 * http://steamcommunity.com/profiles/Dendi will have the vanity name Dendi. The alternative to a vanity name URL is a URL with a number representing their 64-bit account-id, e.g. http://steamcommunity.com/profiles/76561198047462618.  
	 * @param name The player's vanity name.
	 * @return The player's id. 
	 */
	public static String getSteamIdFromName(String name) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("vanityurl", name);
		params.put("format", "xml");
		Document input = new Query().queryAPI(Defines.resolveVanityURL, params);
		return input.getRootElement().getChild("steamid").getText();
	}
	
	/**
	 * Test whether a player has shared his or her match data. 
	 * @param accountID The 32-bit or 64-bit steam-account-id of the player. 
	 * @return True if the has not shared match-data, false if he has.
	 */
	public static boolean isProfilePrivate(String accountID) {
		BigInteger privateId = new BigInteger("4294967295");
		BigInteger difference6432bit = new BigInteger("76561197960265728");
		if (new BigInteger(accountID).equals(privateId)
				|| new BigInteger(accountID).subtract(difference6432bit)
						.equals(privateId)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Get the steam profile details of a player.
	 * @param accountID The player's account id. 
	 * @return A PlayerDetails object containing the details. 
	 */
	public static PlayerDetails getPlayerInfo(String accountID) {
		StopWatch playerInfoBuild = new LoggingStopWatch("Player Info Build");
		HashMap<String, String> params = ParameterFactory
				.getParametersWithXML();
		if (accountID.length() <= 10) { 
			accountID = (new BigDecimal(accountID).add(new BigDecimal("76561197960265728"))).toString(); 
		}
		params.put("steamids", accountID);
		Document details = new Query().queryAPI(Defines.playerSummaryURL,
				params);
		org.jdom2.Element player = details.getRootElement().getChild("players")
				.getChild("player");
		Long playerSteamID = new Long(player.getChild("steamid").getText());
		long player32bitSteamID = playerSteamID - new Long("76561197960265728");
		Integer playerVisibility = new Integer(player.getChild(
				"communityvisibilitystate").getText());
		String playerPersona = player.getChild("personaname").getText();
		String playerLogOff = player.getChild("lastlogoff").getText();
		String playerProfile = player.getChild("profileurl").getText();
		String playerAvatar = player.getChild("avatar").getText();
		Integer playerPersonaState = new Integer(player
				.getChild("personastate").getText());
		playerInfoBuild.stop();
		return new PlayerDetails(playerSteamID, playerVisibility,
				playerPersona, playerLogOff, playerProfile, playerAvatar, null,
				null, playerPersonaState);
	}

}
