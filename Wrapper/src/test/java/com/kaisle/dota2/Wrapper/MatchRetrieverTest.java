package com.kaisle.dota2.Wrapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.kaisle.data.Match;
import com.github.kaisle.util.Defines;
import com.github.kaisle.util.MatchRetriever;
import com.github.kaisle.util.Query;
/**
 * This test-class tests whether the MatchRetriever class can extract valid match-data. 
 * @author Anders Borum
 *
 */
public class MatchRetrieverTest {

	@Category(FastTests.class)
	@Test
	public void queryShouldReturnProperDoc() {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("format", "XML");
		assertEquals(new Query().queryAPI(Defines.matchHistoryURL, parameters)
				.getRootElement().getName(), "result");
	}

	@Category(SlowTests.class)
	@Test
	public void getAllMatchesForPlayerShouldReturnProperDoc() {
		String accountID = "76561198051709146";
		assertEquals(
				new MatchRetriever()
						.getAllMatchesForPlayerAsDocument(accountID)
						.getRootElement().getName(), "accountID");
	}
	
	@Category(SlowTests.class)
	@Test
	public void getMatchesForLeagueShouldIncludeSampleMatch() {
		String sampleMatchId = "19926255";
		Match sampleMatch = new MatchRetriever().getMatchDetails(sampleMatchId);
		List<Match> leagueMatches = new MatchRetriever()
				.getAllMatchesForLeague("2");
		assertTrue(leagueMatches.contains(sampleMatch));
	}
	
	@Category(FastTests.class)
	@Test
	public void specificMatchShouldHavePlayerWithId() {
		long accountID = 166207485;
		String matchID = "1357370233";
		Match match = new MatchRetriever().getMatchDetails(matchID);
		System.out.println(match.getPlayers().get(5).getAccount_id());
		assertEquals(match.getPlayers().get(5).getAccount_id(), accountID);
		
	}
	
	@Category(UltraSlowTests.class)
	@Test
	public void match5WithGivenIdShouldBeRadiantWin() {
		String accountID = "76561198146605594"; // 
		List<Match> accountIDMatches = new MatchRetriever().getAllMatchesForPlayer(accountID);
		assertTrue(accountIDMatches.get(4).isRadiant_win());
	}

}
