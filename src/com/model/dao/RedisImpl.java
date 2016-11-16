package com.model.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sound.midi.MidiDevice.Info;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.model.Model.PlayStoreDataFetching;
import com.model.Model.PlayStoreGameSuggesstion;
import com.model.dto.GameInfo;
import com.model.dto.SuggestInfo;

import redis.clients.jedis.Jedis;

public class RedisImpl {

	PlayStoreDataFetching playStoreDataFetching=new PlayStoreDataFetching();
	PlayStoreGameSuggesstion playStoreGameSuggesstion=new PlayStoreGameSuggesstion();
	GameInfo gameInfo=new GameInfo();
	SuggestInfo suggestInfo=new SuggestInfo();
	Map<String, String> similarGames = new HashMap<String, String>();
	final static Jedis redisConnect = new Jedis("localhost");
	static Gson gson=new Gson();
	public void redisData(SuggestInfo info,GameInfo gInfo)
	{
		String packagecon;
		similarGames.put("Gamename",suggestInfo.getGameName());
		similarGames.put("PackageName",suggestInfo.getPackageName());
		similarGames.put("GameUrl",suggestInfo.getGameUrl());
		similarGames.put("imagurl",suggestInfo.getImageUrl());
		similarGames.put("PackageId",suggestInfo.getPackageid());
		similarGames.put("Ratings",suggestInfo.getGameRating());
		
		Set<Map<String, String>> suggestedGames = new HashSet<Map<String, String>>();
		suggestedGames.add(similarGames);

		// redis data with base game as key
		String baseGameId = gInfo.getPackageId().substring(0, 3);
		redisConnect.hset("GameId:-" + baseGameId, "Package Id:-" + info.getPackageid(),
				"Game Suggestion:-" + suggestedGames.toString());

		// Storing related game related package id in json
		Set<String> relatedPackage = new HashSet<String>();
		relatedPackage.add(info.getPackageid());

		packagecon = gson.toJson(relatedPackage);
		redisConnect.hset("Jsondata" + baseGameId, "Pack id:-" + gInfo.getPackageId(), packagecon);

		List<String> jsonData = redisConnect.hmget(baseGameId, gInfo.getPackageId());
		
		
		Set<String> recordSet = new HashSet<String>();
		if (jsonData.get(0) != null) {
			recordSet = gson.fromJson(jsonData.get(0), new TypeToken<Set<String>>() {}.getType());
			addData(recordSet, info.getPackageid(), packagecon);
		}
		else
		{
			addData(recordSet,info.getPackageid(), packagecon);
		}



	}
	public static void addData(Set<String> record, String packageId, String packagecon) {
		GameInfo gameDetails=new GameInfo();
		record.add(packageId);
		String packageIdString = null;
		packageIdString = gson.toJson(record);
		redisConnect.hset("Base packageId:"+gameDetails.getPackageId().substring(0, 3) ,gameDetails.getPackageId(), packageIdString);
	}
	
}
