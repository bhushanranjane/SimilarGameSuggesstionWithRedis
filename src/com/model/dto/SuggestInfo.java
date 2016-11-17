package com.model.dto;

import java.util.List;


public class SuggestInfo {
	
	
	private int sequenceId;
	
	private String baseGameId;
	
	public String getBaseGameId() {
		return baseGameId;
	}
	public void setBaseGameId(String baseGameId) {
		this.baseGameId = baseGameId;
	}

	private String gameName;
	
	
	private String packageName;
	

	private String imageUrl;
	

	private String gameUrl;
	

	private String gameRating;
	
	
	private String gameCost;
	
	private String packageid;
	private List<GameInfo> game;
	
	public List<GameInfo> getGame() {
		return game;
	}
	public void setGame(List<GameInfo> game) {
		this.game = game;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getGameUrl() {
		return gameUrl;
	}
	public void setGameUrl(String gameUrl) {
		this.gameUrl = gameUrl;
	}
	public String getGameRating() {
		return gameRating;
	}
	public void setGameRating(String gameRating) {
		this.gameRating = gameRating;
	}
	public int getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}
	public String getGameCost() {
		return gameCost;
	}
	public void setGameCost(String gameCost) {
		this.gameCost = gameCost;
	}
	public String getPackageid() {
		return packageid;
	}
	public void setPackageid(String packageid) {
		this.packageid = packageid;
	}
	
	public SuggestInfo(){
		
	}

	public SuggestInfo(String gameName,String packageName,String imageUrl,String gameUrl,String gameRating,String gameCost){
		setGameName(gameName);
		setPackageName(packageName);
		setGameRating(gameRating);
		setGameUrl(gameUrl);
		setImageUrl(imageUrl);
		setGameCost(gameCost);
		
	}
}
