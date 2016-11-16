package com.model.dto;

public class GameInfo {
	
	
	private String gameName;
	
	private String packageId;
	
	
	private String packageName;
	
	
	private String gameCategory;
	
	
	private String gameURL;
	
	
	private String paid;
	

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getGameCategory() {
		return gameCategory;
	}

	public void setGameCategory(String gameCategory) {
		this.gameCategory = gameCategory;
	}

	public String getGameURL() {
		return gameURL;
	}

	public void setGameURL(String gameURL) {
		this.gameURL = gameURL;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public GameInfo(){
		
	}
	
	public GameInfo(String gameName,String packageId,String packageName,String gameCategory,String gameURL,String paid){
		setGameName(gameName);
		setPackageId(packageId);
		setGameCategory(gameCategory);
		setGameURL(gameURL);
		setPackageName(packageName);
		setPaid(paid);
	}
}
