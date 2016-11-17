package com.model.Main;

import java.util.Scanner;

import com.model.Model.PlayStoreDataFetching;
import com.model.Model.PlayStoreGameSuggesstion;
import com.model.Model.PlayStoreUrlFetching;
import com.model.dao.RedisImpl;
import com.model.dto.GameInfo;
import com.model.dto.SuggestInfo;

public class DataMain {
	
	public static void main(String args[]){

		PlayStoreUrlFetching pUrl=new PlayStoreUrlFetching();
		PlayStoreDataFetching pdata=new PlayStoreDataFetching();
		PlayStoreGameSuggesstion pSuggest=new PlayStoreGameSuggesstion();
		Scanner sc=new Scanner(System.in);
		GameInfo gameInfo=new GameInfo();
		
		SuggestInfo suggestInfo=new SuggestInfo();
		
		
		System.out.println("Enter the game name");
		String gname=sc.nextLine();
			String url=pUrl.findUrl(gname);
			//System.out.println("url is:-"+url);
		/*gameInfo=pdata.getPlaystoreData(url);
		pSuggest.getGameSuggesstion(gameInfo);
		*/
		gameInfo=pdata.getPlaystoreData(url);
		suggestInfo=pSuggest.getGameSuggesstion(gameInfo);
		/*System.out.println("Base gam:-"+suggestInfo.getBaseGameId());
		redisImpl.redisData(suggestInfo);*/
		
			
			
		}
		
	}


