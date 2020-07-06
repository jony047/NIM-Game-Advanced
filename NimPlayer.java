/*
 * Name: Jony Saha
 * Username: jonys
 * Student Number:1134030
 * Version 3.0
 */


import java.io.Serializable;

public abstract class  NimPlayer implements Serializable {
    private String userName;
    private String familyName;
    private String givenName;
    private int gamePlayed;
    private int gameWon;
    private double winRatio;

    //default constructor for an empty object creation

    public NimPlayer() {
        userName="";
        familyName="";
        givenName="";
        gamePlayed= 0;
        gameWon=0;
    }

    //constructor to create new player with all valid inputs

    public NimPlayer(String userName, String familyName, String givenName, int gamePlayed, int gameWon) {
        this.userName = userName;
        this.familyName = familyName;
        this.givenName = givenName;
        this.gamePlayed = gamePlayed;
        this.gameWon = gameWon;
    }



    //Abstract remove Stone method
    public abstract  int removeStone(int stoneLeft, int upperBound);



    //getter and setter methods of all instance variables
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public int getGamePlayed() {
        return gamePlayed;
    }

    public void setGamePlayed(int gamePlayed) {
        this.gamePlayed = gamePlayed;
    }

    public int getGameWon() {
        return gameWon;
    }

    public void setGameWon(int gameWon) {
        this.gameWon = gameWon;
    }

    public double getWinRatio() {
        if (this.gamePlayed == 0)
            return 0;
        else
            return (double)this.getGameWon()/(double)this.getGamePlayed();
    }

    //toString method for printing a player details
    public String toString(){
        return this.getUserName()+","+this.getGivenName()+","+this.getFamilyName()+","+this.getGamePlayed()+" games,"+this.getGameWon()+" wins";
    }
}
