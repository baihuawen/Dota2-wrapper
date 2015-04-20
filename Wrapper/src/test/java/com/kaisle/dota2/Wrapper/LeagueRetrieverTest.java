package com.kaisle.dota2.Wrapper;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.kaisle.data.League;
import com.github.kaisle.util.LeagueRetriever;

public class LeagueRetrieverTest {

	@Category(FastTests.class)
	@Test
	public void getAllLeaguesShouldIncludeSampleLeague() {
		String name = "#DOTA_Item_Dota_2_Just_For_Fun";
		int id = 1212;
		String description = "#DOTA_Item_Desc_Dota_2_Just_For_Fun";
		String tournament_url = "https://binarybeast.com/xDOTA21404228/";
		int itemdef = 10541;
		League sampleLeague = new League(name, id, description, tournament_url,
				itemdef);
		assertTrue(new LeagueRetriever().getAllLeagues().contains(sampleLeague));
	}
}
