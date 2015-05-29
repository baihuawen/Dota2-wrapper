# DotA2-Wrapper
Wrapper for the DotA2 WebAPI written in Java. It greatly simplifies the process of performing automatic API-requests, as well as combining the responses and deserializing them into Java objects. It also offers a number of additional features, such as player search through Dotabuff and retrieval of Steam Community data. Possibly a very solid stepping stone for your new DotA2-application. 

<br>To start using it, just include it through Maven by adding the following XML-snippet to your pom.xml: 

```
        <dependency>
          <groupId>com.github.kaisle</groupId>
           <artifactId>DotA2-Wrapper</artifactId>
           <version>1.0.1</version>
        </dependency>

```
<br>Then get your API-key at: http://steamcommunity.com/dev/apikey. 
Finally, add your key to the apiKey-field of the Defines class (empty by default, must be specified): 
```
        Defines.apiKey = <your_api_key>; 
```
<br>Now you have access to all the functionality of this wrapper.
Before proceeding, make sure you have read the terms for using the API: http://steamcommunity.com/dev/apiterms

##Getting the last 500 matches for a player (if you know his/her steam-id)

If you already know the 32-bit or 64-bit steam-id of the player, this is very easy. Just create a new MatchRetriever-object
and invoke the method getAllMatchesForPlayer, specifying the id as parameter. For instance, to get the matches for a player with id <i>86745912</i>:
```
        MatchRetriever matchRetriever = new MatchRetriever(); 
        List<MatchResponseDetails> matches = matchRetriever.getLightMatchData("86745912"); 
```
<br>After running the above code the wrapper will call the API 5 times (100 matches per request) to get the last 500 matches, represented as MatchResponseDetails-objects.
<br>A MatchResponseDetails object contains the following information (courtesy of http://dev.dota2.com/showthread.php?t=58317): 
``` 
    	match_id - the numeric match ID
        match_seq_num - the match's sequence number - the order in which matches are recorded
        start_time - date in UTC seconds since Jan 1, 1970 (unix time format)
        lobby_type - the type of lobby; see: https://github.com/kronusme/dota2-ap...a/lobbies.json
        players - an array of players (MatchResponsePlayer) with the following properties: 
                account_id - the player's 32-bit Steam ID - will be set to "4294967295" if the player has set their account to private. 
                player_slot - an 8-bit unsigned int: if the left-most bit is set, the player was on dire. the two right-most bits represent the player slot (0-4).
                hero_id - the numeric ID of the hero that the player used (https://github.com/kronusme/dota2-api/blob/master/data/heroes.json)
``` 
<br>To get the <b>full details</b> of a match the wrapper needs to call the API for that specific match. This is done by specifying the match_id.
<br>For instance, to get the full details of match with id <i>1404838670</i>: 
``` 
        Match match = matchRetriever.getMatchDetails(1404838670); 
``` 

<br>Now you have access to a Match object which contains all the available data: 

``` 
    radiant_win - true if radiant won, false otherwise
    duration -  the total time in seconds the match ran for
	start_time - date in UTC seconds since Jan 1, 1970 (unix time format)
	match_id - the numeric match ID
	match_seq_num - the match's sequence number - the order in which matches are recorded
	tower_status_radiant - an 11-bit unsinged int: see http://wiki.teamfortress.com/wiki/WebAPI/GetMatchDetails#Tower_Status
    tower_status_dire - an 11-bit unsinged int: see http://wiki.teamfortress.com/wiki/WebAPI/GetMatchDetails#Tower_Status
    barracks_status_radiant - a 6-bit unsinged int: see http://wiki.teamfortress.com/wiki/WebAPI/GetMatchDetails#Barracks_Status
    barracks_status_dire - a 6-bit unsinged int: see http://wiki.teamfortress.com/wiki/WebAPI/GetMatchDetails#Barracks_Status
	cluster - replay-id, see http://dev.dota2.com/showthread.php?t=58317 under REPLAYS
    first_blood_time - the time in seconds at which first blood occurred
	lobby_type -  the type of lobby; see: https://github.com/kronusme/dota2-ap...a/lobbies.json
	int human_players - the number of human players in the match
	leagueid - the league this match is from
	positive_votes - the number of thumbs up the game has received
	negative_votes - the number of thumbs down the game has received
	game_mode - a number representing the game mode of this match; see https://github.com/kronusme/dota2-ap...data/mods.json
    players- an array of players (Player-object) with the following properties: 
                account_id - the player's 32-bit Steam ID - will be set to "4294967295" if the player has set their account to private.
                player_slot - an 8-bit unsigned int: if the left-most bit is set, the player was on dire. the two right-most bits represent the player slot (0-4)
                hero_id - the numeric ID of the hero that the player used.
                leaver_status 
                Inventory - an object containing the items owned by the player as properties: 
                        item_0 - the numeric ID of the item that player finished with in their top-left slot.
                        item_1 - the numeric ID of the item that player finished with in their top-center slot.
                        item_2 - the numeric ID of the item that player finished with in their top-right slot.
                        item_3 - the numeric ID of the item that player finished with in their bottom-left slot.
                        item_4 - the numeric ID of the item that player finished with in their bottom-center slot.
                        item_5 - the numeric ID of the item that player finished with in their bottom-right slot.
                Score - an object containing statistics about player score: 
                        kills - the number of kills the player got.
                        deaths - the number of times the player died.
                        assists - the number of assists the player got.
                Statistics - an object containing other stastitics about how the player performed: 
                        gold - the amount of gold the player had left at the end of the match
                        last_hits - the number of times a player last-hit a creep
                        denies - the number of times a player denied a creep
                        gold_per_min - the player's total gold/min
                        xp_per_min - the player's total xp/min
                        gold_spent - the total amount of gold the player spent over the entire match
                        hero_damage - the amount of damage the player dealt to heroes
                        tower_damage - the amount of damage the player dealt to towers
                        hero_healing - the amount of damage on other players that the player healed
                        level - the player's final level
                an array of abilities (List<Ability>) learned by the player in the particular match, each ability having the following properties
                        abilityId - the numeric ID of the ability that the point was spent on.
                        time - the time this ability point was spent - in number of seconds since the start of the match.
                        level - the level of the hero when the ability was leveled.
                an array of additonal units (List<AdditionalUnit>) controlled by the player (e.g. Spirit Bear for Lone Druid): 
                        unitname - the name of the unit
                        item_0 - the numeric ID of the item that player finished with in their top-left slot.
                        item_1 - the numeric ID of the item that player finished with in their top-center slot.
                        item_2 - the numeric ID of the item that player finished with in their top-right slot.
                        item_3 - the numeric ID of the item that player finished with in their bottom-left slot.
                        item_4 - the numeric ID of the item that player finished with in their bottom-center slot.
                        item_5 - the numeric ID of the item that player finished with in their bottom-right slot.
                PlayerDetails - an object containing community details about the player, see https://developer.valvesoftware.com/wiki/Steam_Web_API#GetPlayerSummaries_.28v0002.29
                        
                        
                
``` 

<br>To get this amount of details for all of the player's last 500 matches, use the following command: 
``` 
        List<Match> matches = matchRetriever.getAllMatchesForPlayer(<steam-id>); 
``` 
Bear in mind though that this request will take a lot of time to process (the wrapper has to call the API 505 times).

<br>To get all the match details of an array of players, put each of their id's in an array of strings and invoke the following command: 
```
	List<Match> matches = matchRetriever.getAllMatchesForPlayers(<your-id-array>); 
```
<br>To save all matches for a player as an XML-file on your local drive: 
```
	matchRetriever.getAllMatchesForPlayerInXML(<steam-id>, <path to save location>); 
```
<br><b><i>Can I get older matches?</i></b>
<br>Nope. Only the last 500 matches for each player is stored in Valve's database. To get older matches, you would need to use data from a third-party site which has those stored in their own database, such as Dotabuff. 
##Getting the last 500 matches for a player (if you don't know his/her steam-id)

Use the Search-object. The Search-object scours Dotabuff.com for data so use it moderately/wisely and make sure to credit the Dotabuff-guys for the search results that you receive. To use it, simply use the search-method and put your search-string as a parameter, then retrieve the results from the fields of the search-object. You can put the nickname, steam-profile-link or steam-id as search parameters. If there is just one search result, the DotA2-matches for that player is stored as Match-objects in the private field called matches. If there are several search results (e.g. for the keyword <i>s4</i> there will be loads of matches/results), the nicknames and steam-id's of those players are stored as PlayerDetails-objects in the field called players. E.g. to search for players with the nickname "Dendi":
```
	Search search = new Search();
	search.search("Dendi");
	List<MatchResponseDetails> matchResults = search.getMatches(); // Will be empty in this case, as there are lots of players with the nickname "Dendi"
	List<PlayerDetails> players = search.getPlayers(); // Will contain all the players whose nicknames are affiliated with "Dendi"
```
You can then evaluate the results from the list of players and get the last 500 DotA2-matches for the one that you choose, e.g. if you choose the first result:
```
	Long relevantPlayerSteamId = players.get(0).getSteamid; // Get steam-id as a Long
	MatchRetriever matchRetriever = new MatchRetriever();
	List<Match> relevantPlayerMatches = matchRetriever.getAllMatchesForPlayer(relevantPlayerSteamId.toString()); // Convert Long to String and get all matches for the player  
```
If there was just one search result, the above is not neccesary. The search-object will automatically fetch the matches for the player and they can be retrieved like this:
```
	List<Match> relevantPlayerMatches = search.getMatches(); 
```

##Getting the full details of a match

Specify the id to your MatchRetriever, e.g. for id 1389940008: 

        MatchRetriever matchRetriever = new MatchRetriever();
        Match match = matchRetriever.getMatchDetails(1389940008);

##Getting league data

To get all the leagues currently available, invoke: 
```
	LeagueRetriever leagueRetriever() = new LeagueRetriever();
	List<League> leagues = leagueRetriever.getAllLeagues(); 
```
<br> A league-object has the following properties: 
```
	name - Name of league. 
	leagueid - ID of league. 
	description - Description. 
	tournament_url - URL to the site of the league.
	itemdef
```

<br>To get all the matches for a specific league, specify the league-id, e.g. for the league with id <i>2</i>: 
```
	MatchRetriever matchRetriever = new MatchRetriever();
	List<Match> leagueMatches = matchRetriever.getAllMatchesForLeague("2"); 
```
<br>To get all matches for all leagues (takes a while): 
```
	List<Match> allLeagueMatches = matchRetriever.getAllLeagueMatches(); 
```
<br> To get all matches for a specific set of leagues: 
```
	List<Match> specificLeagueMatches = matchRetriever.getSpecificLeagueMatches(<java.util.List of relevant league-id's>)
```
<br>Save all matches for a specific set of leagues as XML:
```
	LeagueRetriever leagueRetriever = new LeagueRetriever();
	List<Document> specificLeagueMatches = leagueRetriever.getSpecificLeagueMatchesAsXML(<java.util.List of relevant league-id's>);
	for (Document document: specificLeagueMatches) {
	Utility.saveDocumentToFile(document, <path to save location>); 
	}
```

##Getting community details about a player

To get community details about a player, just specify the 32-bit or 64-bit steam-id for that player:
```
	PlayerDetails communityDetails = Utility.getPlayerInfo(<steam-id>); 
```
<br> The PlayerDetails object contains data such as the nickname/persona-name, a URL for the player avatar, a URL for the player profile and the time since the player has last logged off in UNIX-time. A full description can be found here: https://developer.valvesoftware.com/wiki/Steam_Web_API#GetPlayerSummaries_.28v0002.29. 

##Checking whether a player has shared match data

Simply specify the player's 32-bit or 64-bit steam-id: 
```
	boolean isProfilePrivate = Utility.isProfilePrivate(<steam-id>); 
```





