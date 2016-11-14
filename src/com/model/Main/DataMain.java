package com.model.Main;

import java.util.Scanner;

import com.model.Model.PlayStoreDataFetching;
import com.model.Model.PlayStoreGameSuggesstion;
import com.model.Model.PlayStoreUrlFetching;

public class DataMain {
	
	public static void main(String args[]){

		PlayStoreUrlFetching pUrl=new PlayStoreUrlFetching();
		PlayStoreDataFetching pdata=new PlayStoreDataFetching();
		PlayStoreGameSuggesstion pSuggest=new PlayStoreGameSuggesstion();
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the game name");
		String gname=sc.nextLine();
			String url=pUrl.findUrl(gname);
			//System.out.println("url is:-"+url);
		pdata.getPlaystoreData(url);
		pSuggest.getGameSuggesstion(url);
			
			
		}
		
	}


