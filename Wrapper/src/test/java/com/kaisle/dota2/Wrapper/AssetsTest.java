package com.kaisle.dota2.Wrapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.github.kaisle.data.HeroResponse;
import com.github.kaisle.util.Utility;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.HashMap;

/**
 * Created by Anders Borum on 02-06-2015.
 */
public class AssetsTest {

    @Category(FastTestSuite.class)
    @Test
    public void testHeroAssets() {
        HashMap<Integer, HeroResponse> hero_assets = Utility.getHeroDetailsFromId();
        assertEquals(hero_assets.get(2).getDisplayName(), "Axe");
    }

    @Category(FastTestSuite.class)
    @Test
    public void testLobbyType() {
        HashMap<Integer, String> lobby_type_binding = Utility.getLobbyTypeFromId();
        assertEquals(lobby_type_binding.get(2), "Tournament");
    }
}
