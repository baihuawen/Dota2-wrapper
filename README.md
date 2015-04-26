# DotA2-Wrapper
Wrapper for the DotA2 WebAPI written in Java. It greatly simplifies the process of performing automatic API-requests, as well as combining the responses and deserializing them into Java objects. It also offers a number of additional features, such as player search through Dotabuff and retrieval of Steam Community data. Possibly a very solid stepping stone for your new DotA2-application. 

<br>To start using it, just include it through Maven by adding the following XML-snippet to your pom.xml: 

```
        <dependency>
          <groupId>com.github.kaisle</groupId>
           <artifactId>DotA2-Wrapper</artifactId>
           <version>1.0.0</version>
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
and invoke the method getAllMatchesForPlayer, specifying the id as parameter. For instance, to get the matches for a player with id 86745912:
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
<br>To get the full details of a match the wrapper needs to call the API for that specific match. This is done by specifying the match_id.
<br>For instance, to get the full details of match with id 1404838670: 
``` 

        Match match = matchRetriever.getMatchDetails(1404838670); 
``` 

<br>Now you have access to a Match object which contains all the available data (courtesy of http://dev.dota2.com/showthread.php?t=58317)

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
        players - an array of players (Player-object) with the following properties: 
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
        matchRetriever.getAllMatchesForPlayer(<steam-id>); 
``` 
<br>Bear in mind though that this request will take a lot of time to process (the wrapper has to call the API 505 times). 

##Getting the last 500 matches for a player (IF YOU DON'T KNOW HIS/HER STEAM-ID)

##Getting the full details of a match

Specify the id to your MatchRetriever, e.g. for id 1389940008: 

        MatchRetriever matchRetriever = new MatchRetriever();
        Match match = matchRetriever.getMatchDetails(1389940008);

##Getting all leagues available

##Getting matches for a specific league

##Getting community details about a player

##Checking whether a player has shared match data

##Getting data in XML




