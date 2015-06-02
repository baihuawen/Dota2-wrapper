package com.github.kaisle.util;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.kaisle.data.HeroResponse;
import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

	/**
	 * Get portraits and names for each hero, represented by a HeroResponse object. Each object is bound to the corresponding hero id.
	 * @return A map of hero id's and the corresponding hero details (name/portrait urls)
	 */
	public static HashMap<Integer, HeroResponse> getHeroDetailsFromId() {
		HashMap<Integer, HeroResponse> hero_assets = new HashMap<Integer, HeroResponse>();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("language", "en_us");
		params.put("format", "xml");
		Document input = new Query().queryAPI(Defines.heroURL, params);
		for (org.jdom2.Element hero: input.getRootElement().getChild("heroes").getChildren("hero")) {
			String name = hero.getChild("name").getText();
			String sub = name.substring(name.indexOf("_") + 1);
			sub = sub.substring(sub.indexOf("_") + 1);
			sub = sub.substring(sub.indexOf("_") + 1);
			String smallPicURL = Defines.imageURL + sub + "_" + Defines.smallPic;
			String mediumPicURL = Defines.imageURL + sub + "_" + Defines.mediumPic;
			String largeHorizontalPicURL = Defines.imageURL + sub + "_" + Defines.horiPic;
			String largeVerticalPicURL = Defines.imageURL + sub + "_" + Defines.vertPic;
			hero_assets.put(new Integer(hero.getChild("id").getText()), new HeroResponse(hero.getChild("id").getText(), name, hero.getChild("localized_name").getText(), smallPicURL, mediumPicURL, largeHorizontalPicURL, largeVerticalPicURL));
		}

		return hero_assets;

	}

	/**
	 * Get the type for each lobby, represented as a String. The type is mapped to the lobby id String.
	 */
	public static HashMap<Integer, String> getLobbyTypeFromId() {
		HashMap<Integer, String> lobby_mapping = new HashMap<Integer, String>();
		JSONObject lobbies = new Query().queryAPIForJSON(Defines.lobbyTypeURL, new HashMap<String, String>());
		try {
			JSONArray lobbyTypes = lobbies.getJSONArray("lobbies");
			for (int i = 0; i < lobbyTypes.length(); i++) {
				String id = lobbyTypes.getJSONObject(i).get("id").toString();
				String name = lobbyTypes.getJSONObject(i).get("name").toString();
				lobby_mapping.put(new Integer(id), name);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lobby_mapping;
	}

}
