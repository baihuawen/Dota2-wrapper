package com.github.kaisle.util;
/**
 * This class stores a number of global variables, mostly request-strings. To query the API you must set the apiKey-field of this class to a valid API key. 
 * @author Anders Borum
 *
 */
public class Defines {
	public static final String matchHistoryURL = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/v001/"; 
	public static final String matchDetailsURL = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v001/"; 
	public static final String leagueMatchesURL = "https://api.steampowered.com/IDOTA2Match_570/GetLeagueListing/v0001/";
	public static final String heroURL = "https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/";
	public static final String imageURL = "http://cdn.dota2.com/apps/dota2/images/heroes/";
	public static final String playerSummaryURL ="http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/";
	public static final String lobbyTypeURL = "https://raw.githubusercontent.com/kronusme/dota2-api/master/data/lobbies.json";
	public static final String steamProfileBaseURL = "http://steamcommunity.com/id";
	public static final String resolveVanityURL = " http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/";
	public static final String searchPlayerURL = "http://www.dotabuff.com/search?";
	public static final String smallPic = "sb.png";
	public static final String mediumPic = "lg.png";
	public static final String horiPic = "full.png";
	public static final String vertPic = "vert.jpg";
	public static String apiKey; 
	
	public void setAPIKey(String myApiKey) {
		apiKey = myApiKey; 
	}
}
