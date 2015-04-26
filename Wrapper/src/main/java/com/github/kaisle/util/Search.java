package com.github.kaisle.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.kaisle.data.MatchResponseDetails;
import com.github.kaisle.data.PlayerDetails;
import com.github.kaisle.data.SearchOptions;

public class Search {

	private List<PlayerDetails> players;
	private List<MatchResponseDetails> matches;

	public static List<PlayerDetails> searchNickname(String nickname) {
		List<PlayerDetails> players = new ArrayList<PlayerDetails>();
		try {
			Response response = Jsoup
					.connect(
							Defines.searchPlayerURLDotabuff + "q=" + nickname
									+ "&commit=Search")
					.ignoreContentType(true)
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/37.0.1")
					.referrer("http://www.google.com").timeout(12000)
					.followRedirects(true).execute();
			org.jsoup.nodes.Document doc = response.parse();
			if (doc.title().substring(0, 6).equals("Search")) {
				Elements results = doc.getElementsByClass("record")
						.not(".team").not(".league");
				for (Element result : results) {
					List<Element> children = result.children();
					for (Element child : children) {
						if (child.className().equals("name")) {
							Element nameElement = child.child(0);
							String name = nameElement.ownText();
							long id = new Long(nameElement.attr("href")
									.substring(9));
							if (Utility.isProfilePrivate(new Long(id)
									.toString())) {
								continue;
							}
							players.add(new PlayerDetails(id, name));
							System.out.println(name);
							System.out.println(id);
						}
					}
				}
			} else if (doc.title().contains("- DOTABUFF - Dota 2 Stats")) {
				System.out.println(doc.title());
				players.add(new PlayerDetails(new Long(response.url()
						.toString().substring(32)), nickname));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return players;

	}

	public String extractIdFromProfileURL(String profileURL) {
		int idBeginIndex = 0;
		if (profileURL.contains("profiles/")) {
			idBeginIndex = profileURL.indexOf("profiles/")
					+ "profiles/".length();
		} else if (profileURL.contains("id/")) {
			idBeginIndex = profileURL.indexOf("id/") + "id/".length();
		}
		int idEndIndex = profileURL.lastIndexOf("/");
		String profileString = new String();
		if (idEndIndex > idBeginIndex) {
			profileString = profileURL.substring(idBeginIndex, idEndIndex);
		} else {
			profileString = profileURL.substring(idBeginIndex);
		}

		return profileString;
	}

	public boolean search(String searchString) {
		System.out.println(searchString);
		MatchRetriever matchRetriever = new MatchRetriever();
		switch (evaluateString(searchString)) {
		case ID:
			matches = matchRetriever.getLightMatchData(searchString);
			return true;
		case URL_ID:
			matches = matchRetriever
					.getLightMatchData(extractIdFromProfileURL(searchString));
			return true;
		case VanityName:
			matches = matchRetriever.getLightMatchData(Utility
					.getSteamIdFromName(extractIdFromProfileURL(searchString)));
			return true;
		case NoDirectMatch:
			players = searchNickname(searchString);
			if (players.size() == 1) {
				matches = matchRetriever.getLightMatchData(new Long(players
						.get(0).getSteamid()).toString());
				return true;
			}
			return false;
		case ID_OUT_OF_RANGE:
			return false;
		case INVALID_ID:
			return false;
		default:
			break;
		}
		return false;
	}

	public static SearchOptions evaluateString(String searchString) {
		try {
			Long.parseLong(searchString);
			return SearchOptions.ID;
		} catch (NumberFormatException e) {
			if (searchString.contains("steamcommunity.com")) {
				int idBeginIndex = 0;
				if (searchString.contains("profiles/")) {
					idBeginIndex = searchString.indexOf("profiles/")
							+ "profiles/".length();
				} else if (searchString.contains("id/")) {
					idBeginIndex = searchString.indexOf("id/") + "id/".length();
				}
				//int lastSlash = searchString.lastIndexOf("/");
				int idEndIndex = searchString.lastIndexOf("/");
				String profileString = new String();
				if (idEndIndex > idBeginIndex) {
					profileString = searchString.substring(idBeginIndex,
							idEndIndex);
				} else {
					profileString = searchString.substring(idBeginIndex);
				}
				if (profileString.length() > 18) {
					return SearchOptions.ID_OUT_OF_RANGE;
				}
				try {
					Long.parseLong(profileString);
					return SearchOptions.URL_ID;
				} catch (NumberFormatException e1) {
					if (profileString.matches(".*\\d+.*")) {
						return SearchOptions.INVALID_ID;
					} else {
						return SearchOptions.VanityName;
					}
				}

			} else {
				return SearchOptions.NoDirectMatch;
			}
		}

	}

	public List<PlayerDetails> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDetails> players) {
		this.players = players;
	}

	public List<MatchResponseDetails> getMatches() {
		return matches;
	}

	public void setMatches(List<MatchResponseDetails> matches) {
		this.matches = matches;
	}
}
