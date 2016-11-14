package com.model.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.model.dto.GameInfo;

public class PlayStoreDataFetching {

	PlayStoreUrlFetching playStoreUrl = new PlayStoreUrlFetching();
	GameInfo gameInfo = new GameInfo();

	public void getPlaystoreData(String url) {

		String title;
		String packName ;
		String catInfo;
		String packageId;
		String gameUrl;
		String cost;
		String charge ;
		
		boolean result;
		try {

			// fetching the document over HTTP
			Document doc = Jsoup.connect(url).userAgent("Chrome/50.0.2661.94").timeout(10000).get();

			// getting game title class to fetch title
			Elements t = doc.getElementsByClass("document-title");
			
			// Store the title into string
			title = t.select("[class=id-app-title]").text();
			
			// getting package name of the game
			Elements g = doc.getElementsByClass("details-wrapper apps");
			packName = url.substring(url.indexOf("id=") + 3);
			
			// getting the category of the game
			Elements category = doc.getElementsByClass("document-subtitle");
			catInfo = category.select("[itemprop=genre]").text();
			
			// generate unique hash code for each game
			packageId = Integer.toString(packName.hashCode() & Integer.MAX_VALUE);

			// get the url of the game
			gameUrl = url;			

			//get the cost of the game
			Elements status = doc.getElementsByClass("id-track-impression");
			charge = status.text();
			
			
			//set the values game information
			gameInfo.setGameName(title);
			gameInfo.setPackageName(packName);
			gameInfo.setGameCategory(catInfo);
			gameInfo.setPackageId(packageId);
			gameInfo.setGameURL(gameUrl);
				
			//if the game is having charges
			if (charge.startsWith("₹")) {
				cost = charge.substring(charge.indexOf("₹") + 1, charge.indexOf("Buy") - 1);
				gameInfo.setPaid(cost);
			} else {
				gameInfo.setPaid("Free");
			}

			if (packageId.equals("") && title.equals("") && packName.equals("") && catInfo.equals("")
					&& gameUrl.equals("")){
				System.out.println("package id:-" + gameInfo.getPackageId());
				 result=false;
			}
			else {
				/*
				 * playStoreDetails.add(gameInfo.getPackageId());
				 * playStoreDetails.add(gameInfo.getGameName());
				 * playStoreDetails.add(gameInfo.getPackageName());
				 * playStoreDetails.add(gameInfo.getGameCategory());
				 * playStoreDetails.add(gameInfo.getGameURL());
				 * playStoreDetails.add(gameInfo.getPaid());
				 */
				

				System.out.println("package id:-" +gameInfo.getPackageId());
				System.out.println("Game Name:-" + gameInfo.getGameName());
				System.out.println("package Name:-" + gameInfo.getPackageName());
				System.out.println("game category:-" + gameInfo.getGameCategory());
				System.out.println("game url:-" + gameInfo.getGameURL());
				System.out.println("Paid/Unpaid:-" + gameInfo.getPaid());

			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
}
