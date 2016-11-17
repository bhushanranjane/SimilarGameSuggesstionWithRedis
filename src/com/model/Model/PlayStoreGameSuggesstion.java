package com.model.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.model.dao.RedisImpl;
import com.model.dto.GameInfo;
import com.model.dto.SuggestInfo;

public class PlayStoreGameSuggesstion {

	PlayStoreUrlFetching playStoreUrl = new PlayStoreUrlFetching();
	List<String> jsonData = new ArrayList<String>();
	RedisImpl redisImpl = new RedisImpl();

	public SuggestInfo getGameSuggesstion(GameInfo gameInfo) {

		String gameUrl = null;
		String packName = null;
		String image = null;
		String flag = null;
		String rating = null;
		String iurl = null;
		String packageId = null;
		String packagecon = null;
		SuggestInfo suggesstion = null;

		ArrayList<SuggestInfo> arrayList = new ArrayList<SuggestInfo>();
		ArrayList<String> imageinfo = new ArrayList<String>();
		ArrayList<String> games = new ArrayList<String>();
		Map<String, String> similarGames = new HashMap<String, String>();

		try {
			Document doc = Jsoup.connect(gameInfo.getGameURL()).userAgent("Chrome/50.0.2661.94").timeout(10000).get();

			// get the titles of the suggested game
			Elements e = doc.select("div.cards.id-card-list");
			Elements t1 = e.select("[class=details]");
			String gameTitle = t1.select("[class=title]").toString();
			String[] gamename = gameTitle.split("title");

			// get the Ratings of the game
			Elements rate = doc.getElementsByClass("reason-set");
			rating = rate.select("[class=reason-set-star-rating]").toString();
			String grating[] = rating.split("editable-container");

			// get the image url for each game
			iurl = doc.getElementsByClass("cover-inner-align").select("[class=cover-image]").toString();
			String jurl[] = iurl.split("data-cover-small");
			for (int k = 0; k < jurl.length; k++) {
				try {
					imageinfo.add(jurl[k].substring(jurl[k].indexOf("src") + 5, jurl[k].indexOf("aria") - 2));
					// System.out.println("Image url is:-" + imageinfo.get(k));
				} catch (StringIndexOutOfBoundsException e2) {
					System.out.println("error in image url");
				}

			}

			// add the names of the game into array list
			for (int i = 0; i < gamename.length; i++) {
				if (gamename[i].contains("href"))
					continue;

				try {
					// take a sub String of the String to find game name
					games.add(gamename[i].substring(gamename[i].indexOf(">") + 1, gamename[i].indexOf("<") - 1));

				} catch (StringIndexOutOfBoundsException e1) {
					System.out.println("error occured");
				}
			}

			// Store the Ratings of all games
			ArrayList<String> gameRating = new ArrayList<String>();

			// add the ratings of the game into array list
			for (int x = 1; x < grating.length; x++) {
				try {
					gameRating.add(grating[x].substring(grating[x].indexOf("label") + 7, grating[x].indexOf(">") - 1));
				} catch (StringIndexOutOfBoundsException e3) {
					System.out.println("Error in ratings");
				}
			}

			// Checks wheather the game is paid or free
			Elements status = doc.getElementsByClass("id-track-impression");
			String charge = status.text();
			String cost = null;
			if (charge.startsWith("₹")) {
				cost = charge.substring(charge.indexOf("₹") + 1, charge.indexOf("Buy") - 1);
				System.out.println("cost " + cost);
				flag = "Paid";
			} else {
				flag = "Free";
			}

			System.out.println("Game Size:-" + games.size());
			// contains all the details of the game
			for (int j = 0; j < games.size(); j++) {
				String baseGameId = gameInfo.getPackageId();
				suggesstion = new SuggestInfo();
				gameUrl = playStoreUrl.findUrl(games.get(j));
				packName = gameUrl.substring(gameUrl.indexOf("id=") + 3);
				packageId = Integer.toString(packName.hashCode() & Integer.MAX_VALUE);
				image = imageinfo.get(j);
				// Append http to the image url
				if (image.contains("http") == false) {
					image = ("http:").concat(image.trim());

				}

				suggesstion.setGameName(games.get(j));
				suggesstion.setPackageName(packName);
				suggesstion.setGameUrl(gameUrl);
				suggesstion.setImageUrl(image);
				suggesstion.setGameRating(gameRating.get(j));
				suggesstion.setBaseGameId(baseGameId);
				suggesstion.setPackageid(packageId);

				if (!flag.equals("Free")) {
					suggesstion.setGameCost(cost);
				} else
					suggesstion.setGameCost("Free");
				redisImpl.redisData(suggesstion);

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		return suggesstion;

	}

}
