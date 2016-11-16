package com.model.Main;

import java.util.Scanner;

import com.model.Model.PlayStoreDataFetching;
import com.model.Model.PlayStoreGameSuggesstion;
import com.model.Model.PlayStoreUrlFetching;
import com.model.dto.GameInfo;

public class DataMain {
	
	public static void main(String args[]){

		PlayStoreUrlFetching pUrl=new PlayStoreUrlFetching();
		PlayStoreDataFetching pdata=new PlayStoreDataFetching();
		PlayStoreGameSuggesstion pSuggest=new PlayStoreGameSuggesstion();
		Scanner sc=new Scanner(System.in);
		GameInfo gameInfo=new GameInfo();
		
		System.out.println("Enter the game name");
		String gname=sc.nextLine();
			String url=pUrl.findUrl(gname);
			//System.out.println("url is:-"+url);
		gameInfo=pdata.getPlaystoreData(url);
		pSuggest.getGameSuggesstion(gameInfo);
		
/*		gameInfo=pdata.getPlaystoreData(url);
		suggestInfo=pSuggest.getGameSuggesstion(gameInfo);
		redisImpl.redisData(suggestInfo,gameInfo);
		*/
			
			
		}
		
	}


