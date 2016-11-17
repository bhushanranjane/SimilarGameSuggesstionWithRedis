package com.model.Main;

import java.util.Scanner;

import com.model.Model.PlayStoreDataFetching;
import com.model.Model.PlayStoreGameSuggesstion;
import com.model.Model.PlayStoreUrlFetching;
import com.model.dto.GameInfo;

public class DataMain {

	public static void main(String args[]) {

		PlayStoreUrlFetching pUrl = new PlayStoreUrlFetching();
		PlayStoreDataFetching pdata = new PlayStoreDataFetching();
		PlayStoreGameSuggesstion pSuggest = new PlayStoreGameSuggesstion();
		Scanner sc = new Scanner(System.in);
		GameInfo gameInfo = new GameInfo();

		/*SuggestInfo suggestInfo = new SuggestInfo();*/

		System.out.println("Enter the game name");
		String gname = sc.next();
		String url = pUrl.findUrl(gname);
		pUrl.similarGamesUrl(gname);
		// System.out.println("url is:-"+url);
		/*
		 * gameInfo=pdata.getPlaystoreData(url);
		 * pSuggest.getGameSuggesstion(gameInfo);
		 */
		/*gameInfo = pdata.getPlaystoreData(url);
		pSuggest.getGameSuggesstion(gameInfo);*/
		System.out.println("similar game url"+pUrl.similarGamesUrl(gname));
		/*
		 * System.out.println("Base gam:-"+suggestInfo.getBaseGameId());
		 * redisImpl.redisData(suggestInfo);
		 */

	}

}
